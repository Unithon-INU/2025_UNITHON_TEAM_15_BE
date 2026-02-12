package com.unithon.team15_server.global.university.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AccreditedUniversityDto {

    private final DegreeProgram degree;
    private final LanguageProgram language;

    @Getter
    @NoArgsConstructor
    public static class DegreeProgram {

        private List<String> bachelors;
        private List<String> associate;
        private List<String> graduate;
    }

    @Getter
    @NoArgsConstructor
    public static class LanguageProgram {

        private List<String> bachelors;
        private List<String> associate;
        private List<String> graduate;
    }

}
