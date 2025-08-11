package zjg.marketplace.infrastructure.notification.impl;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.notification.EmailNotification;
import zjg.marketplace.core.interfaces.services.notification.INotificationSender;
import zjg.marketplace.infrastructure.notification.config.EmailConfig;
import java.time.Duration;
import java.util.Properties;

@Service
public class EmailSender implements INotificationSender<EmailNotification> {
    private final EmailConfig config;

    public EmailSender(EmailConfig config) {
        this.config = config;
    }

    @Override
    public Mono<Void> send(EmailNotification notification) {
        return Mono.fromRunnable(() -> {
            var prop = getProperties();
            var session = getSession(prop);
            try {
                var message = makeMessage(notification, session);
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        })
        .timeout(Duration.ofSeconds(30))
        .then();
    }
    private Properties getProperties() {
        var prop = new Properties();
        prop.put("mail.smtp.host", config.getSmtpHost());
        prop.put("mail.smtp.port", config.getSmtpPort());
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");
        return prop;
    }

    private Session getSession(Properties properties) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.getEmail(), config.getPassword());
            }
        });
    }

    private Message makeMessage(EmailNotification notification, Session session) throws MessagingException {
        var message = new MimeMessage(session);
        message.setFrom(new InternetAddress(config.getEmail()));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(notification.getTarget())
        );
        message.setSubject(notification.getSubject());
        message.setText(notification.getContent());
        return message;
    }
}
