package ru.smarkov.demo.domain.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.smarkov.demo.domain.mail.dto.MailInfo;

import java.util.Map;

@Service
public class MailSender {
    @Autowired
    private Map<String, MailGenerator> map;

    public void send(MailInfo mailInfo) {

        MailGenerator mailGenerator = map.get(mailInfo.code());
        if (mailGenerator == null) {
            throw new UnsupportedOperationException(mailInfo + " is not supported yet");
        }

        String html = mailGenerator.generate(mailInfo.message());
        sendMail(html);
    }

    private void sendMail(String html) {
        System.out.println(html);
    }
}
