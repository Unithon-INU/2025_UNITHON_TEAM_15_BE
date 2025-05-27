package com.unithon.team15_server.global.verification;

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

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String senderEmail;

    public void sendEmail(EmailSendReq emailSendReq) throws MessagingException {
        MimeMessage emailForm = createEmailForm(emailSendReq.getEmail());
        javaMailSender.send(emailForm);
    }

    public void verifyCode(EmailVerifyCodeReq emailVerifyCodeReq) {
        Email email = emailRepository.findEmailByEmail(emailVerifyCodeReq.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        if(!email.getExpiredAt().isBefore(LocalDateTime.now())) { //유효기간 만료
            emailRepository.delete(email);
            throw new CustomException(ErrorCode.EXPIRED_CODE);
        }
        if (!email.getCode().equals(emailVerifyCodeReq.getCode())) { //코드 유효 X
            throw new CustomException(ErrorCode.INVALID_CODE);
        }
    }

    private String createCode() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) { // 총 8자리 인증 번호 생성
            int idx = random.nextInt(3); // 0~2 사이의 값을 랜덤하게 받아와 idx에 집어넣습니다

            // 0,1,2 값을 switchcase를 통해 꼬아버립니다.
            // 숫자와 ASCII 코드를 이용합니다.
            switch (idx) {
                case 0:
                    // 0일 때, a~z 까지 랜덤 생성 후 key에 추가
                    key.append((char) (random.nextInt(26) + 97));
                    break;
                case 1:
                    // 1일 때, A~Z 까지 랜덤 생성 후 key에 추가
                    key.append((char) (random.nextInt(26) + 65));
                    break;
                case 2:
                    // 2일 때, 0~9 까지 랜덤 생성 후 key에 추가
                    key.append(random.nextInt(9));
                    break;
            }
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
        message.setSubject("안녕하세요. 앱이름에서 보낸 인증번호입니다.");
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
