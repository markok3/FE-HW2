package com.example.demo.repository;

import com.example.demo.model.DogReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogReportRepository extends JpaRepository<DogReport, String> {
    List<DogReport> findAll();
    List<DogReport> findByUserId(String userId);
}
