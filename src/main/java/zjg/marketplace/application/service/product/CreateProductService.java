package zjg.marketplace.application.service.product;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.product.ProductInput;
import zjg.marketplace.application.mapper.ProductMapper;
import zjg.marketplace.application.service.promisse.CreateService;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.product.entity.Product;

@Service
public class CreateProductService implements CreateService<Product, ProductInput> {
    private final CommandRepository<Product> command;

    public CreateProductService(CommandRepository<Product> command) {
        this.command = command;
    }

    @Override
    public Mono<Product> create(ProductInput productInput) {
        var product = ProductMapper.map(productInput);
        return command.insert(product);
    }
}
