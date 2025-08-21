package zjg.marketplace.core.order.strategies.valdiators;

import zjg.marketplace.core.order.strategies.valdiators.exceptions.BlankBuyerIdException;
import zjg.marketplace.core.order.strategies.valdiators.exceptions.NullBuyerIdException;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.strategy.interfaces.Validator;

import java.util.Objects;

public class BuyerIdValidator implements Validator<Order> {
    private static final String NULL_MESSAGE = "BuyerId cannot be null!";
    private static final String BLANK_MESSAGE = "BuyerId cannot be blank!";
    @Override
    public void validate(Order order) {
        var buyerId = order.getBuyerId();
        if(Objects.isNull(buyerId)) throw new NullBuyerIdException(NULL_MESSAGE);
        if(buyerId.isBlank()) throw new BlankBuyerIdException(BLANK_MESSAGE);
    }
}
