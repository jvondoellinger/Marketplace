package zjg.marketplace.core.product.strategies.validators;

import zjg.marketplace.core.product.entity.Product;
import zjg.marketplace.core.product.strategies.validators.exceptions.title.BlankTitleException;
import zjg.marketplace.core.product.strategies.validators.exceptions.title.TitleTooLongException;
import zjg.marketplace.core.product.strategies.validators.exceptions.title.TitleTooShortException;
import zjg.marketplace.core.strategy.interfaces.Validator;

public class TitleValidator implements Validator<Product> {
    final static String BLANK_TITLE = "You provided a blank username! Please, send another username.";
    final static String SHORT_TITLE = "You provided a short username! Please, send a longer username.";
    final static String LONGER_TITLE = "You provided a short username! Please, send a longer username.";

    @Override
    public void validate(Product product) {
        var s = product.getTitle();
        var n = s.length();
        if(s.isBlank()) throw new BlankTitleException(BLANK_TITLE);
        if(n < 5) throw new TitleTooShortException(SHORT_TITLE);
        if(n > 255) throw new TitleTooLongException(LONGER_TITLE);
    }
}
