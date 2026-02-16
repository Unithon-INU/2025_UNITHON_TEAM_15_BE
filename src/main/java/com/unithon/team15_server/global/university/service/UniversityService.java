package com.unithon.team15_server.global.university.service;

import com.unithon.team15_server.global.translate.TranslateRedisRepository;
import com.unithon.team15_server.global.translate.Translation;
import com.unithon.team15_server.global.translate.Translator;
import com.unithon.team15_server.global.university.dto.UniversityMajorsRes;
import com.unithon.team15_server.global.university.dto.UniversityRes;
import com.unithon.team15_server.global.university.entity.University;
import com.unithon.team15_server.global.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final TranslateRedisRepository translateRedisRepository;
    private final Translator translator;

    public List<UniversityRes> searchByUniversity(String university) {
        String lang = LocaleContextHolder.getLocale().getLanguage();
        final boolean isEnglish = lang.equals("en");

        if (isEnglish) {
            university = translator.translateToKorean(university);
        }


        List<University> results = universityRepository
                .findByUniversityContaining(university);

        return results.stream()
                .collect(Collectors.groupingBy(
                        u -> u.getUniversity() + "|" + u.getUniversityType()))
                .values().stream()
                .map(group -> {
                    University first = group.get(0);
                    return UniversityRes.builder()
                            .university(isEnglish ? getUniversityTranslation(first.getUniversity()) : first.getUniversity())
                            .universityType(first.getUniversityType())
                            .majors(group.stream()
                                    .map(u -> UniversityMajorsRes.builder()
                                            .major(isEnglish ? getMajorTranslation(u.getMajor()) : u.getMajor())
                                            .studyPeriod(u.getStudyPeriod())
                                            .build())
                                    .toList())
                            .build();
                })
                .sorted(Comparator
                        .comparing(UniversityRes::getUniversityType)
                        .thenComparing(UniversityRes::getUniversity))
                .toList();
    }

    private String getUniversityTranslation(String kor) {
        return translateRedisRepository.findById("university:" + kor)
                .map(Translation::getEng)
                .orElseGet(
                        () -> {
                            String eng = translator.translateToEnglish(kor);
                            translateRedisRepository.save(Translation.ofUniversity(kor, eng));
                            return eng;
                        }
                );
    }

    private String getMajorTranslation(String kor) {
        return translateRedisRepository.findById("major:" + kor)
                .map(Translation::getEng)
                .orElseGet(
                        () -> {
                            String eng = translator.translateToEnglish(kor);
                            translateRedisRepository.save(Translation.ofMajor(kor, eng));
                            return eng;
                        }
                );
    }
}