package com.unithon.team15_server.global.translate.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class TranslateReq {

    private final List<String> text;
    private final String target_lang;
}
