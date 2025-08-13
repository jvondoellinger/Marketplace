package zjg.marketplace.core.factory.mediator;

import zjg.marketplace.core.image.service.StorageService;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.product.facade.ProductImageUploadFacade;

public class ProductImageUploadFactory {

    private ProductImageUploadFactory() {}

    public static ProductImageUploadFacade factory(CommandRepository<Product> repository, StorageService storageService) {
        return new ProductImageUploadFacade(repository, storageService);
    }
}
