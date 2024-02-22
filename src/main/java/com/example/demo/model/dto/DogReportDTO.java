package com.example.demo.model.dto;

import com.example.demo.model.DogReport;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DogReportDTO {
    private String id;
    private boolean isLost;
    private String userId;
    private String title;
    private String description;
    private String imgUrl;
    private LocalDateTime dateTime;
    private double latitude;
    private double longitude;

    public static DogReportDTO fromEntity(DogReport dogReport) {
        DogReportDTO dto = new DogReportDTO();
        dto.setId(dogReport.getId());
        dto.setLost(dogReport.isLost());
        dto.setUserId(dogReport.getUserId());
        dto.setTitle(dogReport.getTitle());
        dto.setDescription(dogReport.getDescription());
        dto.setImgUrl(dogReport.getImgUrl());
        dto.setDateTime(dogReport.getDateTime());
        dto.setLatitude(dogReport.getLatitude());
        dto.setLongitude(dogReport.getLongitude());
        return dto;
    }
}
