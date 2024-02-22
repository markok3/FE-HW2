package com.example.demo.web;

import com.example.demo.model.Image;
import com.example.demo.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    private byte[] getImageById(@PathVariable String id){
        return this.imageService.getImageById(id);
    }

    @PostMapping("/upload")
    private String uploadImage(@RequestBody byte[] data){
        return this.imageService.uploadImage(data);
    }

}
