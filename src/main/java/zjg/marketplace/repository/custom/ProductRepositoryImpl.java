package zjg.marketplace.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import zjg.marketplace.core.entity.Product;

public class ProductRepositoryImpl implements BasicFindRepository<Product>{
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Product> findWithPagination(Long offset, int limit) {
        Query query = new Query()
                .skip(offset)
                .limit(limit);
        return mongoTemplate.find(query, Product.class);
    }
}
