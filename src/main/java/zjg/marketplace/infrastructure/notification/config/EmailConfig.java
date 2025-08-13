package zjg.marketplace.infrastructure.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {
    @Value("${EMAIL}")
    private String email;
    @Value("${EMAIL_PASSWORD}")
    private String password;
    @Value("${SMTP_HOST}")
    private String smtpHost;
    @Value("${SMTP_PORT}")
    private int smtpPort;

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getSmtpHost() {
        return smtpHost;
    }
    public int getSmtpPort() {
        return smtpPort;
    }

    public void setEmail(String email) {
        System.out.println(email);
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }
    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

}
