package zjg.marketplace.core.chain.interfaces;

public interface IHandler<TObj> {
    void handle(TObj obj);
}
