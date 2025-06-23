package zjg.marketplace.core.strategy.impl.user;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.strategy.interfaces.IValidator;
import zjg.marketplace.core.utils.ThrowUtils;

public class PhoneValidator implements IValidator<User> {
    final String INVALID_DDD_FORMAT = "Invalid Area Code! Please, send a area code in format: DDD (always in number, no spaces)";
    final String INVALID_COUNTRY_CODE = "Invalid Coutry Code! Please, send a country code in format: +CC or CC (always in number, no spaces)";
    final String INVALID_NUMBER = "Invalid phone number! Please, send a phone number in format: 00000-0000 or 000000000 (always in number, no spaces)";
    @Override
    public void validate(User user) {
        // OBS: Regex faz as validações, reduzindo ifs e codições a mais (teoricamente)
        var phone = user.getPhone();
        ThrowUtils.throwIfTrue(!phone.getAreaCode().matches("\\d{1,3}"), INVALID_DDD_FORMAT, IllegalArgumentException.class);
        ThrowUtils.throwIfTrue(!phone.getCountryCode().matches("^\\+?\\d{1,4}$"), INVALID_COUNTRY_CODE, IllegalArgumentException.class);
        ThrowUtils.throwIfTrue(!phone.getNumber().matches("^\\d{5}-?\\d{4}$"), INVALID_NUMBER);
    }
}
