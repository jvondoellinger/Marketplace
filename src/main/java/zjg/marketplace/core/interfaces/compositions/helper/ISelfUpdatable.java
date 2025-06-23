package zjg.marketplace.core.interfaces.compositions.helper;

@Deprecated
public interface ISelfUpdatable<T> {
    T selfUpdate(T partial);
}
