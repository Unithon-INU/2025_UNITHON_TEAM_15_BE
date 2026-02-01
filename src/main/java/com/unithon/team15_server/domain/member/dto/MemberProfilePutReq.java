package com.unithon.team15_server.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberProfilePutReq {

    @NotBlank
    @Schema(description = "비자 종류", example = "D-2")
    private String visaType;

    @NotBlank
    @Schema(description = "언어 능력", example = "TOPIK 3급")
    private String languageLevel;

    @NotBlank
    @Schema(description = "업종 (쉼표로 구분)", example = "음식점/카페,편의점/마트")
    private String industry;
}
