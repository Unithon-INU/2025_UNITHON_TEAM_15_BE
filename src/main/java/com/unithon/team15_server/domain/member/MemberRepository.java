package com.unithon.team15_server.domain.member;

import com.unithon.team15_server.domain.member.dto.MemberProfileGetRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.email = :email")
    boolean existsByEmail(String email);

    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.nickname = :nickname")
    boolean existsByNickname(String nickname);

    @Query("""
            SELECT new com.unithon.team15_server.domain.member.dto.MemberProfileGetRes(
                m.language,
                m.topikLevel,
                m.visaType,
                m.industry
            )
            FROM Member m
            WHERE m.id = :id
            """)
    MemberProfileGetRes findMemberProfileById(Long id);

}
