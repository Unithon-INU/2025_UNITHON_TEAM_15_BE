package com.unithon.team15_server.domain.employmentcheck.dto;

import com.unithon.team15_server.domain.employmentcheck.CheckStep;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateEmplCheckReq {

    @Schema(description = "현재 회원이 진행 중인 step", example = "STEP1")
    @NotBlank
    private CheckStep memberCheckStep;

    @Schema(description = "모든 step의 제출 서류 정보")
    private List<UpdateEmplCheckDetailReq> updateEmplCheckDetailReqs;
}
