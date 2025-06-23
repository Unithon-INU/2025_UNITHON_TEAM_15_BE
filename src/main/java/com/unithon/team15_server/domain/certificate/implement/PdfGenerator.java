package com.unithon.team15_server.domain.certificate.implement;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.unithon.team15_server.domain.certificate.dto.CertificateReq;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PdfGenerator {

    public String parseHtmlToString(CertificateReq certificateReq) {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setForceTemplateMode(true);
        resolver.setTemplateMode(TemplateMode.HTML);

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);

        List<String> workingWeekday = certificateReq.getWeekdayWorkTimes().stream()
                .map(time -> {
                    String startTime = time.getWorkingStartTime().format(DateTimeFormatter.ofPattern("HH:mm"));
                    String endTime = time.getWorkingEndTime().format(DateTimeFormatter.ofPattern("HH:mm"));
                    String days = String.join(",", time.getDay());
                    return startTime + "~" + endTime + "(" + days + ")";
                })
                .toList();

        List<String> workingEndDay = certificateReq.getWeekendWorkTimes().stream()
                .map(time -> {
                    String startTime = time.getWorkingStartTime().format(DateTimeFormatter.ofPattern("HH:mm"));
                    String endTime = time.getWorkingEndTime().format(DateTimeFormatter.ofPattern("HH:mm"));
                    String days = String.join(",", time.getDay());
                    return startTime + "~" + endTime + "(" + days + ")";
                })
                .toList();

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        Context context = new Context();
        Map<String, Object> data = new HashMap<>();
        data.put("name", certificateReq.getName());
        data.put("regNum", certificateReq.getRegNum());
        data.put("major", certificateReq.getMajor());
        data.put("phoneNum", certificateReq.getPhoneNum());
        data.put("email", certificateReq.getEmail());
        data.put("semester", certificateReq.getSemester());
        data.put("companyName", certificateReq.getCompanyName());
        data.put("bizRegNum", certificateReq.getBizRegNum());
        data.put("industry", certificateReq.getIndustry());
        data.put("address", certificateReq.getAddress());
        data.put("companyPhoneNum", certificateReq.getCompanyPhoneNum());
        data.put("period", certificateReq.getWorkingStartDate().toString() + "~" + certificateReq.getWorkingEndDate().toString());
        data.put("hourlyWage", certificateReq.getHourlyWage());
        data.put("weekDayWorkTimes", workingWeekday);
        data.put("weekEndWorkTimes", workingEndDay);
        data.put("date", date);
        context.setVariables(data);

        return engine.process("certificate_ko", context);
    }

    public byte[] generatePdf(String html) throws DocumentException, IOException {
        ITextRenderer iTextRenderer = getRenderer(html);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            iTextRenderer.createPDF(baos);
            return baos.toByteArray();
        }
    }

    private ITextRenderer getRenderer(String html) throws IOException, DocumentException {
        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(html);

        iTextRenderer.getFontResolver()
                .addFont(new ClassPathResource("/static/font/NanumBarunGothic.ttf").getURL()
                                .toString(),
                        BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED);

        iTextRenderer.layout();
        return iTextRenderer;
    }
}
