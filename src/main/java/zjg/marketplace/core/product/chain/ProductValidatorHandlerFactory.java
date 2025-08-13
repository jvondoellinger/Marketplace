package zjg.marketplace.core.products.chain;

import zjg.marketplace.core.chain.impl.validators.ValidationHandler;
import zjg.marketplace.core.chain.interfaces.Handler;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.impl.common.validators.AmountValidator;
import zjg.marketplace.core.strategy.impl.product.validators.DescriptionValidator;
import zjg.marketplace.core.strategy.impl.product.validators.ImagePathValidator;
import zjg.marketplace.core.strategy.impl.product.validators.TitleValidator;

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
