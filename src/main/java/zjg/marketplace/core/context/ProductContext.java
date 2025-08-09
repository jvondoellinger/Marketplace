package zjg.marketplace.core.context;

import zjg.marketplace.core.chain.interfaces.IDualHandler;
import zjg.marketplace.core.entity.file.BasicImageFile;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.events.models.DomainEvent;
import zjg.marketplace.core.interfaces.services.repository.Repository;
import zjg.marketplace.core.interfaces.services.storage.StorageService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ProductContext implements Context<Product, ProductContext> {
    private final Repository<Product> repository;
    private final StorageService storage;
    private final IDualHandler<Product> handler;

    protected ProductContext(Repository<Product> repository, StorageService storage, IDualHandler<Product> handler) {
        this.repository = repository;
        this.storage = storage;
        this.handler = handler;
    }
    @Override
    public ProductContext operation(Function<Repository<Product>, Product> func) {
        func.apply(repository);
        return this;
    }
    public ProductContext images(List<BasicImageFile> files) {
        return this;
    }
    public ProductContext events(List<DomainEvent> events) {
        return this;
    }

    public CompletableFuture<Void> commit() {
        return CompletableFuture.runAsync(() -> {return;});
    }
}
