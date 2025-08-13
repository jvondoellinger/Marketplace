package zjg.marketplace.core.entity.user;

import zjg.marketplace.core.entity.base.BaseBuilder;
import zjg.marketplace.core.valueObjects.cpf.CPF;
import zjg.marketplace.core.valueObjects.phone.PhoneNumber;

import java.util.Date;

public class UserBuilder extends BaseBuilder<User, UserBuilder> {
    //private final User entity;
    private UserBuilder() {
        super(new User());
    }
    protected UserBuilder(User user) {
        super(user);
    }
    // Implementation
    @Override
    protected UserBuilder self() {
        return this;
    }

    // Set values
    public UserBuilder username(String username) {
        entity.setUsername(username);
        return this;
    }
    public UserBuilder email(String email) {
        entity.setEmail(email);
        return this;
    }
    public UserBuilder password(String password) {
        entity.setPassword(password);
        return this;
    }
    public UserBuilder birthDay(Date birthday) {
        entity.setBirthDay(birthday);
        return this;
    }
    public UserBuilder phone(PhoneNumber phone) {
        entity.setPhone(phone);
        return this;
    }
    public UserBuilder document(CPF phone) {
        entity.setDocument(phone);
        return this;
    }

    // Static method
    public static UserBuilder builder() {
        return new UserBuilder();
    }

}
