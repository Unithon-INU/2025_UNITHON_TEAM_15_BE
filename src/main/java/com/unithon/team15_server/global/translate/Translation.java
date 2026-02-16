package com.unithon.team15_server.global.translate;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("translation")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Translation {

    @Id
    private final String id;

    private final String eng;

    public static Translation ofUniversity(String kor, String eng) {
        return new Translation("university:" + kor, eng);
    }

    public static Translation ofMajor(String kor, String eng) {
        return new Translation("major:" + kor, eng);
    }
}
