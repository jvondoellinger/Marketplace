package zjg.marketplace.core.user.strategies.validators;

import zjg.marketplace.core.user.strategies.validators.exceptions.birthday.UnderageException;
import zjg.marketplace.core.strategy.interfaces.Validator;
import zjg.marketplace.core.user.entity.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class BirthdayValidator implements Validator<User> {
    private static final int MINIMAL_AGE = 18;
    private static final String UNDER_AGE = "Not available to minors!";

    @Override
    public void validate(User user) {
        var now = LocalDate.now();
        var date = user.getBirthDay()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        if (Period.between(date, now).getYears() < MINIMAL_AGE) throw new UnderageException(UNDER_AGE);
    }
}
