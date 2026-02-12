package com.unithon.team15_server.global.university.dto;

import com.unithon.team15_server.global.university.enums.UniversityType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UniversityRes {

    @Schema(description = "학교명", example = "인천대학교")
    private final String university;

    @Schema(description = "학교 유형(과정명)", example = "인천대학교")
    private final UniversityType universityType;

    @Schema(description = "학과 리스트")
    private final List<UniversityMajorsRes> majors;
}
