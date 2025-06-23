package zjg.marketplace.core.strategy.impl.user;

import zjg.marketplace.core.entity.user.User;
import zjg.marketplace.core.strategy.interfaces.IValidator;
import zjg.marketplace.core.utils.ThrowUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class BirthdayValidator implements IValidator<User> {
    final String DATE_IS_TODAY = "The birthday can't be today...";
    final String UNDER_AGE = "Not available to minors!";

    @Override
    public void validate(User user) {
        var date = user.getBirthDay()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        var now = LocalDate.now();
        ThrowUtils.throwIfTrue(date.equals(now), DATE_IS_TODAY, IllegalArgumentException.class);
        ThrowUtils.throwIfTrue(Period.between(date, now).getYears() < 18, UNDER_AGE, IllegalArgumentException.class);
    }


}
