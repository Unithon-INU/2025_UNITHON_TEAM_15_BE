package com.unithon.team15_server.domain.certificate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
public class WorkTimeReq {

    @Schema(description = "근무 시작 시간", example = "09:00:00")
    private LocalTime workingStartTime;

    @Schema(description = "근무 종료 시간", example = "18:00:00")
    private LocalTime workingEndTime;

    @Schema(description = "근무 요일")
    private List<String> day;
}
