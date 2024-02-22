package com.example.demo.service;

import com.example.demo.model.Image;

public interface ImageService {
    byte[] getImageById(String id);
    String uploadImage(byte[] data);
}
