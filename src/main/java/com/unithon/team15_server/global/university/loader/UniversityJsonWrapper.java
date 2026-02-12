package com.unithon.team15_server.global.university.loader;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UniversityJsonWrapper {
    private List<Field> fields;
    private List<UniversityRecord> records;

    @Getter
    @NoArgsConstructor
    public static class Field {
        private String id;
    }
}