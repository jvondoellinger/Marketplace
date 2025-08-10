package zjg.marketplace.application.service.product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.product.ProductInput;
import zjg.marketplace.application.mapper.ProductMapper;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.application.service.promisse.IUpdateService;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.factory.chain.updater.ProductUpdaterHandleFactory;
import zjg.marketplace.core.factory.chain.validators.ProductValidatorHandlerFactory;
import zjg.marketplace.core.interfaces.services.repository.Repository;

@Service
public class  UpdateProductService implements IUpdateService<Product, ProductInput> {
    private final IFindService<Product> findService;
    private final Repository<Product> repository;

    public UpdateProductService(Repository<Product> repository, IFindService<Product> facade) {
        this.repository = repository;
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
                    return repository.update(target);
                });
    }
}
