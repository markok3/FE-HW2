package com.example.demo.service.impl;

import com.example.demo.model.DogReport;
import com.example.demo.model.dto.DogReportDTO;
import com.example.demo.repository.DogReportRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DogReportService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DogReportServiceImpl implements DogReportService {
    private final DogReportRepository dogReportRepository;
    private final UserRepository userRepository;

    public DogReportServiceImpl(DogReportRepository dogReportRepository, UserRepository userRepository) {
        this.dogReportRepository = dogReportRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DogReport> getAllDogReports() {
        return dogReportRepository.findAll();
    }

    @Override
    public DogReport createDogReport(DogReportDTO dogReportDTO) {
        DogReport dogReport = DogReport.fromDTO(dogReportDTO);

        if(!userRepository.existsUserByUsername(dogReportDTO.getUserId())) {
            throw new RuntimeException("User does not exist");
        }
        userRepository.findByUsername(dogReportDTO.getUserId()).getDogReports();

        dogReportRepository.save(dogReport);

        return dogReport;

    }
}
