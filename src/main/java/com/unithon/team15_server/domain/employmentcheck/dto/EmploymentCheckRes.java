package com.unithon.team15_server.domain.employmentcheck.dto;

import com.unithon.team15_server.domain.employmentcheck.CheckStep;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EmploymentCheckRes {

    private CheckStep checkStep;

    private StepInfoRes stepInfoRes;

    private List<DocumentInfoRes> documentInfoRes;
}
