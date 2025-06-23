package zjg.marketplace.application.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import zjg.marketplace.core.valueObject.cpf.CPF;
import zjg.marketplace.core.valueObject.phone.PhoneNumber;
import java.util.Date;

public class UserInput {
    private String username;
    private String email;
    private String password;
    private PhoneNumber phone;
    private CPF document;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    // Getter ------------------------------------------------------------
    public Date getBirthDay() {
        return birthDay;
    }
    public PhoneNumber getPhone() {
        return phone;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public CPF getDocument() {
        return document;
    }

    // Setter ------------------------------------------------------------
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone(PhoneNumber phone) {
        this.phone = phone;
    }
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    public void setDocument(CPF document) {
        this.document = document;
    }
}
