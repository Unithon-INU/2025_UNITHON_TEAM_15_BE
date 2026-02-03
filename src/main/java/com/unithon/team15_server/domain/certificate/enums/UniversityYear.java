package com.unithon.team15_server.domain.certificate.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UniversityYear {

    ASSOCIATE("전문대"),
    BACHELOR_1("학사 1학년"),
    BACHELOR_2("학사 2학년"),
    BACHELOR_3("학사 3학년"),
    BACHELOR_4("학사 4학년"),
    GRADUATE("석박사");

    private final String description;
}
