package zjg.marketplace.presentation.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.adapter.ProductImageUploadMediatorAdapter;
import zjg.marketplace.application.mapper.BinaryMapper;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.mediator.ProductImageUploadMediator;
import zjg.marketplace.presentation.adapter.path.PathMediaTypeAdapter;
import zjg.marketplace.presentation.input.ImageListWrapper;
import zjg.marketplace.presentation.input.ProductInput;
import zjg.marketplace.presentation.input.ProductWithImageInput;
import zjg.marketplace.application.service.impl.ProductService;
import zjg.marketplace.application.service.impl.StorageServiceImpl;
import zjg.marketplace.core.entity.product.Product;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@BadCode
public class ProductController {
    private final ProductService productService;
    private final StorageServiceImpl storageService;
    private final ProductImageUploadMediatorAdapter mediator;
    public ProductController(ProductService productService, StorageServiceImpl storageService, ProductImageUploadMediatorAdapter mediator) {
        this.productService = productService;
        this.storageService = storageService;
        this.mediator = mediator;
    }

    /// * Form method for sharing an image
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Product>> create(@ModelAttribute ProductWithImageInput input) {
        var creation = productService.create(input);
        return creation.map(ResponseEntity::ok);
    }

    /// * Form method for sharing an image
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/image/add/{id}")
    public Mono<ResponseEntity<Product>> insertImage(@ModelAttribute ImageListWrapper wrapper, @PathVariable String id) {
        return productService
                .findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException()))
                .flatMap(product -> mediator.fromFilePart(product, wrapper.getFiles()))
                .map(ResponseEntity::ok);
    }

    @PatchMapping("/fields/{id}")
    public Mono<ResponseEntity<Product>> partialUpdate(@ModelAttribute ProductInput input, @PathVariable String id) {
        var update = productService.update(input, id); // a
        return update.map(ResponseEntity::ok);
    }
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return productService.delete(id).map(ResponseEntity::ok);
    }

    //
    @GetMapping
    public Mono<List<Product>> get(
            @RequestParam(defaultValue = "0") Long offset,
            @RequestParam(defaultValue = "10") Integer max) {
        return productService.get(offset, max);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Product>> get(@PathVariable String id) {
        return productService.findById(id).map(ResponseEntity::ok);
    }

    // * Image ----------------------------------------------
    @GetMapping("/image/{path}")
    public Mono<ResponseEntity<Resource>> getImage(@PathVariable String path) {
        var adapter = new PathMediaTypeAdapter(path);
        return storageService.get(adapter)
                .map(content -> {
                    var resource = new ByteArrayResource(content);
                    return ResponseEntity.ok().contentType(adapter.getMediaType()).body(resource);
                });
    }
}
