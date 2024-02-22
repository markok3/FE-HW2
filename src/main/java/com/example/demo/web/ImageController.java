package com.example.demo.web;

import com.example.demo.model.Image;
import com.example.demo.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    private static final String UPLOAD_FOLDER = "C:\\Users\\marko\\Desktop\\FAKS\\demo3\\src\\main\\java\\com\\example\\demo\\images\\";


    @GetMapping("/{id}")
    private byte[] getImageById(@PathVariable String id) {
        try {
            // Load the image file from the uploads folder
            File file = new File(UPLOAD_FOLDER + id ) ;
            FileInputStream fis = new FileInputStream(file);
            byte[] imageData = fis.readAllBytes();
            fis.close();
            return imageData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/upload")
    private String uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload";
        }

        try {
            // Generate a unique filename for the image
            String fileName = UUID.randomUUID().toString() + ".jpg";

            // Create the images folder if it doesn't exist
            Path imagesPath = Paths.get(UPLOAD_FOLDER);
            if (!Files.exists(imagesPath)) {
                Files.createDirectories(imagesPath);
            }

            // Save the image to the images folder
            byte[] data = file.getBytes();
            Files.write(imagesPath.resolve(fileName), data);

            return  fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload image";
        }
    }


}
