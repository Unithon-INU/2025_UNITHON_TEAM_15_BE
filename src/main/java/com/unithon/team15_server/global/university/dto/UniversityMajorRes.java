package com.unithon.team15_server.global.university.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UniversityMajorRes {

    @Schema(description = "학과명", example = "컴퓨터공학부")
    private final String majorName;

    @Schema(description = "학위과정", example = "4학년")
    private final String lssnTerm;
}
