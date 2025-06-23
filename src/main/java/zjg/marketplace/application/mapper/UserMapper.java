package zjg.marketplace.application.mapper;

import org.springframework.stereotype.Service;
import zjg.marketplace.presentation.input.UserInput;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.entity.user.UserBuilder;
import zjg.marketplace.core.factory.user.UserFactory;
import zjg.marketplace.core.interfaces.security.TextEncryptor;

@Service
public class UserMapper {
    public User map(UserInput dto) {
        return UserFactory.factory(dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getPhone(), dto.getBirthDay());
    }
    public User map(UserInput dto, TextEncryptor encryptor) {
        return UserFactory.factory(dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getPhone(), dto.getBirthDay(), encryptor);
    }
    public User partialMap(UserInput input) {
        return UserBuilder.builder()
                .username(input.getUsername())
                .email(input.getEmail())
                .phone(input.getPhone())
                .birthDay(input.getBirthDay())
                .build();
    }
    public User partialMap(UserInput input, TextEncryptor encryptor) {
        return UserBuilder.builder()
                .username(input.getUsername())
                .email(input.getEmail())
                .phone(input.getPhone())
                .birthDay(input.getBirthDay())
                .password(encryptor.encrypt(input.getPassword()))
                .build();
    }
}
