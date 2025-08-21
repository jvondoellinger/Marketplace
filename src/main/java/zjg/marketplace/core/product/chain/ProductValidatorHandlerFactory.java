package zjg.marketplace.core.product.chain;

import zjg.marketplace.core.chain.impl.validators.ValidationHandler;
import zjg.marketplace.core.chain.interfaces.Handler;
import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.product.strategies.validators.DescriptionValidator;
import zjg.marketplace.core.product.strategies.validators.ImagePathValidator;
import zjg.marketplace.core.product.strategies.validators.TitleValidator;
import zjg.marketplace.core.strategy.common.AmountValidator;

public class ProductValidatorHandlerFactory {
    public ProductValidatorHandlerFactory() {}

    public static Handler<Product> factory() {
        var chain = new ValidationHandler<Product>();
        chain.setNext(new AmountValidator<Product>());
        chain.setNext(new DescriptionValidator());
        chain.setNext(new ImagePathValidator());
        chain.setNext(new TitleValidator());
        return chain;
    }
}
