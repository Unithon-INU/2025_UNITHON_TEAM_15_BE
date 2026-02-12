package com.unithon.team15_server.global.university.controller;

import com.unithon.team15_server.global.university.service.UniversityService;
import com.unithon.team15_server.global.university.dto.UniversityRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
@RequiredArgsConstructor
@Tag(name = "Universities", description = "대학교 API")
public class UniversityController {
    private final UniversityService universityService;

    @Operation(summary = "대학교 정보 조회")
    @ApiResponse(responseCode = "200", description = "대학교 및 학과 리스트 조회")
    @GetMapping
    public ResponseEntity<List<UniversityRes>> searchUniversity(@RequestParam String university) {
        return ResponseEntity.ok(universityService.searchByUniversity(university));
    }
}
