package com.example.demo.web;

import com.example.demo.model.DogReport;
import com.example.demo.model.dto.DogReportDTO;
import com.example.demo.service.DogReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dog-reports")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DogReportController {
    private final DogReportService dogReportService;

    public DogReportController(DogReportService dogReportService) {
        this.dogReportService = dogReportService;
    }

    @GetMapping
    private List<DogReport> getAllDogReports(){
        return this.dogReportService.getAllDogReports();
    }

    @PostMapping("/create")
    private DogReport createDogReport(@RequestBody DogReportDTO dogReport){
        return this.dogReportService.createDogReport(dogReport);
    }

}
