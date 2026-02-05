package com.unithon.team15_server.domain.certificate.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkingTimeLimit {

    // D-4
    D4_NO_TOPIK(10, null, "토픽 없음"),
    D4_TOPIK_2(20, null, "토픽 2급 이상"),
    D4_CERTIFIED_TOPIK(25, null, "인증대 + 토픽"),

    // D-2 전문대
    D2_ASSOCIATE_NO_TOPIK(10, null, "토픽 없음"),
    D2_ASSOCIATE_TOPIK_3(null, 20, "토픽 3급 이상"),
    D2_ASSOCIATE_CERTIFIED_TOPIK(null, 25, "인증대 + 토픽"),

    // D-2 학사 1~2학년
    D2_BACHELOR_12_NO_TOPIK(10, null, "토픽 없음"),
    D2_BACHELOR_12_TOPIK_3(null, 20, "토픽 3급 이상"),
    D2_BACHELOR_12_CERTIFIED_TOPIK(null, 25, "인증대 + 토픽"),

    // D-2 학사 3~4학년
    D2_BACHELOR_34_NO_TOPIK(10, null, "토픽 없음"),
    D2_BACHELOR_34_TOPIK_4(null, 20, "토픽 4급 이상"),
    D2_BACHELOR_34_CERTIFIED_TOPIK(null, 25, "인증대 + 토픽"),

    // D-2 석박사
    D2_GRADUATE_NO_TOPIK(15, null, "토픽 없음"),
    D2_GRADUATE_TOPIK_4(null, 30, "토픽 4급 이상, 주중"),
    D2_GRADUATE_CERTIFIED_TOPIK(null, 35, "인증대 + 토픽");

    private final Integer weeklyHours;      // 주당 시간 (null == 주중과 주말 근무시간 각자 다름)
    private final Integer weekdayHours;     // 주중 시간
    private final String description;
}
