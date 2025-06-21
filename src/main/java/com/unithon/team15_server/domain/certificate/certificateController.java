package com.unithon.team15_server.domain.certificate;

import com.lowagie.text.DocumentException;
import com.unithon.team15_server.domain.certificate.dto.CertificateReq;
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
public class certificateController {
    private final CertificateService certificateService;

    @PostMapping
    public ResponseEntity<Void> sendCert(@RequestBody CertificateReq certificateReq) throws MessagingException, DocumentException, IOException {
        certificateService.sendCert(certificateReq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
