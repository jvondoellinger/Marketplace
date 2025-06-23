package zjg.marketplace.application.service.helper;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.entity.user.User;

import java.util.List;
import java.util.function.BiConsumer;

@Service
public class FindUserAndProductsHelper {
    private final IFindService<User> userFindService;
    private final IFindService<Product> productFindService;

    public FindUserAndProductsHelper(IFindService<User> userFindService, IFindService<Product> productFindService) {
        this.userFindService = userFindService;
        this.productFindService = productFindService;
    }

    @Cacheable(value = "userAndProduct", key = "#userId + '-' + #limit")
    public Mono<Pair<User, List<Product>>> findUserAndProducts(String userId, List<String> productsId) {
        if(productsId.isEmpty()) throw new IllegalArgumentException("The product id list cannot be empty!");
        var userMono = userFindService.findById(userId);
        var prod = Flux.fromIterable(productsId)
                .flatMap(productFindService::findById)
                .switchIfEmpty(Mono.error(new NullPointerException("Invalid ids")))
                .collectList();
        return Mono.zip(userMono, prod)
                .map(tuple -> Pair.of(tuple.getT1(), tuple.getT2()));
    }
}
