package zjg.marketplace.application.adapter;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.mapper.BinaryMapper;
import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.factory.file.ImageFileFactory;
import zjg.marketplace.core.field.extension.ImageExtension;
import zjg.marketplace.core.interfaces.repository.IRepository;
import zjg.marketplace.core.interfaces.storage.StorageService;
import zjg.marketplace.core.mediator.ProductImageUploadMediator;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageUploadMediatorAdapter extends ProductImageUploadMediator {
    private final BinaryMapper mapper;
    public ProductImageUploadMediatorAdapter(IRepository<Product> repository, StorageService storageService, BinaryMapper mapper) {
        super(repository, storageService);
        this.mapper = mapper;
    }

    public Mono<Product> fromFilePart(Product product, FilePart filePart) {
        return mapper.toByteArray(filePart).flatMap(binary -> {
            return uploadImage(product, binary, new ImageExtension(filePart.filename()));
        });
    }
    public Mono<Product> fromFilePart(Product product, List<FilePart> fileParts) {
        return Flux.fromIterable(fileParts)
                .flatMap(file -> fromFilePart(product, file))
                .collectList()
                .thenReturn(product);
    }
}
