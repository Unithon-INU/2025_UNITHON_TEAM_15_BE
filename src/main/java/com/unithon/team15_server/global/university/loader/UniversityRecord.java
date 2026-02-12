package com.unithon.team15_server.global.university.loader;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UniversityRecord {

    @JsonProperty("학교명")
    private String university;

    @JsonProperty("학위과정명")
    private String universityType;

    @JsonProperty("학과명")
    private String major;

    @JsonProperty("수업연한")
    private String studyPeriod;

    @JsonProperty("학과상태명")
    private String majorStatus;
}