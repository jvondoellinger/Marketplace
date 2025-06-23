package zjg.marketplace.core.factory.chain.validators;

import zjg.marketplace.core.chain.impl.validators.ValidationHandler;
import zjg.marketplace.core.chain.interfaces.IHandler;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.impl.common.validators.AmountValidator;
import zjg.marketplace.core.strategy.impl.product.validators.DescriptionValidator;
import zjg.marketplace.core.strategy.impl.product.validators.ImagePathValidator;
import zjg.marketplace.core.strategy.impl.product.validators.TitleValidator;

public class ProductValidatorHandlerFactory {
    public ProductValidatorHandlerFactory() {}

    public static IHandler<Product> factory() {
        var chain = new ValidationHandler<Product>();
        chain.setNext(new AmountValidator<Product>());
        chain.setNext(new DescriptionValidator());
        chain.setNext(new ImagePathValidator());
        chain.setNext(new TitleValidator());
        return chain;
    }
}
