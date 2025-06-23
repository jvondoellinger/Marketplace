package zjg.marketplace.presentation.controller.publicControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.service.promisse.IFindService;
import zjg.marketplace.core.entity.product.Product;

import java.util.List;

@RestController
@RequestMapping("/api/public/product")
public class ProductPublicController {
    private final IFindService<Product> findService;

    public ProductPublicController(IFindService<Product> findService) {
        this.findService = findService;
    }

    @GetMapping
    public Mono<List<Product>> get(
            @RequestParam(defaultValue = "0") Long offset,
            @RequestParam(defaultValue = "10") Integer max) {
        return findService.get(offset, max).collectList();
    }
}
