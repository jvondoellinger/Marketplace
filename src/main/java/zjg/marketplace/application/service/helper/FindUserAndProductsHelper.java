package zjg.marketplace.application.service.helper;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.product.repository.exceptions.ProductNotFoundException;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.repository.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Objects;

@Service
public class FindUserAndProductsHelper { // Façade
    private final FindService<User> userFindService;
    private final FindService<Product> productFindService;

    public FindUserAndProductsHelper(FindService<User> userFindService, FindService<Product> productFindService) {
        this.userFindService = userFindService;
        this.productFindService = productFindService;
    }
    public Mono<List<Product>> existsUserAndProduct(String userId, List<String> productsId) {
        if(productsId.isEmpty()) throw new IllegalArgumentException("The product id list cannot be empty!");
        var flux = Flux.fromIterable(productsId)
                .flatMap(productFindService::findById)
                .map(product -> {
                    if(Objects.isNull(product)) throw new ProductNotFoundException();
                    return product;
                }).collectList();
          System.out.println(userId);
        return userFindService.exists(userId)
                .doOnNext(contains -> {
                    if(!contains) throw new UserNotFoundException();
                })
                .then(flux);

    }
}
