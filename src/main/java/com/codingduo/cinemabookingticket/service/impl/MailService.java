package com.codingduo.cinemabookingticket.service.impl;

import com.codingduo.cinemabookingticket.service.IMailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
@Service
public class MailService implements IMailService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private TemplateEngine templateEngine;
    public static final String EMAIL_TEMPLATE = "email";
    public static final String EMAIL_SUBJECT = "Chúc mừng bạn đã đặt vé thành công";
    public static final String UTF_8_ENCODING = "UTF-8";
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.verify.host}")
    private String fromEmail;
    @Override
    @Async
    public void sendHtmlEmail(String numSeat, String infoSeat, String infoCombo, String to) {
        try {
            Context context = new Context();
            context.setVariable("numSeat", numSeat);
            context.setVariable("infoSeats", infoSeat);
            context.setVariable("infoCombo", infoCombo);
//            context.setVariables(Map.of("name", name, "url", getVerificationUrl(host, token)));
            String text = templateEngine.process(EMAIL_TEMPLATE, context);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(EMAIL_SUBJECT);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }
}
