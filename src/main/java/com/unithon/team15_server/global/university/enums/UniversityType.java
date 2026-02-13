package com.unithon.team15_server.global.university.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UniversityType {

    BACHELOR("학사", 1),
    GRADUATE("석박사",2),
    ASSOCIATE("전문대",3);

    private final String description;
    private final int order;
}