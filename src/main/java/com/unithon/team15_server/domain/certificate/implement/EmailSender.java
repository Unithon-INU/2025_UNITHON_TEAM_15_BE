package com.unithon.team15_server.domain.certificate.implement;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSender {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String senderEmail;

    public void snedEmail(String email, byte[] pdfBytes) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(email);
        helper.setFrom(senderEmail);
        helper.setSubject("CheckMate에서 보낸 메일입니다.");

        ByteArrayResource dataResource = new ByteArrayResource(pdfBytes, "application/pdf");

        helper.addAttachment("certificate.pdf", dataResource);
        helper.setText("");
        javaMailSender.send(message);
    }
}
