package com.unithon.team15_server.global.verification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailVerifyCodeReq {

    @NotBlank
    @Schema(description = "가입할 이메일", example = "qwer1234@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "가입할 이메일로 보낸 인증 코드", example = "qwer1234")
    private String code;
}
