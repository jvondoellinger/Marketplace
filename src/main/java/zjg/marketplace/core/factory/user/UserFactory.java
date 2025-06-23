package zjg.marketplace.core.factory.user;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.entity.user.UserBuilder;
import zjg.marketplace.core.factory.chain.UserValidatorHandlerFactory;
import zjg.marketplace.core.field.phone.PhoneNumber;
import zjg.marketplace.core.interfaces.security.TextEncryptor;

import java.util.Date;


public class UserFactory {
    private static User generate(String username, String email, String password, PhoneNumber phone, Date birthday) {
        return UserBuilder.builder()
                .username(username)
                .email(email)
                .password(password)
                .phone(phone)
                .birthDay(birthday)
                .build();
    }
    public static User factory(String username, String email, String password, PhoneNumber phone, Date birthday) {
        var validator = UserValidatorHandlerFactory.factory();
        var user= generate(username, email, password, phone, birthday);
        validator.handle(user);
        return user;
    }
    public static User factory(String username, String email, String password, PhoneNumber phone, Date birthday, TextEncryptor encryptor) {
        var user = factory(username, email, password, phone, birthday);
        var encodedPassword = encryptor.encrypt(user.getPassword());
        var newUser = user.toBuilder()
                .password(encodedPassword)
                .build();
        return newUser; // Mesmo sendo a mesmma memoria alocada, optei por deixar assim para ser mais explicito!
    }
 }
