package com.unithon.team15_server.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberSignupReq {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}