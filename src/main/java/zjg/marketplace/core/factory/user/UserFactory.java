package zjg.marketplace.core.factory.user;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.entity.user.UserBuilder;
import zjg.marketplace.core.factory.chain.validators.UserValidatorHandlerFactory;
import zjg.marketplace.core.valueObject.cpf.CPF;
import zjg.marketplace.core.valueObject.phone.PhoneNumber;
import zjg.marketplace.core.interfaces.services.security.TextEncryptor;

import java.util.Date;


public class UserFactory {
    public static User factory(String username,
                               String email,
                               String password,
                               PhoneNumber phone,
                               CPF document,
                               Date birthday,
                               TextEncryptor encryptor) {
        var handler = UserValidatorHandlerFactory.factory();
        var pass = encryptor.encrypt(password);
        var user = UserBuilder.builder()
                .username(username)
                .email(email)
                .password(pass)
                .phone(phone)
                .document(document)
                .birthDay(birthday)
                .build();
        handler.handle(user);
        return user;
    }
 }
