package com.unithon.team15_server.global.university;

import com.unithon.team15_server.global.university.dto.UniApi.UniversityApiWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UniversityClient", url = "http://api.data.go.kr/openapi/tn_pubr_public_univ_major_api")
public interface UniversityClient {

    @GetMapping(produces = "application/json")
    UniversityApiWrapper getUniversityMajors(@RequestParam("serviceKey") String serviceKey, @RequestParam("pageNo") String pageNo, @RequestParam("numOfRows") String numOfRows, @RequestParam("type") String type, @RequestParam("SCHL_NM") String university);
}
