package zjg.marketplace.core.interfaces.compositions.updater;

import zjg.marketplace.core.entity.base.BaseEntity;

public interface EntityUpdater<T extends BaseEntity> {
    T update(T partial);
}
