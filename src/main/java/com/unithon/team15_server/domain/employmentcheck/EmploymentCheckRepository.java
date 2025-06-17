package com.unithon.team15_server.domain.employmentcheck;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentCheckRepository extends JpaRepository<EmploymentCheck, Long> {
    int countByMemberIdAndIsCheckedTrue(Long memberId);

    List<EmploymentCheck> findAllByMemberIdAndCheckStep(Long memberId, CheckStep checkStep);

    List<EmploymentCheck> findAllByMemberId(Long memberId);
}
