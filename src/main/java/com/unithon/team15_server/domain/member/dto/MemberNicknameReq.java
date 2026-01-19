package com.unithon.team15_server.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberNicknameReq {

    @NotBlank
    @Schema(description = "회원의 닉네임", example = "박춘봉")
    private String nickname;
}
