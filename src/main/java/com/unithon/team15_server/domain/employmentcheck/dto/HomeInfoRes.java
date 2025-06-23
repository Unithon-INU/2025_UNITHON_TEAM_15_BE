package com.unithon.team15_server.domain.employmentcheck.dto;

import com.unithon.team15_server.domain.employmentcheck.CheckStep;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class HomeInfoRes {

    @Schema(description = "회원의 닉네임", example = "봉식이")
    private String nickname;

    @Schema(description = "회원의 이메일", example = "asdf@gmail.com")
    private String email;
    
    @Schema(description = "진행 현황")
    private int progress;

    @Schema(description = "회원이 진행하고 있던 step", example = "STEP1")
    private CheckStep memberCheckStep;

    @Schema(description = "각 step의 정보")
    private List<EmploymentCheckRes> employmentCheckRes;
}
