package com.unithon.team15_server.global.university.dto.UniApi;

import lombok.Getter;

import java.util.List;

@Getter
public class UniversityApiBody {

    private List<UniversityApiMajorItem> items;
    private String totalCount;
    private String numOfRows;
    private String pageNo;
}
