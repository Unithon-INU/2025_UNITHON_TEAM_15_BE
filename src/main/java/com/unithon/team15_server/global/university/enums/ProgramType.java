package com.unithon.team15_server.global.university.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProgramType {
    DEGREE("학위과정"),
    LANGUAGE("어학연수");

    private final String description;
}
