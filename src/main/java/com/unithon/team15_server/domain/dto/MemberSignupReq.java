package com.unithon.team15_server.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberSignupReq {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}