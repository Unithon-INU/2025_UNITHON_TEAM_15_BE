package com.unithon.team15_server.global.verification;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "email_tb")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String code;

    private LocalDateTime createAt;

    private LocalDateTime expiredAt;

    @Builder
    private Email(String email, String code, LocalDateTime createAt, LocalDateTime expiredAt) {
        this.email = email;
        this.code = code;
        this.createAt = createAt;
        this.expiredAt = expiredAt;
    }

    public static Email create(String email, String code) {
        LocalDateTime now = LocalDateTime.now();
        return Email.builder()
                .email(email)
                .code(code)
                .createAt(now)
                .expiredAt(now.plusMinutes(10))
                .build();
    }

    public void updateInfo(String code) {
        LocalDateTime now = LocalDateTime.now();
        this.code = code;
        this.createAt = now;
        this.expiredAt = now.plusMinutes(10);
    }
}
