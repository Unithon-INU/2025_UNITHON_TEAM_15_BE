package com.unithon.team15_server.domain.dto;

import lombok.Getter;

@Getter
public class MemberSignInReq {
    private String email;
    private String password;
}
