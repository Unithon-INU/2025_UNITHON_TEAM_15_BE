package com.unithon.team15_server.global.university;

import com.unithon.team15_server.global.university.enums.ProgramType;
import com.unithon.team15_server.global.university.enums.UniversityType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "accredited_university_tb")
public class AccreditedUniversity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    ProgramType programType;

    @Enumerated(EnumType.STRING)
    UniversityType universityType;

    @Builder
    private AccreditedUniversity(String name, ProgramType programType, UniversityType universityType) {
        this.name = name;
        this.programType = programType;
        this.universityType = universityType;
    }

    public static AccreditedUniversity create(String name, ProgramType programType, UniversityType universityType) {
        return AccreditedUniversity.builder()
                .name(name)
                .programType(programType)
                .universityType(universityType)
                .build();
    }
}