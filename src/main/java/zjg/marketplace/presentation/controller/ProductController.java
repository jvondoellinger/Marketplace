package zjg.marketplace.presentation.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.adapter.ProductImageUploadMediatorAdapter;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.*;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.interfaces.services.storage.StorageService;
import zjg.marketplace.presentation.adapter.path.PathMediaTypeAdapter;
import zjg.marketplace.application.dto.product.ProductInput;
import zjg.marketplace.core.entity.product.Product;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@BadCode
public class ProductController {
    private final ICreateService<Product, ProductInput> createService;
    private final IDeleteService<Product> deleteService;
    private final IFindService<Product> findService;
    private final IUpdateService<Product, ProductInput> updateService;
    private final ProductImageUploadMediatorAdapter mediator;
    private final StorageService storageService;

    public ProductController(ServiceResolverFacade facade,
                             StorageService storageService,
                             ProductImageUploadMediatorAdapter mediator) {
        this.createService = facade.resolveCreate(Product.class, ProductInput.class);
        this.updateService = facade.resolveUpdate(Product.class, ProductInput.class);
        this.findService = facade.resolveFind(Product.class);
        this.deleteService = facade.resolveDelete(Product.class);
        this.mediator = mediator;
        this.storageService = storageService;
    }

    /// * Form method for sharing an image
    @PostMapping()
    public Mono<ResponseEntity<Product>> create(@RequestBody ProductInput input) {
        return createService.create(input)
                .map(ResponseEntity::ok);
    }

    /// * Form method for sharing an image
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/image/add/{id}")
    public Mono<ResponseEntity<Product>> insertImage(@RequestPart("files") List<FilePart> files, @PathVariable String id) {
        return findService
                .findByIdNoCache(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("ID not founded")))
                .flatMap(product -> mediator.fromFilePart(product, files))
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return deleteService.deleteById(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> get(@PathVariable String id) {
        return findService.findById(id).map(ResponseEntity::ok);
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

    @PatchMapping("/{id}")
    public Mono<Product> update(@RequestBody ProductInput input, @PathVariable String id) {
        return updateService.update(input, id);
    }
}
