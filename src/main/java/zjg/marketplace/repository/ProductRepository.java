package zjg.marketplace.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import zjg.marketplace.core.entity.Product;
import zjg.marketplace.core.entity.User;
import zjg.marketplace.repository.custom.BasicFindRepository;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, String>, BasicFindRepository<Product> {
}
