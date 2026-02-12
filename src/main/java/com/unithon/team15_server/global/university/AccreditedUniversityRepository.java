package com.unithon.team15_server.global.university;

import com.unithon.team15_server.global.university.enums.ProgramType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccreditedUniversityRepository extends JpaRepository<AccreditedUniversity, Long> {

    boolean existsByNameAndProgramType(String name, ProgramType programType);
}
