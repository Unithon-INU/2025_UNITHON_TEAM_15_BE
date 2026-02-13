package com.unithon.team15_server.global.university.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unithon.team15_server.global.university.entity.University;
import com.unithon.team15_server.global.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
@Slf4j
public class UniversityDataInitializer implements CommandLineRunner {

    private final UniversityRepository universityRepository;
    private final UniversityTypeConverter universityTypeConverter;
    private final ObjectMapper objectMapper;

    @Value("${data-path.university.major}")
    private String dataPath;

    @Override
    public void run(String... args) throws Exception {
        if (universityRepository.count() > 0) {
            return;
        }

        File file = new File(dataPath);
        if (!file.exists()) {
            log.warn("파일을 찾을 수 없습니다: {}", dataPath);
            return;
        }

        UniversityJsonWrapper wrapper = objectMapper.readValue(file, UniversityJsonWrapper.class);

        var entities = wrapper.getRecords().stream()
                .filter(record -> !"폐과".equals(record.getMajorStatus()))
                .filter(record -> record.getMajor() == null || !record.getMajor().contains("기타"))
                .map(record -> University.builder()
                        .university(record.getUniversity())
                        .universityType(universityTypeConverter.convert(record.getUniversityType()))
                        .major(record.getMajor())
                        .studyPeriod(cleanStudyPeriod(record.getStudyPeriod()))
                        .build())
                .toList();

        universityRepository.saveAll(entities);
        log.info("Loaded {} university majors", entities.size());
    }

    private String cleanStudyPeriod(String studyPeriod) {
        if (studyPeriod == null) return null;
        return studyPeriod.replaceAll("\\(.*\\)", "").trim();
    }
}