package com.unithon.team15_server.domain.employmentcheck.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DocumentInfoRes {

    private String title;

    private boolean isChecked;
}
