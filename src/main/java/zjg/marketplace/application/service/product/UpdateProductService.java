package zjg.marketplace.application.service.product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.product.ProductInput;
import zjg.marketplace.application.mapper.ProductMapper;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.UpdateService;
import zjg.marketplace.core.product.chain.ProductUpdaterHandleFactory;
import zjg.marketplace.core.product.chain.ProductValidatorHandlerFactory;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.product.entity.Product;

@Service
public class  UpdateProductService implements UpdateService<Product, ProductInput> {
    private final FindService<Product> findService;
    private final CommandRepository<Product> command;

    public UpdateProductService(CommandRepository<Product> command, FindService<Product> facade) {
        this.command = command;
        this.findService = facade;
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    public Mono<Product> update(ProductInput productInput, String id) {
        var updateHandler = ProductUpdaterHandleFactory.factory();
        var validateHandler = ProductValidatorHandlerFactory.factory();
        return findService.findById(id)
                .flatMap(target -> {
                    var source = ProductMapper.unsafeMap(productInput);
                    updateHandler.handle(target, source);
                    validateHandler.handle(target);
                    return command.update(target);
                });
    }
}
