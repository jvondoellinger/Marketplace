package zjg.marketplace.core.chain.impl;

import zjg.marketplace.core.chain.abstractions.AbstractValidationHandler;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.strategy.interfaces.IValidator;

public class ProductValidationHandler extends AbstractValidationHandler<Product> {
    // Constructures
    public ProductValidationHandler(IValidator<Product> validatorHandler) {
        super(validatorHandler);
    }
    public ProductValidationHandler() {}

    // Overrides
    @Override
    protected AbstractValidationHandler<Product> self() {
        return this;
    }

    @Override
    protected AbstractValidationHandler<Product> self(IValidator<Product> validatorHandler) {
        return new ProductValidationHandler(validatorHandler);
    }

}