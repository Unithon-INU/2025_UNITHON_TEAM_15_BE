package com.unithon.team15_server.domain.employmentcheck;

import com.unithon.team15_server.domain.employmentcheck.dto.HomeInfoRes;
import com.unithon.team15_server.domain.employmentcheck.dto.TipInfoRes;
import com.unithon.team15_server.domain.employmentcheck.dto.UpdateEmplCheckReq;
import com.unithon.team15_server.global.jwt.MemberDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employment")
@RequiredArgsConstructor
@Tag(name = "Employmenr Check", description = "시간제취업확인")
public class EmploymentCheckController {
    private final EmploymentCheckService employmentCheckService;

    @Operation(summary = "내 시간제취업 정보 전체 조회", description = "- 모든 step에 대한 체크리스트 정보를 전달\n" +
            "- step의 정보와 유의사항 전달\n")
    @ApiResponse(responseCode = "200", description = "내 시간제취업 정보 전체 조회 성공")
    @GetMapping("/home")
    public ResponseEntity<HomeInfoRes> getHomeInfo(@AuthenticationPrincipal MemberDetail memberDetail) {
        return ResponseEntity.ok(employmentCheckService.getHomeInfo(memberDetail.getId()));
    }

    @Operation(summary = "Tip 정보 조회", description = "- step에 대한 tip 정보 조회")
    @ApiResponse(responseCode = "200", description = "Tip 정보 조회 성공")
    @GetMapping("/tips")
    public ResponseEntity<List<TipInfoRes>> getTips(@RequestParam CheckStep checkStep) {
        return ResponseEntity.ok(employmentCheckService.getTipInfo(checkStep));
    }

    @Operation(summary = "모든 step의 체크리스트 업데이트", description = "- 모든 step의 제출 서류의 체크리스트 업데이트")
    @ApiResponse(responseCode = "204", description = "모든 step의 체크리스트 업데이트 성공")
    @PutMapping
    public ResponseEntity<Void> putEmploymentCheck(@AuthenticationPrincipal MemberDetail memberDetail, @RequestBody UpdateEmplCheckReq updateEmplCheckReq) {
        employmentCheckService.updateEmploymentCheck(memberDetail.getId(), updateEmplCheckReq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
