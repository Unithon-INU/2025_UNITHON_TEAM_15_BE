package com.unithon.team15_server.domain.employmentcheck;

import com.unithon.team15_server.domain.employmentcheck.dto.HomeInfoRes;
import com.unithon.team15_server.domain.employmentcheck.dto.TipInfoRes;
import com.unithon.team15_server.global.jwt.MemberDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employment")
@RequiredArgsConstructor
public class EmploymentCheckController {
    private final EmploymentCheckService employmentCheckService;

    @GetMapping("/home")
    public ResponseEntity<HomeInfoRes> getHomeInfo(@AuthenticationPrincipal MemberDetail memberDetail) {
        return ResponseEntity.ok(employmentCheckService.getHomeInfo(memberDetail.getId()));
    }

    @GetMapping("/steps")
    public void getSteps(@AuthenticationPrincipal MemberDetail memberDetail) {

    }

    @GetMapping("/tips")
    public ResponseEntity<List<TipInfoRes>> getTips(CheckStep checkStep) {
        return ResponseEntity.ok(employmentCheckService.getTipInfo(checkStep));
    }
}
