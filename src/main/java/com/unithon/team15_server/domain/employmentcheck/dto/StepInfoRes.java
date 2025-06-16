package com.unithon.team15_server.domain.employmentcheck.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StepInfoRes {

    private String title;

    private String subtitle;

    private List<String> precautions;

}
