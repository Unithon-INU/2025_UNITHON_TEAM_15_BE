package com.unithon.team15_server.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberSignInReq {
    private String email;
    private String password;
}
