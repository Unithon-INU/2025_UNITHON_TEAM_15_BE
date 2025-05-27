package com.unithon.team15_server.global.verification;

import com.unithon.team15_server.global.verification.dto.EmailSendReq;
import com.unithon.team15_server.global.verification.dto.EmailVerifyCodeReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @Operation(summary = "인증 코드 전송")
    @ApiResponse(responseCode = "204", description = "인증 코드 전송 성공")
    @PostMapping("/send")
    public ResponseEntity<Void> send(@RequestBody EmailSendReq emailSendReq) throws MessagingException {
        emailService.sendEmail(emailSendReq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "인증 코드 유효성 검사")
    @ApiResponse(responseCode = "204", description = "인증 코드 유효함")
    @ApiResponse(responseCode = "401", description = "인증코드가 유효하지 않거나 만료되었습니다.")
    @PostMapping("/verify")
    public ResponseEntity<Void> verify(@RequestBody EmailVerifyCodeReq emailVerifyCodeReq){
        emailService.verifyCode(emailVerifyCodeReq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
