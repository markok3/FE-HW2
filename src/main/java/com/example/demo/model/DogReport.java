package com.example.demo.model;

import com.example.demo.model.dto.DogReportDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class DogReport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private boolean isLost;
    private String userId;
    private String title;
    private String description;
    private String imgUrl;

    private LocalDateTime dateTime;

    double latitude;
    double longitude;

    public DogReport() {
    }

    public static DogReport fromDTO(DogReportDTO dto) {
        DogReport entity = new DogReport();

        entity.setLost(dto.isLost());
        entity.setUserId(dto.getUserId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDateTime(dto.getDateTime());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        return entity;
    }
}
