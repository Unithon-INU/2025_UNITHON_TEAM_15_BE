package com.unithon.team15_server.domain.certificate.dto;

import com.unithon.team15_server.domain.certificate.enums.UniversityYear;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WorkingTimeLimitReq {

    @Schema(description = "학년", example = "BACHELOR_2")
    private final UniversityYear year;

    @Schema(description = "인증대 여부", example = "true")
    private final Boolean isAccredited;
}
