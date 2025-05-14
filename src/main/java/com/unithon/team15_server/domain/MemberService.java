package com.unithon.team15_server.domain;

import com.unithon.team15_server.domain.dto.MemberNicknameReq;
import com.unithon.team15_server.domain.dto.MemberProfileReq;
import com.unithon.team15_server.domain.dto.MemberSignInReq;
import com.unithon.team15_server.domain.dto.MemberSignupReq;
import com.unithon.team15_server.domain.enums.MemberRole;
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

    @Transactional
    public String signup(MemberSignupReq memberSignupReq) {
        if (memberRepository.existsByEmail(memberSignupReq.getEmail())) {
            throw new CustomException(ErrorCode.USER_ALREADY_REGISTERED);
        }
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

    @Transactional
    public void registerProfile(Long memberId, MemberProfileReq memberProfileReq) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        member.updateProfile(memberProfileReq.getLanguage(), memberProfileReq.getLanguageLevel(), memberProfileReq.getVisaType(), memberProfileReq.getIndustry());
        member.updateMemberRole(MemberRole.USER); //MemberRole == USER
    }


    public String signin(MemberSignInReq memberSignInReq){
        Member member = memberRepository.findByEmail(memberSignInReq.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        if (!passwordProcessor.matches(memberSignInReq.getPassword(), member.getPassword())){
           throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        return getToken(member.getEmail());
    }

    private String getToken(String email){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return jwtProvider.generateToken(authentication);
    }

}
