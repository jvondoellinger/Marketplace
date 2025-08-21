package zjg.marketplace.application.mapper;

import zjg.marketplace.application.dto.user.UserInput;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.entity.UserBuilder;
import zjg.marketplace.core.user.entity.UserFactory;
import zjg.marketplace.core.security.services.TextEncryptor;

public class UserMapper {
    public static User map(UserInput input, TextEncryptor encryptor) {
        return UserFactory.factory(input.getUsername(),
                input.getEmail(),
                input.getPassword(),
                input.getPhone(),
                input.getDocument(),
                input.getBirthDay(),
                encryptor);
    }
    public static User unsafeMap(UserInput input, TextEncryptor encryptor) {
        var rawPass = encryptor.encrypt(input.getPassword());
        return UserBuilder.builder()
                .username(input.getUsername())
                .email(input.getEmail())
                .document(input.getDocument())
                .phone(input.getPhone())
                .birthDay(input.getBirthDay())
                .password(rawPass)
                .build();
    }
}
