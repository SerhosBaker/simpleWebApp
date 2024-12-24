package ru.smarkov.demo.domain.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smarkov.demo.domain.mail.MailSender;
import ru.smarkov.demo.domain.mail.dto.MailInfo;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private MailSender mailSender;

    @PostMapping("/sendmail")
    public void sendMail() {
        mailSender.send(new MailInfo("2", "New books in out book store!"));
    }
}
