package com.unithon.team15_server.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberProfileSetReq {

    @NotBlank
    @Schema(description = "설정한 언어", example = "English")
    private String language;

    @NotBlank
    @Schema(description = "비자 종류", example = "D-2")
    private String visaType;

    @NotBlank
    @Schema(description = "한국어 능력", example = "TOPIK 3급")
    private String topikLevel;

    @NotBlank
    @Schema(description = "업종", example = "음식점/카페,편의점/마트")
    private String industry;
}
