package zjg.marketplace.core.chain.impl.updater;

import zjg.marketplace.core.chain.interfaces.DualHandler;
import zjg.marketplace.core.chain.interfaces.NextHandler;
import zjg.marketplace.core.strategy.interfaces.UpdateStrategy;

import java.util.Objects;

public class EntityUpdaterHandler<T> implements DualHandler<T>, NextHandler<UpdateStrategy<T>> {
    protected EntityUpdaterHandler<T> next;
    protected UpdateStrategy<T> strategy;

    public EntityUpdaterHandler() {}
    private EntityUpdaterHandler(UpdateStrategy<T> strategy) {
        this.strategy = strategy;
    }

    @Override
    public void handle(T t1, T t2) {
        Objects.requireNonNull(t1, "The input t1 can't be null!");
        Objects.requireNonNull(t2, "The input t2 can't be null!");
        if(!Objects.isNull(strategy)) strategy.applyUpdate(t1, t2);
        if(!Objects.isNull(next)) next.handle(t1, t2);
    }

    @Override
    public void setNext(UpdateStrategy<T> obj) {
        if (next == null) {
            this.next = new EntityUpdaterHandler<>(obj);
        } else {
            this.next.setNext(obj);
        }
    }
}
