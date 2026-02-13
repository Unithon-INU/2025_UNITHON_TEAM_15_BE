package com.unithon.team15_server.global.university.service;

import com.unithon.team15_server.global.university.dto.UniversityRes;
import com.unithon.team15_server.global.university.dto.UniversityMajorsRes;
import com.unithon.team15_server.global.university.entity.University;
import com.unithon.team15_server.global.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;

    public List<UniversityRes> searchByUniversity(String university) {
        List<University> results = universityRepository
                .findByUniversityContaining(university);

        return results.stream()
                .collect(Collectors.groupingBy(
                        u -> u.getUniversity() + "|" + u.getUniversityType()))
                .values().stream()
                .map(group -> {
                    University first = group.get(0);
                    return UniversityRes.builder()
                            .university(first.getUniversity())
                            .universityType(first.getUniversityType())
                            .majors(group.stream()
                                    .map(u -> UniversityMajorsRes.builder()
                                            .major(u.getMajor())
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
}