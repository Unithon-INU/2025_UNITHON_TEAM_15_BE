package com.unithon.team15_server.global.university;

import com.unithon.team15_server.global.university.dto.UniApi.UniversityApiMajorItem;
import com.unithon.team15_server.global.university.dto.UniApi.UniversityApiWrapper;
import com.unithon.team15_server.global.university.dto.UniversityMajorRes;
import com.unithon.team15_server.global.university.dto.UniversityRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityClient universityClient;
    @Value("${key.public-data}")
    private String serviceKey;
    private final String TYPE = "json";
    private final String PAGE_NO = "1";
    private final String NUM_OF_ROWS = "1000";

    public List<UniversityRes> getUniversityMajors(String universityName) {
        return searchUniversityMajors(serviceKey, PAGE_NO, NUM_OF_ROWS, TYPE, universityName);
    }

    private List<UniversityRes> searchUniversityMajors(String serviceKey, String pageNo, String numOfRows, String type, String university) {
        List<UniversityRes> universityResList = new ArrayList<>();

        UniversityApiWrapper universityApiResponse = universityClient.getUniversityMajors(serviceKey, pageNo, numOfRows, type, university);
        List<UniversityApiMajorItem> majorList = universityApiResponse.getUniversityApiResponse().getBody().getItems().isEmpty() ? null : universityApiResponse.getUniversityApiResponse().getBody().getItems();

        String universityName = majorList.get(0).getSchlNm();
        List<UniversityMajorRes> universityMajorResList = majorList.stream().filter(item -> !"폐과".equals(item.getScsbjtSttsNm())).collect(Collectors.toMap(
                UniversityApiMajorItem::getScsbjtNm,
                item -> UniversityMajorRes.builder()
                        .major(item.getScsbjtNm())
                        .lssnTerm(item.getLssnTerm())
                        .build(),
                (existing, replacement) -> existing // 학과 중복 방지를 위해 map 사용
        )).values().stream().toList();

        universityResList.add(UniversityRes.builder()
                .university(universityName)
                .majors(universityMajorResList)
                .build());

        return universityResList;
    }
}