package app.atula.banking.service;

import app.atula.banking.dto.EmailDetail;

public interface EmailService {
    public void sendEmail(EmailDetail emailDetail);
}
