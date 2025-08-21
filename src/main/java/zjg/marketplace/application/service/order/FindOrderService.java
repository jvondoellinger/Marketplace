package zjg.marketplace.application.service.order;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IFindByUserId;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.interfaces.services.repository.query.QueryByUserIdRepository;
import zjg.marketplace.core.interfaces.services.repository.query.QueryRepository;

@Service
public class FindOrderService implements FindService<Order>, IFindByUserId<Order> {
    private final QueryByUserIdRepository<Order> queryUserIdRepository;
    private final QueryRepository<Order> queryOrder;

    public FindOrderService(QueryByUserIdRepository<Order> queryUserIdRepository, QueryRepository<Order> queryOrder) {
        this.queryUserIdRepository = queryUserIdRepository;
        this.queryOrder = queryOrder;
    }

    @Override
    @Cacheable(value = "order", key = "#offset + '-' + #limit")
    public Flux<Order> get(long offset, int limit) {
        return queryOrder.findWithPagination(offset, limit);
    }

    @Override
    @Cacheable(value = "order", key = "#id")
    public Mono<Order> findById(String id) {
        return queryOrder.findById(id);
    }

    @Override
    public Mono<Order> findByIdNoCache(String id) {
        return queryOrder.findById(id);
    }

    @Override
    @Cacheable(value = "order", key = "#userId")
    public Flux<Order> findByUserId(String userId) {
        return queryUserIdRepository.findByUserId(userId);
    }
}
