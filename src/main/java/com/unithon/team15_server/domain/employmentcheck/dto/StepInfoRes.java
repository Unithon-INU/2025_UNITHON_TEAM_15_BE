package com.unithon.team15_server.domain.employmentcheck.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StepInfoRes {

    @Schema(description = "step 제목", example = "근로계약서 작성")
    private String title;

    @Schema(description = "step 부제목", example = "근로계약서를 작성하는 단계예요")
    private String subtitle;

    @Schema(description = "step의 유의사항들")
    private List<String> precautions;

}
