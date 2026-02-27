package com.unithon.team15_server.global.university.dto;

import com.unithon.team15_server.global.university.enums.UniversityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AccreditedUniversityRes {
    private final List<UniversityInfo> university;

    @Getter
    @AllArgsConstructor
    public static class UniversityInfo {
        private String nameKo;
        private String nameEn;
        private UniversityType universityType;
    }
}
