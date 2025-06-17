package com.unithon.team15_server.domain.employmentcheck.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unithon.team15_server.domain.employmentcheck.CheckStep;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateEmplCheckDetailReq {

    @Schema(description = "step", example = "STEP1")
    @NotBlank
    private CheckStep checkStep;

    @Schema(description = "제출 서류 체크리스트의 인덱스", example = "1")
    @NotBlank
    private int submissionIdx;

    @Schema(description = "체크리스트의 체크 유무", example = "true")
    @JsonProperty("isChecked")
    private boolean isChecked;
}
