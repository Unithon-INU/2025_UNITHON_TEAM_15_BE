package com.unithon.team15_server.domain.employmentcheck.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TipInfoRes {

    @Schema(description = "tip 제목", example = "제목입니다.")
    private final String title;

    @Schema(description = "tip 세부 정보")
    private final List<TipInfoDetailRes> tipInfoDetailRes;
}
