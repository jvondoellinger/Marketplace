package zjg.marketplace.core.chain.interfaces;

public interface DualHandler<T> {
    void handle(T t1, T t2);
}
