package zjg.marketplace.core.entity;

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

    // Static method
    public static UserBuilder builder() {
        return new UserBuilder();
    }

}
