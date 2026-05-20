package ru.smarkov.demo.domain.mail.impl;

import org.springframework.stereotype.Component;
import ru.smarkov.demo.domain.mail.MailGenerator;

@Component("1")
public class WelcomeMailGenerator implements MailGenerator {

    @Override
    public String generate(String mailInfo) {
        return mailInfo + "\n welcome mail";
    }
}
