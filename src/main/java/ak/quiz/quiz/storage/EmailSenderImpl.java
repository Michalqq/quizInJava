package ak.quiz.quiz.storage;

import ak.quiz.quiz.model.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderImpl implements EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendEmail(String to, String title, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo("wi7@vp.pl");
            helper.setSubject(title);
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        javaMailSender.send(mail);
        return true;
    }
}