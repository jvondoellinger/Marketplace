package zjg.marketplace.core.user.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.interfaces.compositions.builder.Reconstructable;
import zjg.marketplace.core.interfaces.compositions.clone.IClonable;
import zjg.marketplace.core.user.valueObj.cpf.CPF;
import zjg.marketplace.core.user.valueObj.phone.PhoneNumber;

import java.util.Date;
import java.util.Objects;

@Document(collection = "users")
public class User extends BaseEntity implements Reconstructable<UserBuilder>, IClonable<User> {
    // Properties -------------------------------------------
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    private String password;
    @Indexed(unique = true)
    private PhoneNumber phone;
    @Indexed(unique = true)
    private CPF document;
    private Date birthDay;

    // Ctors -------------------------------------------
    protected User() {
        super();
    }
    protected User(User user) {
        setId(user.getId());
        setCreatedAt(user.getCreatedAt());
        setUpdatedAt(user.getUpdatedAt());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setPhone(user.getPhone());
        setBirthDay(user.getBirthDay());
    }
    // Getter --------------------------------------------------
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public Date getBirthDay() {
        return birthDay;
    }
    public PhoneNumber getPhone() {
        return phone;
    }
    public CPF getDocument() {
        return document;
    }

    // Setter --------------------------------------------------
    protected void setPassword(String password) {
        this.password = password;
    }
    protected void setEmail(String email) {
        this.email = email;
    }
    protected void setUsername(String username) {
        this.username = username;
    }
    protected void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    protected void setPhone(PhoneNumber phone) {
        this.phone = phone;
    }
    protected void setDocument(CPF document) {
        this.document = document;
    }

    // Custom
    public User clearPassword() {
        this.password = "N/A";
        return this;
    }

    @Override
    public UserBuilder toBuilder() {
        return new UserBuilder(this);
    }

    @Override
    public User clone() {
        return new User(this);
    }

    public String findFirstName() {
        var index = username.indexOf(" ");
        if(index == -1)
            return username;
        return username.substring(index);
    }

    public String findLastName() {
        var index = username.lastIndexOf(" ");
        if(index == -1) return username;
        return username.substring(index);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(document, user.document) &&
                Objects.equals(birthDay, user.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, phone, document, birthDay);
    }
}
