package zjg.marketplace.infrastructure.repository.adapter.query.order;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.interfaces.services.repository.query.QueryRepository;
import zjg.marketplace.infrastructure.repository.interfaces.OrderReactiveMongoRepository;

@Repository
public class QueryOrderRepositoryImpl implements QueryRepository<Order> {
    private final OrderReactiveMongoRepository jpaRepository;
    private final ReactiveMongoTemplate template;

    public QueryOrderRepositoryImpl(OrderReactiveMongoRepository jpaRepository, ReactiveMongoTemplate template) {
        this.jpaRepository = jpaRepository;
        this.template = template;
    }

    @Override
    public Mono<Order> findById(String id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Flux<Order> findWithPagination(Long offset, Integer limit) {
        var query = new Query().skip(offset).limit(limit);
        return template.find(query, Order.class);
    }
}
