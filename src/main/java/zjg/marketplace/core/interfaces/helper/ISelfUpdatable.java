package zjg.marketplace.core.interfaces.helper;

public interface ISelfUpdatable<T> {
    T selfUpdate(T partial);
}
