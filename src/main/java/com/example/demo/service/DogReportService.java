package com.example.demo.service;

import com.example.demo.model.DogReport;
import com.example.demo.model.dto.DogReportDTO;

import java.util.List;

public interface DogReportService {
    List<DogReport> getAllDogReports();
    DogReport createDogReport(DogReportDTO dogReportDTO);
    List<DogReportDTO> findByUserId(String userId);
    DogReportDTO getDogReport(String id);
    void deleteDogReport(String id);

}
