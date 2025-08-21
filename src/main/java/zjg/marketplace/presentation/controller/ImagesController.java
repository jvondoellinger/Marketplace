package zjg.marketplace.presentation.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.adapter.ProductImageUploadFacadeAdapter;
import zjg.marketplace.application.resolver.facade.ServiceResolverFacade;
import zjg.marketplace.application.service.promisse.FindService;
import zjg.marketplace.core.image.service.StorageService;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.presentation.adapter.path.PathMediaTypeAdapter;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImagesController {
    private final FindService<Product> findService;
    private final ProductImageUploadFacadeAdapter mediator;
    private final StorageService storageService;
    public ImagesController(ServiceResolverFacade facade,
                            ProductImageUploadFacadeAdapter mediator,
                            StorageService storageService) {
        this.findService = facade.resolveFind(Product.class);
        this.mediator = mediator;
        this.storageService = storageService;
    }

    /// * Form method for sharing an image
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/add/{id}")
    public Mono<ResponseEntity<Product>> insertImage(@RequestPart("files") List<FilePart> files, @PathVariable String id) {
        return findService
                .findByIdNoCache(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("ID not founded")))
                .flatMap(product -> mediator.fromFilePart(product, files))
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{path}")
    public Mono<ResponseEntity<Resource>> getImage(@PathVariable String path) {
        var adapter = new PathMediaTypeAdapter(path);
        return storageService.get(adapter)
                .map(content -> {
                    var resource = new ByteArrayResource(content);
                    return ResponseEntity.ok().contentType(adapter.getMediaType()).body(resource);
                });
    }
}
