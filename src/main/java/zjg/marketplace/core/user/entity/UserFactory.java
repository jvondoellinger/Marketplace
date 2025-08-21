package zjg.marketplace.core.user.entity;

import zjg.marketplace.core.security.services.TextEncryptor;
import zjg.marketplace.core.user.chain.UserValidatorHandlerFactory;
import zjg.marketplace.core.user.valueObj.cpf.CPF;
import zjg.marketplace.core.user.valueObj.phone.PhoneNumber;

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
