package zjg.marketplace.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import zjg.marketplace.core.entity.Product;
import zjg.marketplace.infrastructure.repository.custom.BasicFindRepository;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, String>, BasicFindRepository<Product> {
}
