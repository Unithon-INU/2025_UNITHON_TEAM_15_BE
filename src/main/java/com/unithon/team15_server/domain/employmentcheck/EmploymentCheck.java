package com.unithon.team15_server.domain.employmentcheck;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "employment_check_tb")
public class EmploymentCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @Enumerated(EnumType.STRING)
    private CheckStep checkStep;

    //제출서류 인덱스
    private int submissionIdx;

    private boolean isChecked;

    @Builder
    private EmploymentCheck(Long memberId, CheckStep checkStep, int submissionIdx, boolean isChecked) {
        this.memberId = memberId;
        this.checkStep = checkStep;
        this.submissionIdx = submissionIdx;
        this.isChecked = isChecked;
    }

    public static EmploymentCheck create(Long memberId, CheckStep checkStep, int submissionIdx) {
        return EmploymentCheck.builder()
                .memberId(memberId)
                .checkStep(checkStep)
                .submissionIdx(submissionIdx)
                .isChecked(false)
                .build();
    }
}
