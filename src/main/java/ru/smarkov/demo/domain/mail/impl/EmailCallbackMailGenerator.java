package ru.smarkov.demo.domain.mail.impl;

import org.springframework.stereotype.Component;
import ru.smarkov.demo.domain.mail.MailGenerator;

@Component("2")
public class EmailCallbackMailGenerator implements MailGenerator {

    @Override
    public String generate(String mailInfo) {

        return mailInfo + "\n don't call us, we call you";
    }
}
