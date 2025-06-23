package zjg.marketplace.api.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import zjg.marketplace.api.input.ProductInput;
import zjg.marketplace.api.input.ProductWithImageInput;
import zjg.marketplace.application.service.impl.ProductService;
import zjg.marketplace.core.interfaces.storage.StorageService;
import zjg.marketplace.core.entity.Product;

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
                    System.out.printf("Erro ao processar requisição: { %s }\n", e.getMessage());
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
    @GetMapping("/image/{path}")
    public Mono<ResponseEntity<byte[]>> getImage(@PathVariable String path) {
        return productService.getImage(path)
                .map(content -> ResponseEntity
                    .ok()
                    .contentType(guessContentType(path))
                    .body(content));
    }

    private MediaType guessContentType(String filename) {
        if (filename.endsWith(".png")) return MediaType.IMAGE_PNG;
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) return MediaType.IMAGE_JPEG;
        if (filename.endsWith(".gif")) return MediaType.IMAGE_GIF;
        return MediaType.APPLICATION_OCTET_STREAM;
    }
}
