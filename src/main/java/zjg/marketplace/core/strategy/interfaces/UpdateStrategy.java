package zjg.marketplace.core.strategy.interfaces;

public interface UpdateStrategy<T> {
    void applyUpdate(T target, T source);
}
