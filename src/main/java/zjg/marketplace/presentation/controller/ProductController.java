package zjg.marketplace.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.dto.product.ProductInput;
import zjg.marketplace.application.resolver.facade.ServiceResolver;
import zjg.marketplace.application.service.promisse.CreateService;
import zjg.marketplace.application.service.promisse.DeleteService;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.application.service.promisse.UpdateService;
import zjg.marketplace.core.product.entity.Product;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final CreateService<Product, ProductInput> createService;
    private final DeleteService<Product> deleteService;
    private final FindService<Product> findService;
    private final UpdateService<Product, ProductInput> updateService;

    public ProductController(ServiceResolver facade) {
        this.createService = facade.resolveCreate(Product.class, ProductInput.class);
        this.updateService = facade.resolveUpdate(Product.class, ProductInput.class);
        this.findService = facade.resolveFind(Product.class);
        this.deleteService = facade.resolveDelete(Product.class);
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping
    public Mono<List<Product>> get(@RequestParam(defaultValue = "0") Long offset, @RequestParam(defaultValue = "10") Integer max) {
        return findService.get(offset, max).collectList();
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> get(@PathVariable String id) {
        return findService.findById(id).map(ResponseEntity::ok);
    }

    /// * Form method for sharing an image
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Mono<ResponseEntity<Product>> create(@RequestBody ProductInput input) {
        return createService.create(input)
                .map(ResponseEntity::ok);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public Mono<Product> update(@RequestBody ProductInput input, @PathVariable String id) {
        return updateService.update(input, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return deleteService.deleteById(id)
                .map(ResponseEntity::ok);
    }




}
