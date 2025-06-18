package com.unithon.team15_server.domain.employmentcheck.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TipInfoDetailRes {

    @Schema(description = "tip 세부 제목", example = "세부제목입니다.")
    private final String itemTitle;

    @Schema(description = "tip 세부 내용", example = "세부내용입니다.")
    private final String itemContent;

    @Schema(description = "tip 경고", example = "하지마세요.")
    private final String warning;
}
