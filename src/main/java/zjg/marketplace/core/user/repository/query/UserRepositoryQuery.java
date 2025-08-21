package zjg.marketplace.core.user.repository.query;

import reactor.core.publisher.Mono;
import zjg.marketplace.core.interfaces.services.repository.Repository;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.user.entity.User;

public interface UserRepositoryQuery extends Repository<Product> {
    Mono<User> find(String email);
}
