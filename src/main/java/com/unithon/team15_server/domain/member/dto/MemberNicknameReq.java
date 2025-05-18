package com.unithon.team15_server.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberNicknameReq {

    @NotBlank
    private String nickname;
}
