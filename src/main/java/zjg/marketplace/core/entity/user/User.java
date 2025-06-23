package zjg.marketplace.core.entity.user;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.field.phone.PhoneNumber;
import zjg.marketplace.core.interfaces.builder.Reconstructable;
import zjg.marketplace.core.interfaces.clone.IClonable;
import zjg.marketplace.core.interfaces.helper.ISelfUpdatable;

import java.util.Date;


@Document(collection = "users")
public class User extends BaseEntity implements Reconstructable<UserBuilder>, IClonable<User>, ISelfUpdatable<User> {
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    private String password;
    @Indexed(unique = true)
    private PhoneNumber phone;
    private Date birthDay;

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
    public Date getBirthDay() {
        return birthDay;
    }
    public PhoneNumber getPhone() {
        return phone;
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
    protected void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    protected void setPhone(PhoneNumber phone) {
        this.phone = phone;
    }

    // Custom
    public User clearPassword() {
        this.password = "N/A";
        return this;
    }

    // After build, the entity as valid. Although, when be deserialized, cannot have a guarantee that the entity is valid!
    // Necessary validate again in this method, and return an exception if not valid!
    @BadCode
    @Override
    public User selfUpdate(User partialUser) {
        var username = partialUser.getUsername();
        var email = partialUser.getEmail();
        var password = partialUser.getPassword();
        var phone = partialUser.getPhone();
        var birthday = partialUser.getBirthDay();

        this.setUsername(username == null ? this.getEmail() : username);
        this.setEmail(email == null ? this.getEmail() : email);
        this.setPassword(password == null ? this.getPassword() : password);
        this.setPhone(phone == null ? this.getPhone() : phone);
        this.setBirthDay(birthday == null ? this.getBirthDay() : birthday);
        this.setUpdatedAt(new Date());
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


}
