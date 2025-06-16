package com.unithon.team15_server.domain.employmentcheck.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.unithon.team15_server.domain.employmentcheck.CheckStep;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Checks;

import java.util.List;

@Getter
@Builder
public class HomeInfoRes {

    private String nickname;

    private float progress;

    private CheckStep memberCheckStep;

    private List<EmploymentCheckRes> employmentCheckRes;
}
