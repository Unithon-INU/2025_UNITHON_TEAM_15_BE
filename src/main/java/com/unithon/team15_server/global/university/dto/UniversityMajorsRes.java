package com.unithon.team15_server.global.university.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UniversityMajorsRes {

    @Schema(description = "학과명", example = "컴퓨터공학부")
    private final String major;

    @Schema(description = "수업연한", example = "4년")
    private final String studyPeriod;
}
