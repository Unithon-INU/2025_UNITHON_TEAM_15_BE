package com.unithon.team15_server.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberProfileSetReq {

    @Schema(description = "설정한 언어", example = "English")
    @NotBlank
    private String language;

    @Schema(description = "비자 종류", example = "D-2")
    @NotBlank
    private String visaType;

    @Schema(description = "한국어 능력", example = "TOPIK 3급")
    @NotBlank
    private String topikLevel;

    @Schema(description = "업종", example = "음식점/카페,편의점/마트")
    @NotBlank
    private String industry;
}
