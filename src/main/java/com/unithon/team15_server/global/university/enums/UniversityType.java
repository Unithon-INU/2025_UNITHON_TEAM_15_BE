package com.unithon.team15_server.global.university.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UniversityType {

    ASSOCIATE("전문대"),
    BACHELOR("학사"),
    GRADUATE("석박사");

    private final String description;
}