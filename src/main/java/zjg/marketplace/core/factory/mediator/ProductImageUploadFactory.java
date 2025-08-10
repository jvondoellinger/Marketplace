package zjg.marketplace.core.factory.mediator;

import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.interfaces.services.repository.Repository;
import zjg.marketplace.core.interfaces.services.repository.command.CommandRepository;
import zjg.marketplace.core.interfaces.services.storage.StorageService;
import zjg.marketplace.core.mediator.ProductImageUploadMediator;

public class ProductImageUploadFactory {

    private ProductImageUploadFactory() {}

    public static ProductImageUploadMediator factory(CommandRepository<Product> repository, StorageService storageService) {
        return new ProductImageUploadMediator(repository, storageService);
    }
}
