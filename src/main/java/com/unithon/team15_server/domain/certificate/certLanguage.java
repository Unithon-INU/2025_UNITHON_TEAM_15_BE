package com.unithon.team15_server.domain.certificate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum certLanguage {
    KOREAN("_ko"), ENGLISH("_eng");

    private final String suffix;
}
