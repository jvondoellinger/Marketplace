package zjg.marketplace.infrastructure.repository.interfaces;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import zjg.marketplace.core.entity.product.Product;

@Repository
public interface ProductReactiveMongoRepository extends ReactiveMongoRepository<Product, String> {
}
