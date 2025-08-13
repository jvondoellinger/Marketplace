package zjg.marketplace.core.chain.interfaces;

public interface NextHandler<TService> {
    void setNext(TService service);
}
