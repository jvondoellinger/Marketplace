package zjg.marketplace.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.*;
import zjg.marketplace.application.dto.product.ProductInput;
import zjg.marketplace.core.entity.product.Product;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ICreateService<Product, ProductInput> createService;
    private final IDeleteService<Product> deleteService;
    private final FindService<Product> findService;
    private final IUpdateService<Product, ProductInput> updateService;

    public ProductController(ServiceResolverFacade facade) {
        this.createService = facade.resolveCreate(Product.class, ProductInput.class);
        this.updateService = facade.resolveUpdate(Product.class, ProductInput.class);
        this.findService = facade.resolveFind(Product.class);
        this.deleteService = facade.resolveDelete(Product.class);
    }

    @GetMapping
    public Mono<List<Product>> get(@RequestParam(defaultValue = "0") Long offset, @RequestParam(defaultValue = "10") Integer max) {
        return findService.get(offset, max).collectList();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> get(@PathVariable String id) {
        return findService.findById(id).map(ResponseEntity::ok);
    }

    /// * Form method for sharing an image
    @PostMapping()
    public Mono<ResponseEntity<Product>> create(@RequestBody ProductInput input) {
        return createService.create(input)
                .map(ResponseEntity::ok);
    }

    @PatchMapping("/{id}")
    public Mono<Product> update(@RequestBody ProductInput input, @PathVariable String id) {
        return updateService.update(input, id);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return deleteService.deleteById(id)
                .map(ResponseEntity::ok);
    }




}
