package com.unithon.team15_server.global.translate;

import com.unithon.team15_server.global.translate.dto.TranslateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Translator {
    @Value("${key.deepl-api}")
    private String key;
    private final TranslateClient translateClient;

    public String translateToKorean(String text) {
        return translateClient.translate(key, new TranslateReq(List.of(text), "KO")).getTranslations().get(0).getText();
    }

    public String translateToEnglish(String text) {
        return translateClient.translate(key, new TranslateReq(List.of(text), "EN")).getTranslations().get(0).getText();
    }
}
