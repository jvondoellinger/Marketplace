package zjg.marketplace.core.chain.interfaces;

public interface INextHandler<TService> {
    void setNext(TService service);
}
