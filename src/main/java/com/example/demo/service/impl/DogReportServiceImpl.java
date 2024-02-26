package com.example.demo.service.impl;

import com.example.demo.model.DogReport;
import com.example.demo.model.User;
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
    public List<DogReportDTO> findByUserId(String userId) {
        List<DogReport> dogReports = dogReportRepository.findByUserId(userId);
        return dogReports.stream().map(DogReportDTO::fromEntity).toList();
    }

    @Override
    public DogReportDTO getDogReport(String id) {
        DogReport dogReport = dogReportRepository.findById(id).orElseThrow(() -> new RuntimeException("Dog report not found"));
        return DogReportDTO.fromEntity(dogReport);
    }

    @Override
    public void deleteDogReport(String id) {
        DogReport dogReport = dogReportRepository.findById(id).orElseThrow(() -> new RuntimeException("Dog report not found"));
        User user = userRepository.findByUsername(dogReport.getUserId());
        if (user != null) {
            user.getDogReports().remove(dogReport);
            userRepository.save(user);
        }
        dogReportRepository.deleteById(id);
    }

    @Override
    public DogReport createDogReport(DogReportDTO dogReportDTO) {
        DogReport dogReport = DogReport.fromDTO(dogReportDTO);

        if(!userRepository.existsUserByUsername(dogReportDTO.getUserId())) {
            throw new RuntimeException("User does not exist");
        }
        userRepository.findByUsername(dogReportDTO.getUserId()).getDogReports().add(dogReport);

        dogReportRepository.save(dogReport);

        return dogReport;

    }
}
