package com.unithon.team15_server.global.university.implement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unithon.team15_server.global.exception.CustomException;
import com.unithon.team15_server.global.exception.ErrorCode;
import com.unithon.team15_server.global.university.entity.AccreditedUniversity;
import com.unithon.team15_server.global.university.repository.AccreditedUniversityRepository;
import com.unithon.team15_server.global.university.dto.AccreditedUniversityDto;
import com.unithon.team15_server.global.university.enums.ProgramType;
import com.unithon.team15_server.global.university.enums.UniversityType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UniversityDataLoader implements CommandLineRunner {
    @Value("${data-path.university.accredited}")
    private String accreditedUniPath;
    private final AccreditedUniversityRepository accreditedUniversityRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws IOException {
        if (accreditedUniversityRepository.count() > 0) { // 이미 데이터 존재
            return;
        }

        List<AccreditedUniversity> universities = new ArrayList<>();
        try {
            File file = new File(accreditedUniPath);

            if (!file.exists()) {
                log.error("[로그] 파일을 찾을 수 없습니다: {}", accreditedUniPath);
                return;
            }

            AccreditedUniversityDto accreditedUniversity = objectMapper.readValue(file, AccreditedUniversityDto.class);
            //D-2 인증대 정보 (일반대학)
            universities.addAll(addAccreditedUniversity(accreditedUniversity.getDegree().getBachelors(), ProgramType.DEGREE, UniversityType.BACHELOR));
            universities.addAll(addAccreditedUniversity(accreditedUniversity.getDegree().getAssociate(), ProgramType.DEGREE, UniversityType.ASSOCIATE));
            universities.addAll(addAccreditedUniversity(accreditedUniversity.getDegree().getGraduate(), ProgramType.DEGREE, UniversityType.GRADUATE));
            //D-4 인증대 정보 (어학연수)
            universities.addAll(addAccreditedUniversity(accreditedUniversity.getLanguage().getBachelors(), ProgramType.LANGUAGE, UniversityType.BACHELOR));
            universities.addAll(addAccreditedUniversity(accreditedUniversity.getLanguage().getAssociate(), ProgramType.LANGUAGE, UniversityType.ASSOCIATE));
            universities.addAll(addAccreditedUniversity(accreditedUniversity.getLanguage().getGraduate(), ProgramType.LANGUAGE, UniversityType.GRADUATE));

            accreditedUniversityRepository.saveAll(universities);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(ErrorCode.ACCREDITED_UNI_FILE_NOT_FOUND);
        }
    }

    private List<AccreditedUniversity> addAccreditedUniversity(List<String> universities, ProgramType programType, UniversityType universityType) {
        return universities.stream().map(
                university -> AccreditedUniversity.create(university, programType, universityType)
        ).toList();
    }
}
