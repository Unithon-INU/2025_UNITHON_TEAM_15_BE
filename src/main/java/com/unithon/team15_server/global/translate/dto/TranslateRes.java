package com.unithon.team15_server.global.translate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TranslateRes {

    private List<TranslateBody> translations;

    @Getter
    @NoArgsConstructor
    public static class TranslateBody {
        private String detected_source_language;
        private String text;
    }
}
