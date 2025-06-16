package com.unithon.team15_server.domain.member;

import com.unithon.team15_server.domain.employmentcheck.EmploymentCheckService;
import com.unithon.team15_server.domain.member.dto.*;
import com.unithon.team15_server.domain.member.enums.MemberRole;
import com.unithon.team15_server.global.exception.CustomException;
import com.unithon.team15_server.global.exception.ErrorCode;
import com.unithon.team15_server.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordProcessor passwordProcessor;
    private final EmploymentCheckService employmentCheckService;

    @Transactional
    public String signup(MemberSignupReq memberSignupReq) {
        Member member = Member.create(memberSignupReq, passwordProcessor); //MemberRole == GUEST
        memberRepository.save(member);

        return getToken(member.getEmail());
    }

    @Transactional
    public void registerNickname(Long memberId, MemberNicknameReq memberNicknameReq) {
        if (memberRepository.existsByNickname(memberNicknameReq.getNickname())) {
            throw new CustomException(ErrorCode.NICKNAME_ALREADY_REGISTERED);
        }

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        member.updateNickname(memberNicknameReq.getNickname());
    }

    //진짜 회원
    @Transactional
    public String registerProfile(Long memberId, MemberProfileSetReq memberProfileSetReq) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        member.updateProfile(memberProfileSetReq.getLanguage(), memberProfileSetReq.getTopikLevel(), memberProfileSetReq.getVisaType(), memberProfileSetReq.getIndustry());
        member.updateMemberRole(MemberRole.USER);
        employmentCheckService.createEmploymentCheck(memberId); //회원가입 성공시 해당 회원에 대한 checklist 전체 생성
        return getToken(member.getEmail());
    }


    public String signin(MemberSignInReq memberSignInReq) {
        Member member = memberRepository.findByEmail(memberSignInReq.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        if (!passwordProcessor.matches(memberSignInReq.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        return getToken(member.getEmail());
    }

    public MemberProfileGetRes getMemberProfile(Long memberId) {
        return memberRepository.findMemberProfileById(memberId);
    }

    private String getToken(String email) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return jwtProvider.generateToken(authentication);
    }

}
