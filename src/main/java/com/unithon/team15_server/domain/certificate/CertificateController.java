package com.unithon.team15_server.domain.certificate;

import com.lowagie.text.DocumentException;
import com.unithon.team15_server.domain.certificate.dto.CertificateReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
