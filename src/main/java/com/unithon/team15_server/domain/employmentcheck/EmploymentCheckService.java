package com.unithon.team15_server.domain.employmentcheck;

import com.unithon.team15_server.domain.employmentcheck.dto.*;
import com.unithon.team15_server.domain.member.Member;
import com.unithon.team15_server.domain.member.MemberRepository;
import com.unithon.team15_server.global.exception.CustomException;
import com.unithon.team15_server.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmploymentCheckService {
    private final EmploymentCheckRepository employmentCheckRepository;
    private final MemberRepository memberRepository;
    private final MessageSource messageSource;
    private final static int TOTAL = 10; //TODO 총 제출 서류 바뀌면 수정하기

    @Transactional
    public void createEmploymentCheck(Long memberId) {
        List<EmploymentCheck> employmentChecks = new ArrayList<>();
        for (CheckStep checkStep : CheckStep.values()) {
            for (int i = 0; i < checkStep.getDocumentTotal(); i++) {
                employmentChecks.add(EmploymentCheck.create(memberId, checkStep, i));
            }
        }

        employmentCheckRepository.saveAll(employmentChecks);
    }

    public HomeInfoRes getHomeInfo(Long memberId) { // 모든 값 다 전달하기
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        //진행률 계산
        CheckStep memberCheckStep = member.getCheckStep();

        List<EmploymentCheckRes> employmentCheckRes = new ArrayList<>();
        for (CheckStep checkStep : CheckStep.values()) { //모든 step에 대한 값 가져오기
            //step 정보
            StepInfoRes stepInfoRes = getStepInfo(checkStep);

            //제출서류 정보
            List<EmploymentCheck> employmentChecks = employmentCheckRepository.findAllByMemberIdAndCheckStep(memberId, checkStep);
            List<DocumentInfoRes> documentInfoRes = new ArrayList<>();
            for (int i = 0; i < employmentChecks.size(); i++) {
                documentInfoRes.add(DocumentInfoRes.builder()
                        .submissionIdx(i)
                        .title(messageSource.getMessage("main.step." + checkStep.getInx() + ".submissionDocument." + i, null, Locale.KOREAN))
                        .isChecked(employmentChecks.get(i).getIsChecked())
                        .build());
            }
            employmentCheckRes.add(new EmploymentCheckRes(checkStep, stepInfoRes, documentInfoRes));
        }

        return HomeInfoRes.builder()
                .nickname(member.getNickname())
                .progress(calculateProgress(memberId)) // progress 계산해서 넣기
                .memberCheckStep(memberCheckStep)
                .employmentCheckRes(employmentCheckRes)
                .build();
    }

    private StepInfoRes getStepInfo(CheckStep checkStep) {
        int checkStepInx = checkStep.getInx();
        String prefix = "main.step." + checkStepInx;
        System.out.println("prefix: " + prefix);
        List<String> precautions = new ArrayList<>();
        for (int i = 0; i < checkStep.getPrecautionTotal(); i++) {
            precautions.add(messageSource.getMessage(prefix + ".precautions." + i, null, Locale.KOREAN));
            System.out.println(messageSource.getMessage(prefix + ".precautions." + i, null, Locale.KOREAN));
        }

        return StepInfoRes.builder()
                .title(messageSource.getMessage(prefix + ".title", null, Locale.KOREAN))
                .subtitle(messageSource.getMessage(prefix + ".subtitle", null, Locale.KOREAN))
                .precautions(precautions)
                .build();
    }

    public List<TipInfoRes> getTipInfo(CheckStep checkStep) {
        int checkStepInx = checkStep.getInx();
        List<TipInfoRes> tipInfoRes = new ArrayList<>();

        int i = 0;
        while (true) {
            try {
                String prefix = "main.step." + checkStepInx + ".tip." + i;
                String title = messageSource.getMessage(prefix + ".title", null, Locale.KOREAN);
                List<TipInfoDetailRes> tipInfoDetailRes = new ArrayList<>();

                int j = 0;
                while (true) {
                    try {

                        String itemContent = messageSource.getMessage(prefix + ".item.content." + j, null, Locale.KOREAN);
                        System.out.println("itemContent:" + itemContent);

                        String itemTitle = null;
                        try {
                            itemTitle = messageSource.getMessage(prefix + ".item.title." + j, null, Locale.KOREAN);
                        } catch (NoSuchMessageException e) {
                            itemTitle = ""; // item의 title이 없음
                        }

                        String warning = null;
                        try {
                            warning = messageSource.getMessage(prefix + ".warning." + j, null, Locale.KOREAN);
                        } catch (NoSuchMessageException e) {
                            warning = "";
                        }
                        tipInfoDetailRes.add(new TipInfoDetailRes(itemTitle, itemContent, warning));
                        j++;

                    } catch (NoSuchMessageException e) {
                        break; // item에 대한 값 모두 없음
                    }
                }
                tipInfoRes.add(new TipInfoRes(title, tipInfoDetailRes));
                i++;
            } catch (NoSuchMessageException e) {
                break; // tip 없음
            }
        }
        return tipInfoRes;
    }

    @Transactional
    public UpdateEmplCheckRes updateEmploymentCheck(Long memberId, UpdateEmplCheckReq updateEmplCheckReq) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        member.updateCheckStep(updateEmplCheckReq.getCheckStep()); // 현재 업데이트 하는 곳 == 현재 회원이 진행하는 곳

        EmploymentCheck employmentCheck = employmentCheckRepository.findByCheckStepAndSubmissionIdx(updateEmplCheckReq.getCheckStep(), updateEmplCheckReq.getSubmissionIdx()).orElseThrow(
                () -> new CustomException(ErrorCode.EMPLOY_CHECK_NOT_FOUND)
        );
        employmentCheck.toggleIsChecked();

        return new UpdateEmplCheckRes(calculateProgress(memberId));
    }

    private int calculateProgress(Long memberId) {
        int count = employmentCheckRepository.countByMemberIdAndIsCheckedTrue(memberId);
        return Math.round((float) count / TOTAL * 100);
    }
}
