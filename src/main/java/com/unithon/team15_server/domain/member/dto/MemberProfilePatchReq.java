package com.unithon.team15_server.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberProfilePatchReq {

    @NotBlank
    @Schema(description = "비자 종류/언어 능력/희망 업종 중 하나의 값을 넣어주세요.")
    private String value;
}
