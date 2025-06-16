package com.unithon.team15_server.domain.employmentcheck.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TipInfoRes {

    private final String title;

    private final List<String> itemTitle;

    private final List<String> itemContent;

    private final String warning;
}
