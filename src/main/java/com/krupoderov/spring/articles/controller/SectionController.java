package com.krupoderov.spring.articles.controller;

import com.krupoderov.spring.articles.model.Section;
import com.krupoderov.spring.articles.repository.SectionRepository;
import com.krupoderov.spring.articles.repository.TopicRepository;
import com.krupoderov.spring.articles.response.ResourceNotFoundException;
import com.krupoderov.spring.articles.utils.Constants;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/section")
public class SectionController {

    private final SectionRepository sectionRepository;
    private final TopicRepository topicRepository;

    public SectionController(SectionRepository sectionRepository, TopicRepository topicRepository) {
        this.sectionRepository = sectionRepository;
        this.topicRepository = topicRepository;
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id,
                       Model model,
                       @PageableDefault(sort = {"dateOfPublication"}, value = Constants.PAGE_DEFAULT_SIZE, direction = Sort.Direction.DESC) Pageable pageable) {

        if (!sectionRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }

        Section section = sectionRepository.findById(id).orElse(null);

        model.addAttribute("title", section.getTitle());
        model.addAttribute("section", section);
        model.addAttribute("topics", topicRepository.findBySection(section, pageable));
        return "section/view";
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/";
    }
}
