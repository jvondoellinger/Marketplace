package zjg.marketplace.application.service.helper;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.helper.valueObj.UserProductPair;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.user.entity.User;

import java.util.List;

@Service
public class FindUserAndProductsHelper {
    private final FindService<User> userFindService;
    private final FindService<Product> productFindService;

    public FindUserAndProductsHelper(FindService<User> userFindService, FindService<Product> productFindService) {
        this.userFindService = userFindService;
        this.productFindService = productFindService;
    }
    public Mono<UserProductPair> findUserAndProducts(String userId, List<String> productsId) {
        if(productsId.isEmpty()) throw new IllegalArgumentException("The product id list cannot be empty!");
        var userMono = userFindService.findById(userId);
        var prod = Flux.fromIterable(productsId)
                .flatMap(productFindService::findById)
                .switchIfEmpty(Mono.error(new NullPointerException("Invalid ids")))
                .collectList();
        return userMono.zipWith(prod)
                .map(   tuple ->
                        new UserProductPair(tuple.getT1(), tuple.getT2()));
    }
}
