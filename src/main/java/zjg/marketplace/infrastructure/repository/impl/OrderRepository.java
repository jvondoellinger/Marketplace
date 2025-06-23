package zjg.marketplace.infrastructure.repository.impl;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.interfaces.services.repository.IRepository;
import zjg.marketplace.core.interfaces.services.repository.SearchByUserIdRepository;
import zjg.marketplace.infrastructure.repository.spring.OrderReactiveMongoRepository;

@Repository
public class OrderRepository implements IRepository<Order> {
    public final OrderReactiveMongoRepository repositoryLib;
    private final ReactiveMongoTemplate template;

    public OrderRepository(OrderReactiveMongoRepository repositoryLib, ReactiveMongoTemplate template) {
        this.repositoryLib = repositoryLib;
        this.template = template;
    }

    @Override
    public Mono<Order> insert(Order entity) {
        return repositoryLib.insert(entity);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repositoryLib.deleteById(id);
    }

    @Override
    public Mono<Order> findById(String id) {
        return repositoryLib.findById(id);
    }
    @Override
    public Flux<Order> findWithPagination(Long offset, Integer limit) {
        Query query = new Query().skip(offset).limit(limit);
        return template.find(query, Order.class);
    }

    @Override
    public Mono<Order> update(Order updated) {
        return repositoryLib.save(updated);
    }


}
