package zjg.marketplace.core.user.strategies.validators;

import zjg.marketplace.core.user.strategies.validators.exceptions.phone.InvalidAreaCodeException;
import zjg.marketplace.core.user.strategies.validators.exceptions.phone.InvalidCountryCodeException;
import zjg.marketplace.core.user.strategies.validators.exceptions.phone.InvalidPhoneNumberException;
import zjg.marketplace.core.strategy.interfaces.Validator;
import zjg.marketplace.core.user.entity.User;

public class PhoneValidator implements Validator<User> {
    final String INVALID_DDD_FORMAT = "Invalid Area Code! Please, send a area code in format: DDD (always in number, no spaces)";
    final String INVALID_COUNTRY_CODE = "Invalid Coutry Code! Please, send a country code in format: +CC or CC (always in number, no spaces)";
    final String INVALID_NUMBER = "Invalid phone number! Please, send a phone number in format: 00000-0000 or 000000000 (always in number, no spaces)";
    @Override
    public void validate(User user) {
        var phone = user.getPhone();
        if(!phone.getAreaCode().matches("\\d{1,3}")) throw new InvalidAreaCodeException(INVALID_DDD_FORMAT);
        if(!phone.getCountryCode().matches("^\\+?\\d{1,4}$")) throw new InvalidCountryCodeException(INVALID_COUNTRY_CODE);
        if(!phone.getNumber().matches("^\\d{5}-?\\d{4}$")) throw new InvalidPhoneNumberException(INVALID_NUMBER);
    }
}
