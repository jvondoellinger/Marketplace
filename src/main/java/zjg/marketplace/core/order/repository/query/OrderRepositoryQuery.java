package zjg.marketplace.core.order.repository.query;

import zjg.marketplace.core.interfaces.services.repository.query.*;
import zjg.marketplace.core.order.entity.Order;

public interface OrderRepositoryQuery extends QueryRepository<Order>, QueryByUserIdRepository<Order> {
}
