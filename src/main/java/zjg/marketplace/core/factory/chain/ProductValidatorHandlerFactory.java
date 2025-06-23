package zjg.marketplace.core.factory.chain;

import zjg.marketplace.core.chain.impl.ProductValidationHandler;
import zjg.marketplace.core.strategy.impl.product.AmountValidator;
import zjg.marketplace.core.strategy.impl.product.DescriptionValidator;
import zjg.marketplace.core.strategy.impl.product.ImagePathValidator;
import zjg.marketplace.core.strategy.impl.product.TitleValidator;

public class ProductValidatorHandlerFactory {
    public ProductValidatorHandlerFactory() {}

    public static ProductValidationHandler factory() {
        var instance = new ProductValidationHandler();
        instance.setNext(new AmountValidator());
        instance.setNext(new DescriptionValidator());
        instance.setNext(new ImagePathValidator());
        instance.setNext(new TitleValidator());
        return instance;
    }
}
