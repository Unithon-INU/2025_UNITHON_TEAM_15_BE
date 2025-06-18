package com.unithon.team15_server.domain.employmentcheck.dto;

import com.unithon.team15_server.domain.employmentcheck.CheckStep;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateEmplCheckReq {

    @Schema(description = "update하는 Step", example = "STEP1")
    @NotBlank
    private CheckStep checkStep;

    @Schema(description = "제출 서류 체크리스트의 인덱스", example = "1")
    @NotBlank
    private int submissionIdx;
}
