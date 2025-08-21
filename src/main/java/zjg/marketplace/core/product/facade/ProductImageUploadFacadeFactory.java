package zjg.marketplace.core.product.facade;

import zjg.marketplace.core.image.service.StorageService;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.product.entity.Product;

public class ProductImageUploadFacadeFactory {

    private ProductImageUploadFacadeFactory() {}

    public static ProductImageUploadFacade factory(CommandRepository<Product> repository, StorageService storageService) {
        return new ProductImageUploadFacade(repository, storageService);
    }
}
