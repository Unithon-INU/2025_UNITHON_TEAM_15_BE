package com.unithon.team15_server.domain.certificate;

import com.lowagie.text.DocumentException;
import com.unithon.team15_server.domain.certificate.dto.CertificateReq;
import com.unithon.team15_server.domain.certificate.implement.EmailSender;
import com.unithon.team15_server.domain.certificate.implement.PdfGenerator;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final PdfGenerator pdfGenerator;
    private final EmailSender emailSender;

    public void sendCert(CertificateReq certificateReq) throws DocumentException, IOException, MessagingException {
        String pdf = pdfGenerator.parseHtmlToString(certificateReq);
        emailSender.snedEmail(certificateReq.getEmail(), pdfGenerator.generatePdf(pdf));
    }
}
