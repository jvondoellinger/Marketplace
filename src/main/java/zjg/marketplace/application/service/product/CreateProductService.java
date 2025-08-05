package zjg.marketplace.application.service.product;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.product.ProductInput;
import zjg.marketplace.application.mapper.ProductMapper;
import zjg.marketplace.application.service.promisse.ICreateService;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.services.repository.IRepository;

@Service
public class CreateProductService implements ICreateService<Product, ProductInput> {
    private final IRepository<Product> repository;

    public CreateProductService(IRepository<Product> repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Product> create(ProductInput productInput) {
        var product = ProductMapper.map(productInput);
        return repository.insert(product);
    }
}
