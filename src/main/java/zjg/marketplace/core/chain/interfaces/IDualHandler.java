package zjg.marketplace.core.chain.interfaces;

public interface IDualHandler<T> {
    void handle(T t1, T t2);
}
