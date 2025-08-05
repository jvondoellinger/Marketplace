package zjg.marketplace.core.chain.interfaces;

public interface Handler<TObj> {
    void handle(TObj obj);
}
