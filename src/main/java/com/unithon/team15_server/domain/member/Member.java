package com.unithon.team15_server.domain.member;

import com.unithon.team15_server.domain.member.dto.MemberSignupReq;
import com.unithon.team15_server.domain.member.enums.MemberRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_tb")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String language;

    private String languageLevel;

    private String visaType;

    private String industry;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Builder
    private Member(String nickname, String email, String password, String language, String languageLevel, String visaType, String industry, MemberRole memberRole) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.language = language;
        this.languageLevel = languageLevel;
        this.visaType = visaType;
        this.industry = industry;
        this.memberRole = memberRole;
    }

    public static Member create(MemberSignupReq memberSignupReq, PasswordProcessor passwordProcessor) {
        return Member.builder()
                .nickname("")
                .email(memberSignupReq.getEmail())
                .password(passwordProcessor.encode(memberSignupReq.getPassword()))
                .language("")
                .languageLevel("")
                .visaType("")
                .industry("")
                .memberRole(MemberRole.GUEST)
                .build();
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    public void updateProfile(String language, String languageLevel, String visaType, String industry) {
        this.language = language;
        this.languageLevel = languageLevel;
        this.visaType = visaType;
        this.industry = industry;
    }
}
