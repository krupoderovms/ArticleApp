package com.krupoderov.spring.articles.controller;

import com.krupoderov.spring.articles.model.Photo;
import com.krupoderov.spring.articles.repository.PhotoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PhotoController {

    private final PhotoRepository photoRepository;

    public PhotoController(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @GetMapping("/photo")
    public void showImage(@RequestParam("id") Long imageId, HttpServletResponse response) throws IOException {

        Photo photo = photoRepository.findByUserId(imageId);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(photo.getPhoto());
        response.getOutputStream().close();
    }
}
