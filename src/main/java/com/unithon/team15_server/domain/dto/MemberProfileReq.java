package com.unithon.team15_server.domain.dto;

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
