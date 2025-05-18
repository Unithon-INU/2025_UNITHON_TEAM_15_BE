package com.unithon.team15_server.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberProfileReq {

    @NotBlank
    private String language;

    @NotBlank
    private String languageLevel;

    @NotBlank
    private String visaType;

    @NotBlank
    private String industry;
}
