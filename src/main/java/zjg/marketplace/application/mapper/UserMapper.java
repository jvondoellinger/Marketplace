package zjg.marketplace.application.mapper;

import org.springframework.stereotype.Service;
import zjg.marketplace.core.entity.User;
import zjg.marketplace.core.entity.UserBuilder;
import zjg.marketplace.api.input.UserInput;

@Service
public class UserMapper {
    public User map(UserInput dto) {
        return UserBuilder.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
