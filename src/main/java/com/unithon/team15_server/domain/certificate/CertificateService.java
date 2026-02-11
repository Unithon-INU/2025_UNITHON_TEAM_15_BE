package com.unithon.team15_server.domain.certificate;

import com.lowagie.text.DocumentException;
import com.unithon.team15_server.domain.certificate.dto.CertificateReq;
import com.unithon.team15_server.domain.certificate.dto.WorkingTimeLimitReq;
import com.unithon.team15_server.domain.certificate.dto.WorkingTimeLimitRes;
import com.unithon.team15_server.domain.certificate.enums.UniversityYear;
import com.unithon.team15_server.domain.certificate.enums.WorkingTimeLimit;
import com.unithon.team15_server.domain.certificate.implement.EmailSender;
import com.unithon.team15_server.domain.certificate.implement.PdfGenerator;
import com.unithon.team15_server.domain.member.Member;
import com.unithon.team15_server.domain.member.MemberRepository;
import com.unithon.team15_server.global.exception.CustomException;
import com.unithon.team15_server.global.exception.ErrorCode;
import com.unithon.team15_server.global.university.AccreditedUniversityRepository;
import com.unithon.team15_server.global.university.enums.ProgramType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final PdfGenerator pdfGenerator;
    private final EmailSender emailSender;
    private final MemberRepository memberRepository;
    private final AccreditedUniversityRepository accreditedUniversityRepository;

    public void sendCert(CertificateReq certificateReq) throws DocumentException, IOException, MessagingException {
        String pdf = pdfGenerator.parseHtmlToString(certificateReq);
        emailSender.snedEmail(certificateReq.getEmail(), pdfGenerator.generatePdf(pdf));
    }

    public WorkingTimeLimitRes getWorkingTimeLimit(Long memberId, WorkingTimeLimitReq workingTimeLimitReq) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );

        WorkingTimeLimit workingTimeLimit = calculateWorkingTime(member.getVisaType(), member.getLanguageLevel(), workingTimeLimitReq.getYear(), isAccreditedUniversity(workingTimeLimitReq.getUniversity(), member.getVisaType()));

        return WorkingTimeLimitRes.builder()
                .weeklyHours(workingTimeLimit.getWeeklyHours())
                .weekdayHours(workingTimeLimit.getWeekdayHours())
                .build();
    }

    private WorkingTimeLimit calculateWorkingTime(String visaType, String languageLevel, UniversityYear universityYear, boolean isCertifiedUniversity) {
        WorkingTimeLimit workingTimeLimit = null;

        switch (visaType) {
            case "D-4" -> {
                if (languageLevel.contains("없음")) {
                    workingTimeLimit = WorkingTimeLimit.D4_NO_TOPIK;
                    // 주말 + 주중 10시간
                } else {
                    if (isCertifiedUniversity) { //인증대학
                        workingTimeLimit = WorkingTimeLimit.D4_CERTIFIED_TOPIK;
                        // 주중 25, 주말및방학 무제한
                    } else {
                        workingTimeLimit = WorkingTimeLimit.D4_TOPIK_2;
                        // 주중 20, 주말및방학 무제한
                    }
                }
            }

            case "D-2" -> {
                switch (universityYear) {
                    case ASSOCIATE -> {
                        if (languageLevel.contains("없음")) {
                            workingTimeLimit = WorkingTimeLimit.D2_ASSOCIATE_NO_TOPIK;
                        } else {
                            if (isCertifiedUniversity) { //인증대학
                                workingTimeLimit = WorkingTimeLimit.D2_ASSOCIATE_CERTIFIED_TOPIK;
                            } else { //토픽만
                                workingTimeLimit = WorkingTimeLimit.D2_ASSOCIATE_TOPIK_3;
                            }
                        }
                    }

                    case BACHELOR_1, BACHELOR_2 -> {
                        if (languageLevel.contains("없음")) {
                            workingTimeLimit = WorkingTimeLimit.D2_BACHELOR_12_NO_TOPIK;
                        } else {
                            if (isCertifiedUniversity) { //인증대학
                                workingTimeLimit = WorkingTimeLimit.D2_BACHELOR_12_CERTIFIED_TOPIK;
                            } else { //토픽만
                                workingTimeLimit = WorkingTimeLimit.D2_BACHELOR_12_TOPIK_3;
                            }
                        }
                    }

                    case BACHELOR_3, BACHELOR_4 -> {
                        if (languageLevel.contains("없음")) {
                            workingTimeLimit = WorkingTimeLimit.D2_BACHELOR_34_NO_TOPIK;
                        } else {
                            if (isCertifiedUniversity) { //인증대학
                                workingTimeLimit = WorkingTimeLimit.D2_BACHELOR_34_CERTIFIED_TOPIK;
                            } else { //토픽만
                                workingTimeLimit = WorkingTimeLimit.D2_BACHELOR_34_TOPIK_4;
                            }
                        }
                    }

                    case GRADUATE -> {
                        if (languageLevel.contains("없음")) {
                            workingTimeLimit = WorkingTimeLimit.D2_GRADUATE_NO_TOPIK;
                        } else {
                            if (isCertifiedUniversity) { //인증대학
                                workingTimeLimit = WorkingTimeLimit.D2_GRADUATE_CERTIFIED_TOPIK;
                            } else { //토픽만
                                workingTimeLimit = WorkingTimeLimit.D2_GRADUATE_TOPIK_4;
                            }
                        }
                    }
                    default -> throw new IllegalArgumentException("지원하지 않는 언어 능력 타입: " + languageLevel);
                }
            }
            default -> throw new IllegalArgumentException("지원하지 않는 비자 타입: " + visaType);
        }
        return workingTimeLimit;
    }

    private boolean isAccreditedUniversity(String universityName, String visaType) {
        // 인증대학 여부 확인
        // true -> 인증대 O, false -> 인증대 X
        switch (visaType) {
            case "D-2" -> {
                return accreditedUniversityRepository.existsByNameAndProgramType(universityName, ProgramType.DEGREE);
            }
            case "D-4" -> {
                return accreditedUniversityRepository.existsByNameAndProgramType(universityName, ProgramType.LANGUAGE);
            }
            default -> throw new CustomException(ErrorCode.INVALID_AUTH_CODE);
        }
    }
}
