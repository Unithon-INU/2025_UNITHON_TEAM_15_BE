package com.unithon.team15_server.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberNicknameReq {

    @NotBlank
    private String nickname;
}
