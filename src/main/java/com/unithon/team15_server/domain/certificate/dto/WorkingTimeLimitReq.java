package com.unithon.team15_server.domain.certificate.dto;

import com.unithon.team15_server.domain.certificate.enums.UniversityYear;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WorkingTimeLimitReq {

    @Schema(description = "대학교명", example = "인천대학교")
    private final String university;

    @Schema(description = "학과명", example = "컴퓨터공학부")
    private final String major;

    @Schema(description = "학년", example = "BACHELOR_2")
    private final UniversityYear year;
}
