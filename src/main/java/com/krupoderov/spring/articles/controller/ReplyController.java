package com.krupoderov.spring.articles.controller;

import com.krupoderov.spring.articles.model.Reply;
import com.krupoderov.spring.articles.model.Topic;
import com.krupoderov.spring.articles.repository.ReplyRepository;
import com.krupoderov.spring.articles.repository.TopicRepository;
import com.krupoderov.spring.articles.response.ResourceNotFoundException;
import com.krupoderov.spring.articles.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

@Controller
@PreAuthorize(value = "hasRole('USER') or hasRole('MODERATOR')")
@RequestMapping("/reply")
public class ReplyController {

    private final TopicRepository topicRepository;
    private final ReplyRepository replyRepository;
    private final UserService userService;

    public ReplyController(TopicRepository topicRepository, ReplyRepository replyRepository, UserService userService) {
        this.topicRepository = topicRepository;
        this.replyRepository = replyRepository;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String add(@RequestParam("topic_id") Long topicId,
                      @RequestParam(value = "reply_id", required = false) Long replyId,
                      Model model) {
        model.addAttribute("title", "Add reply");

        Topic topic;
        Reply replyTo;

        if (Objects.isNull(topicId) || Objects.isNull(topic = topicRepository.findById(topicId).orElse(null))) {
            throw new ResourceNotFoundException();
        } else {
            model.addAttribute("topic", topic);
        }

        if (Objects.nonNull(replyId) && Objects.nonNull(replyTo = replyRepository.findById(replyId).orElse(null))) {
            model.addAttribute("replyTo", replyTo);
        }

        model.addAttribute("reply", new Reply());

        return "reply/add";
    }

    @PostMapping("/add")
    public String update(@RequestParam("topic_id") Long topicId,
                         @RequestParam(value = "reply_id", required = false) Long replyId,
                         @Valid Reply reply,
                         BindingResult result,
                         Model model) {
        model.addAttribute("title", "Update reply");

        Topic topic;
        Reply replyTo = null;

        if (Objects.isNull(topicId) || !topicRepository.existsById(topicId)) {
            throw new ResourceNotFoundException();
        } else {
            topic = topicRepository.findById(topicId).orElse(null);
            model.addAttribute("topic", topic);
        }

        if (Objects.nonNull(replyId) && replyRepository.existsById(replyId)) {
            replyTo = replyRepository.findById(replyId).orElse(null);
            model.addAttribute("replyTo", replyTo);
            reply.setReplyTo(replyTo);
        }

        if (result.hasErrors()) {
            return "reply/add";
        }

        if (Objects.isNull(reply.getId())) {
            reply.setUser(userService.getCurrentUser());
            reply.setTopic(topic);
            reply.setDateOfPublication(new Date());

            if (Objects.nonNull(replyTo)) {
                reply.setReplyTo(replyTo);
            }
        } else {
            Reply editReply = replyRepository.findById(reply.getId()).orElse(null);

            if (!userService.isCurrentUserId(editReply.getUser().getId()) | userService.hasRole("ROLE_MODERATOR")) {
                throw new AccessDeniedException("This user can't edit this reply");
            }

            editReply.setText(reply.getText());
            editReply.setChangedUser(userService.getCurrentUser());
            editReply.setDateOfChange(new Date());

            reply = editReply;
        }

        replyRepository.save(reply);

        return "redirect:/topic/" + topic.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("title", "Edit reply");

        Reply reply = replyRepository.findById(id).orElse(null);

        if (Objects.isNull(reply)) {
            throw new ResourceNotFoundException();
        }

        if (!(userService.isCurrentUserId(reply.getUser().getId()) || userService.hasRole("ROLE_MODERATOR"))) {
            throw new AccessDeniedException("This user can't edit this reply");
        }

        if (Objects.isNull(reply.getTopic())) {
            throw new RuntimeException();
        } else {
            model.addAttribute("topic", reply.getTopic());
        }

        if (Objects.nonNull(reply.getReplyTo())) {
            model.addAttribute("replyTo", reply.getReplyTo());
        }

        model.addAttribute("reply", reply);

        return "reply/add";
    }

    @GetMapping("/{id}/delete")
    public String confirmRemoval(@PathVariable Long id, Model model) {
        model.addAttribute("title", "Delete reply");

        Reply reply = replyRepository.findById(id).orElse(null);

        if (Objects.isNull(reply)) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("reply", reply);

        return "reply/delete";
    }

    @PostMapping("/{id}/delete")
    public String remove(@PathVariable Long id) {

        Reply reply = replyRepository.findById(id).orElse(null);

        if (Objects.isNull(reply)) {
            throw new ResourceNotFoundException();
        }

        replyRepository.deleteById(id);

        return "redirect:/topic/" + reply.getTopic().getId();
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") String id) {
        return "redirect:/";
    }
}
