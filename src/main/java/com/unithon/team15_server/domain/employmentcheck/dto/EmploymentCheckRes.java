package com.unithon.team15_server.domain.employmentcheck.dto;

import com.unithon.team15_server.domain.employmentcheck.CheckStep;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EmploymentCheckRes {

    @Schema(description = "step", example = "STEP1")
    private CheckStep checkStep;

    @Schema(description = "step 정보")
    private StepInfoRes stepInfoRes;

    @Schema(description = "step의 제출 서류")
    private List<DocumentInfoRes> documentInfoRes;
}
