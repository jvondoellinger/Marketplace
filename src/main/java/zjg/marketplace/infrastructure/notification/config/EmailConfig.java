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
    private String smtp_host;
    @Value("${SMTP_PORT}")
    private int smtp_port;

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getSmtpHost() {
        return smtp_host;
    }
    public int getSmtpPort() {
        return smtp_port;
    }

    public void setEmail(String email) {
        System.out.println(email);
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setSmtp_host(String smtp_host) {
        this.smtp_host = smtp_host;
    }
    public void setSmtp_port(int smtp_port) {
        this.smtp_port = smtp_port;
    }

}
