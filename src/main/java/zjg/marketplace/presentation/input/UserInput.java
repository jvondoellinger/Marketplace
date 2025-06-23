package zjg.marketplace.presentation.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import zjg.marketplace.core.field.phone.PhoneNumber;

import java.util.Date;

// Depois validar a possibilidade se solicitar um JSON correspondente ao input, mas já desserializar no OBJ desejado!

public class UserInput {
    private String username;
    private String email;
    private String password;
    private PhoneNumber phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    // Getter
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

    // Setter
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
}
