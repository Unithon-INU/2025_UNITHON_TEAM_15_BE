package com.unithon.team15_server.global.university.service;

import com.unithon.team15_server.domain.member.Member;
import com.unithon.team15_server.domain.member.MemberRepository;
import com.unithon.team15_server.global.exception.CustomException;
import com.unithon.team15_server.global.exception.ErrorCode;
import com.unithon.team15_server.global.university.dto.AccreditedUniversityRes;
import com.unithon.team15_server.global.university.dto.AccreditedUniversityRes.UniversityInfo;
import com.unithon.team15_server.global.university.entity.AccreditedUniversity;
import com.unithon.team15_server.global.university.enums.ProgramType;
import com.unithon.team15_server.global.university.repository.AccreditedUniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccreditedUniversityService {
    private final AccreditedUniversityRepository accreditedUniversityRepository;
    private final MemberRepository memberRepository;

    public AccreditedUniversityRes getAccreditedUniversity(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        ProgramType programType = switch (member.getVisaType()) {
            case "D-2" -> ProgramType.DEGREE; //D-2: 학사
            case "D-4" -> ProgramType.LANGUAGE; //D-4: 어학연수
            default -> throw new CustomException(ErrorCode.USER_NOT_FOUND);
        };
        List<AccreditedUniversity> accreditedUniversities = accreditedUniversityRepository.getAccreditedUniversitiesByProgramType(programType);

        List<UniversityInfo> universityInfos = new ArrayList<>();
        for (AccreditedUniversity accreditedUniversity : accreditedUniversities) {
            universityInfos.add(new UniversityInfo(accreditedUniversity.getUniversity(), accreditedUniversity.getUniversityEng(), accreditedUniversity.getUniversityType()));
        }

        return new AccreditedUniversityRes(universityInfos);
    }
}
