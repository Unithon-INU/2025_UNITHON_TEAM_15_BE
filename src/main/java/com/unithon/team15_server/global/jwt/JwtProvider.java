package com.unithon.team15_server.global.jwt;

import com.unithon.team15_server.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProvider {
    private final MemberDetailService memberDetailService;
    private final Key key;
    public static final long VALID_TIME = 2L * 365 * 24 * 60 * 60 * 1000; // 2년 (이후에 3달로 바꿀 예정)

    private JwtProvider(@Value("${spring.jwt.secret}") String secretKey, MemberDetailService memberDetailService) {
        byte[] ketBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(ketBytes);
        this.memberDetailService = memberDetailService;
    }

    public String generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());
        Member member = ((MemberDetail) authentication.getPrincipal()).getMember();
        long now = (new Date()).getTime();

        return Jwts.builder()
                .setSubject(member.getEmail())
                .claim("auth", authorities)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + VALID_TIME))
                .signWith(key)
                .compact();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(""))
                .map(SimpleGrantedAuthority::new)
                .toList();

        MemberDetail memberDetail = memberDetailService.loadUserByEmail(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(memberDetail, "", authorities);
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
