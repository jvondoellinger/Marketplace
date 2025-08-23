package zjg.marketplace.application.service.order;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.IFindByUserId;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.order.repository.query.OrderRepositoryQuery;

@Service
public class FindOrderService implements FindService<Order>, IFindByUserId<Order> {
    private final OrderRepositoryQuery queryOrder;

    public FindOrderService(OrderRepositoryQuery queryOrder) {
        this.queryOrder = queryOrder;
    }

    @Override
    @Cacheable(value = "order", key = "#offset + '-' + #limit")
    public Flux<Order> get(long offset, int limit) {
        return queryOrder.findWithPagination(offset, limit);
    }

    @Override
    @Cacheable(value = "order_exist", key = "#id")
    public Mono<Boolean> exists(String id) {
        return queryOrder.exists(id);
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
        return queryOrder.findByUserId(userId);
    }
}
