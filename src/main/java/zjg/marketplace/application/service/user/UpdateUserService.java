package zjg.marketplace.application.service.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.user.UserInput;
import zjg.marketplace.application.mapper.UserMapper;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.IUpdateService;
import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.factory.chain.updater.UserUpdaterHandleFactory;
import zjg.marketplace.core.factory.chain.validators.UserValidatorHandlerFactory;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.interfaces.services.security.TextEncryptor;

@Service
public class UpdateUserService implements IUpdateService<User, UserInput> {
    private final CommandRepository<User> command;
    private final FindService<User> findService;
    private final TextEncryptor encryptor;
    public UpdateUserService(CommandRepository<User> command, FindService<User> findService, TextEncryptor encryptor) {
        this.command = command;
        this.findService = findService;
        this.encryptor = encryptor;
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public Mono<User> update(UserInput userUpdateInput, String id) {
        var updateHandler = UserUpdaterHandleFactory.factory();
        var validateHandler = UserValidatorHandlerFactory.factory();
        return findService.findById(id)
                .flatMap(target -> {
                    var mapped = UserMapper.unsafeMap(userUpdateInput, encryptor);
                    updateHandler.handle(target, mapped); // modify entity -| In One Helper, perhaps?
                    validateHandler.handle(target); // validate entity ---------|
                    return command.update(target);
                });
    }
}
