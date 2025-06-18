package com.unithon.team15_server.domain.employmentcheck.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DocumentInfoRes {

    @Schema(description = "제출 서류 체크리스트의 인덱스", example = "1")
    private int submissionIdx;

    @Schema(description = "제출 서류 제목", example = "성적증명서")
    private String title;

    @Schema(description = "체크리스트의 체크 유무", example = "true")
    private Boolean isChecked;
}
