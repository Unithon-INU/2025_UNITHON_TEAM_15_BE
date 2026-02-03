package com.unithon.team15_server.global.university.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UniversityRes {

    @Schema(description = "대학교명", example = "인천대학교")
    private final String university;

    @Schema(description = "학과 리스트")
    private final List<UniversityMajorRes> majors;
}
