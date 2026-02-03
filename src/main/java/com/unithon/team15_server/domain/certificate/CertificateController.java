package com.unithon.team15_server.domain.certificate;

import com.lowagie.text.DocumentException;
import com.unithon.team15_server.domain.certificate.dto.CertificateReq;
import com.unithon.team15_server.domain.certificate.dto.WorkingTimeLimitReq;
import com.unithon.team15_server.domain.certificate.dto.WorkingTimeLimitRes;
import com.unithon.team15_server.global.jwt.MemberDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/cert")
@RequiredArgsConstructor
@Tag(name = "Certificate", description = "시간제취업확인서 API")
public class CertificateController {
    private final CertificateService certificateService;

    @Operation(summary = "시간제취업확인서 전송", description = "- 시간제취업확인서를 만든 뒤, 입력된 이메일로 전송 (pdf파일 형식)")
    @ApiResponse(responseCode = "204", description = "시간제취업확인서 전송 성공")
    @PostMapping
    public ResponseEntity<Void> sendCert(@RequestBody CertificateReq certificateReq) throws MessagingException, DocumentException, IOException {
        certificateService.sendCert(certificateReq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "시간제취업 근무 가능 시간 조회", description = "－ 시간제취업 근무 가능 시간 정보 조회\n" +
            "- weeklyHours가 null인 경우, 주중/주말 근무 시간이 각각 다르게 적용\n" +
            "- weekendHours가 null인 경우, 주말 및 방학 기간에는 근무 시간 제한이 없음")
    @ApiResponse(responseCode = "200", description = "근무 가능 시간 조회 성공")
    @GetMapping
    public ResponseEntity<WorkingTimeLimitRes> getWorkingTimeLimit(@AuthenticationPrincipal MemberDetail memberDetail, @ModelAttribute WorkingTimeLimitReq workingTimeLimitReq) {
        return ResponseEntity.ok(certificateService.getWorkingTimeLimit(memberDetail.getId(), workingTimeLimitReq));
    }

}
