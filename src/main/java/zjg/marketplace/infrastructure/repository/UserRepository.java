package zjg.marketplace.infrastructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import zjg.marketplace.core.entity.User;
import zjg.marketplace.infrastructure.repository.custom.BasicFindRepository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String>, BasicFindRepository<User> {
}
