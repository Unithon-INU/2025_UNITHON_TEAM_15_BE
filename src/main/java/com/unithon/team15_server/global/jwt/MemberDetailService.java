package com.unithon.team15_server.global.jwt;

import com.unithon.team15_server.domain.Member;
import com.unithon.team15_server.domain.MemberRepository;
import com.unithon.team15_server.global.exception.CustomException;
import com.unithon.team15_server.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public MemberDetail loadUserByEmail(String email) throws UsernameNotFoundException {
        log.info("MemberDetailsService : {}", email);
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        return new MemberDetail(member);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
