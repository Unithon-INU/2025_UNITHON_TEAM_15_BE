package com.unithon.team15_server.global.university.repository;

import com.unithon.team15_server.global.university.entity.AccreditedUniversity;
import com.unithon.team15_server.global.university.enums.ProgramType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccreditedUniversityRepository extends JpaRepository<AccreditedUniversity, Long> {

    boolean existsByUniversityAndProgramType(String name, ProgramType programType);

    boolean existsByUniversityEngAndProgramType(String name, ProgramType programType);

    List<AccreditedUniversity> getAccreditedUniversitiesByProgramType(ProgramType programType);
}
