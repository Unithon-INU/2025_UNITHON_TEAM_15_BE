package com.unithon.team15_server.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberSignInReq {

    @NotBlank
    @Schema(description = "회원의 이메일", example = "qwer1234@naver.com")
    private String email;

    @NotBlank
    @Schema(description = "회원의 비밀번호", example = "qwer1234")
    private String password;
}
