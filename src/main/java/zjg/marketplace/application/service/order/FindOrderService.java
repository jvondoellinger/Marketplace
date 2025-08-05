package zjg.marketplace.application.service.order;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IFindByUserId;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.core.entity.order.Order;
import zjg.marketplace.core.interfaces.services.repository.IRepository;
import zjg.marketplace.core.interfaces.services.repository.SearchByUserIdRepository;

@Service
public class FindOrderService implements IFindService<Order>, IFindByUserId<Order> {
    private final SearchByUserIdRepository<Order> searchByUserIdRepository;
    private final IRepository<Order> repository;

    public FindOrderService(SearchByUserIdRepository<Order> searchByUserIdRepository, IRepository<Order> repository) {
        this.searchByUserIdRepository = searchByUserIdRepository;
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "order", key = "#offset + '-' + #limit")
    public Flux<Order> get(Long offset, Integer limit) {
        return repository.findWithPagination(offset, limit);
    }

    @Override
    @Cacheable(value = "order", key = "#id")
    public Mono<Order> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Order> findByIdNoCache(String id) {
        return repository.findById(id);
    }

    @Override
    @Cacheable(value = "order", key = "#userId")
    public Flux<Order> findByUserId(String userId) {
        return searchByUserIdRepository.findByUserId(userId);
    }
}
