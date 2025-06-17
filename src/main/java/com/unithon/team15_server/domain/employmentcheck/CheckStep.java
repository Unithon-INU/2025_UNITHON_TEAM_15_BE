package com.unithon.team15_server.domain.employmentcheck;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CheckStep {
    STEP1(1, 4, 4),
    STEP2(2, 3, 4),
    STEP3(3, 3, 4);

    private final int inx;
    private final int documentTotal;
    private final int precautionTotal;
}