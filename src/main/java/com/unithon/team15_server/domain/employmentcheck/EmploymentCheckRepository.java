package com.unithon.team15_server.domain.employmentcheck;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmploymentCheckRepository extends JpaRepository<EmploymentCheck, Long> {
    int countByMemberIdAndIsCheckedTrue(Long memberId);

    List<EmploymentCheck> findAllByMemberIdAndCheckStep(Long memberId, CheckStep checkStep);

    Optional<EmploymentCheck> findByMemberIdAndCheckStepAndSubmissionIdx(Long memberId, CheckStep checkStep, int submissionIdx);

    boolean existsByMemberId(Long memberId);
}
