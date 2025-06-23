package zjg.marketplace.core.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import zjg.marketplace.anotation.BadCode;

import java.util.Date;

@Document(collection = "users")
public class User extends BaseEntity {
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    private String password;

    protected User() {
        super();
    }
    // Getter
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    // Setter
    protected void setPassword(String password) {
        this.password = password;
    }
    protected void setEmail(String email) {
        this.email = email;
    }
    protected void setUsername(String username) {
        this.username = username;
    }

    // After build, the entity as valid. Although, when be deserialized, cannot have a guarantee that the entity is valid!
    // Necessary validate again in this method, and return an exception if not valid!
    @BadCode
    public User updateFrom(User partialUser) {
        var username = partialUser.getUsername();
        var email = partialUser.getEmail();
        var password = partialUser.getPassword();

        this.setUsername(username == null ? this.getEmail() : username);
        this.setEmail(email == null ? this.getEmail() : email);
        this.setPassword(password == null ? this.getPassword() : password);
        this.setUpdatedAt(new Date());
        return this;
    }
}
