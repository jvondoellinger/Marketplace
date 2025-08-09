package zjg.marketplace.core.context;

import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.entity.base.BaseEntity;
import zjg.marketplace.core.events.models.DomainEvent;
import zjg.marketplace.core.interfaces.services.repository.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface Context<TEntity extends BaseEntity, TContext extends Context> {
    TContext operation(Function<Repository<TEntity>, TEntity> func);
    @BadCode
    TContext events(List<DomainEvent> events);
    CompletableFuture<Void> commit();
}
