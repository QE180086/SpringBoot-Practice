package app.atula.banking.service;

import app.atula.banking.dto.EmailDetail;
import app.atula.banking.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sendEmails;

    @Override
    public void sendEmail(EmailDetail emailDetail) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sendEmails);
            simpleMailMessage.setTo(emailDetail.getRecipient());
            simpleMailMessage.setText(emailDetail.getMessageBody());
            simpleMailMessage.setSubject(emailDetail.getSubject());

            javaMailSender.send(simpleMailMessage);
            System.out.println("Email send successful");
        } catch (MailException e){
            throw new RuntimeException();
        }
    }
}
