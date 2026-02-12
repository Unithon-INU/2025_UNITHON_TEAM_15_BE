package com.unithon.team15_server.global.university.entity;

import com.unithon.team15_server.global.university.enums.UniversityType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "university_tb")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String university;   // 학교명

    @Enumerated(EnumType.STRING)
    private UniversityType universityType;   // 학교구분명(과정명)

    private String studyPeriod;         // 수업연한

    private String major;        // 학과명

    @Builder
    public University(String university, UniversityType universityType, String studyPeriod, String major) {
        this.university = university;
        this.universityType = universityType;
        this.studyPeriod = studyPeriod;
        this.major = major;
    }
}