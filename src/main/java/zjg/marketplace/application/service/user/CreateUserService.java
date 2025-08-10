package zjg.marketplace.application.service.user;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.user.UserInput;
import zjg.marketplace.application.mapper.UserMapper;
import zjg.marketplace.application.service.promisse.ICreateService;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.interfaces.services.security.TextEncryptor;

@Service
public class CreateUserService implements ICreateService<User, UserInput> {
    private final CommandRepository<User> command;
    private final TextEncryptor encryptor;

    public CreateUserService(CommandRepository<User> command, TextEncryptor encryptor) {
        this.command = command;
        this.encryptor = encryptor;
    }

    @Override
    public Mono<User> create(UserInput userInput) {
        var user = UserMapper.map(userInput, encryptor);
        return command.insert(user)
                .doOnNext(User::clearPassword);
    }
}
