package com.unithon.team15_server.global.verification;

import com.unithon.team15_server.domain.member.MemberRepository;
import com.unithon.team15_server.global.exception.CustomException;
import com.unithon.team15_server.global.exception.ErrorCode;
import com.unithon.team15_server.global.verification.dto.EmailSendReq;
import com.unithon.team15_server.global.verification.dto.EmailVerifyCodeReq;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository emailRepository;
    private final MemberRepository memberRepository;
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String senderEmail;

    public void sendEmail(EmailSendReq emailSendReq) throws MessagingException {
        if (memberRepository.existsByEmail(emailSendReq.getEmail())) {
            throw new CustomException(ErrorCode.USER_ALREADY_REGISTERED);
        }
        MimeMessage emailForm = createEmailForm(emailSendReq.getEmail());
        javaMailSender.send(emailForm);
    }

    public void verifyCode(EmailVerifyCodeReq emailVerifyCodeReq) {
        Email email = emailRepository.findEmailByEmail(emailVerifyCodeReq.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        if (email.getExpiredAt().isBefore(LocalDateTime.now())) { //유효기간 만료
            emailRepository.delete(email);
            throw new CustomException(ErrorCode.EXPIRED_AUTH_CODE);
        }
        if (!email.getCode().equals(emailVerifyCodeReq.getCode())) { //코드 유효 X
            throw new CustomException(ErrorCode.INVALID_AUTH_CODE);
        }
    }

    private String createCode() {
        Random random = new SecureRandom();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 6; i++) { // 총 6자리 인증 번호 생성
            key.append(random.nextInt(10));
        }
        return key.toString();
    }

    private String setMailContext(String code) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("code", code);

        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process("mail", context);
    }

    private MimeMessage createEmailForm(String email) throws MessagingException {
        String code = createCode();
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("CHECKMATE에서 보낸 인증번호입니다.");
        message.setFrom(senderEmail);
        message.setText(setMailContext(code), "utf-8", "html");

        Optional<Email> emailOptional = emailRepository.findEmailByEmail(email);
        Email emailInfo;

        if (emailOptional.isPresent()) {
            emailInfo = emailOptional.get();
            emailInfo.updateInfo(code); //다시 전송을 눌렀을 경우, 기존에 있던 정보 업데이트
        } else {
            emailInfo = Email.create(email, code);
        }
        emailRepository.save(emailInfo); //원래는 redis로 해야 함

        return message;
    }
}
