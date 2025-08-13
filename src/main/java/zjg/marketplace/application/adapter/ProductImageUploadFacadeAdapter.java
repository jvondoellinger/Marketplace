package zjg.marketplace.application.adapter;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import zjg.marketplace.application.mapper.ImageMapper;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.interfaces.services.storage.StorageService;
import zjg.marketplace.core.mediator.ProductImageUploadMediator;
import java.util.List;

@Service
public class ProductImageUploadMediatorAdapter extends ProductImageUploadMediator {
    private final ImageMapper mapper;

    public ProductImageUploadMediatorAdapter(CommandRepository<Product> command,
                                             StorageService storageService,
                                             ImageMapper mapper) {
        super(command, storageService);
        this.mapper = mapper;
    }

    public Mono<Product> fromFilePart(Product product, List<FilePart> fileParts) {
        return mapper.toImageFile(fileParts, product.getId())
                .collectList()
                .flatMap(files -> uploadImageInEntity(product, files));
    }
}