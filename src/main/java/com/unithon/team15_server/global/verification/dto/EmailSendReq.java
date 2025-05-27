package com.unithon.team15_server.global.verification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailSendReq {

    @NotBlank
    @Schema(description = "가입할 이메일", example = "qwer1234@gmail.com")
    private String email;
}
