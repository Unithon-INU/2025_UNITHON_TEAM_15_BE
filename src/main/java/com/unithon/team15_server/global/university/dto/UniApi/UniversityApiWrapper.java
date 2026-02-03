package com.unithon.team15_server.global.university.dto.UniApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UniversityApiWrapper {

    @JsonProperty("response")
    private UniversityApiResponse universityApiResponse;
}
