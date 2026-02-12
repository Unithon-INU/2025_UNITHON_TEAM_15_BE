package com.unithon.team15_server.global.university.loader;

import com.unithon.team15_server.global.university.enums.UniversityType;
import org.springframework.stereotype.Component;

@Component
public class UniversityTypeConverter {

    public UniversityType convert(String value) {
        return switch (value) {
            case "전문학사" -> UniversityType.ASSOCIATE;
            case "학사" -> UniversityType.BACHELOR;
            default -> UniversityType.GRADUATE;
        };
    }
}