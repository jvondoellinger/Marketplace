package zjg.marketplace.core.services.product;

import zjg.marketplace.core.anotation.ImplementsInFuture;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.repository.IRepository;

@ImplementsInFuture
public class ProductService {
    private final IRepository<Product> repository;

    public ProductService(IRepository<Product> repository) {
        this.repository = repository;
    }

}

