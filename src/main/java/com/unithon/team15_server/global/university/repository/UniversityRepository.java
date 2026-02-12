package com.unithon.team15_server.global.university.repository;

import com.unithon.team15_server.global.university.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findByUniversityContaining(String university);
}