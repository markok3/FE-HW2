package com.example.demo.service.impl;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import org.springframework.stereotype.Service;


@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public byte[] getImageById(String id) {
        return imageRepository.getById(id).getData();
    }

    @Override
    public String uploadImage(byte[] data) {
        Image newImage = new Image();
        newImage.setData(data);

        String imageId = imageRepository.save(newImage).getId();

        return imageId;
    }
}
