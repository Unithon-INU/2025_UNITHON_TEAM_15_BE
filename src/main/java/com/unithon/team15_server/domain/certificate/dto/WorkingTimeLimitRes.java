package com.unithon.team15_server.domain.certificate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WorkingTimeLimitRes {

    @Schema(description = "주당 시간 (null == 주중과 주말 근무시간 각자 다름)", example = "10")
    private final Integer weeklyHours;

    @Schema(description = "주중 시간", example = "20")
    private final Integer weekdayHours;

    @Schema(description = "- 주말 및 방학 시간 (null == 무제한)\n" +
            "- weeklyHours가 null일 경우, weekendHours의 null값은 아무 의미 X", example = "null")
    private final Integer weekendHours;
}
