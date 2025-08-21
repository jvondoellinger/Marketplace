package zjg.marketplace.application.service.product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.DeleteService;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.product.entity.Product;

@Service
public class DeleteProductService implements DeleteService<Product> {
    private final CommandRepository<Product> command;

    public DeleteProductService(CommandRepository<Product> command) {
        this.command = command;
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    public Mono<Void> delete(Product product) {
        return command.delete(product.getId());
    }

    @Override
    @CacheEvict(value = "product", key = "#id")
    public Mono<Void> deleteById(String id) {
        return command.delete(id);
    }
}
