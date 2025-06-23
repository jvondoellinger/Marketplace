package zjg.marketplace.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import zjg.marketplace.api.input.ProductInput;
import zjg.marketplace.api.input.ProductWithImageInput;
import zjg.marketplace.application.service.impl.ProductService;
import zjg.marketplace.application.service.promisse.StorageService;
import zjg.marketplace.core.entity.Product;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService, StorageService storageService) {
        this.productService = productService;
    }
    /// * Form method for sharing an image
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Product>> create(@ModelAttribute ProductWithImageInput input) {
        System.out.printf("Recebida requisição para criar produto: {}\n", input);
        var creation = productService.create(input);
        return creation.map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    System.out.printf("Erro ao processar requisição: {}\n", e.getMessage());
                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }
    /// * Form method for sharing an image
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/complete/{id}")
    public Mono<ResponseEntity<Product>> updateWithImage(@ModelAttribute ProductWithImageInput input, @PathVariable String id) {
        var update = productService.updateWithImage(input, id);
        return update.map(ResponseEntity::ok);
    }

    @PatchMapping("/fields/{id}")
    public Mono<ResponseEntity<Product>> updatePartial(@ModelAttribute ProductInput input, @PathVariable String id) {
        var update = productService.updateFields(input, id); // a
        return update.map(ResponseEntity::ok);
    }
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return productService.delete(id).map(ResponseEntity::ok);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Product>> get(@PathVariable String id) {
        return productService.findById(id).map(ResponseEntity::ok);
    }
}
