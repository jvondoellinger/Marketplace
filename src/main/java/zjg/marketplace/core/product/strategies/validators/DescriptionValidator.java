package zjg.marketplace.core.product.strategies.validators;

import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.product.strategies.validators.exceptions.description.BlankDescriptionException;
import zjg.marketplace.core.product.strategies.validators.exceptions.description.DescriptionTooLongException;
import zjg.marketplace.core.product.strategies.validators.exceptions.description.DescriptionTooShortException;
import zjg.marketplace.core.strategy.interfaces.Validator;

public class DescriptionValidator implements Validator<Product> {
    final static String DEFAULT_MESSAGE = "You provided a invalid description! Please, send another description.";
    final static String SHORT_DESCRIPTION = "You provided a short description! Please, send a longer description.";
    final static String LONGER_DESCRIPTION = "You provided a short description! Please, send a longer description.";

    @Override
    public void validate(zjg.marketplace.core.product.entity.Product product) {
        var s = product.getDescription();
        var n = s.length();
        if(s.isBlank()) throw new BlankDescriptionException(DEFAULT_MESSAGE);
        if(n < 5) throw new DescriptionTooShortException(SHORT_DESCRIPTION);
        if(n > 5000) throw new DescriptionTooLongException(LONGER_DESCRIPTION);
    }
}
