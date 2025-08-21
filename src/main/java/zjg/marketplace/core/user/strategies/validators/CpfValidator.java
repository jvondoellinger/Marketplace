package zjg.marketplace.core.user.strategies.validators;

import zjg.marketplace.core.user.strategies.validators.exceptions.cpf.CpfAllDigitsEqualException;
import zjg.marketplace.core.user.strategies.validators.exceptions.cpf.CpfLengthInvalidException;
import zjg.marketplace.core.user.strategies.validators.exceptions.cpf.InvalidCpfException;
import zjg.marketplace.core.user.strategies.validators.exceptions.cpf.NullCpfException;
import zjg.marketplace.core.strategy.interfaces.Validator;
import zjg.marketplace.core.user.entity.User;

import java.util.Objects;

public class CpfValidator implements Validator<User> {
    private static final String INVALID_LENGTH_MESSAGE = "The provided CPF contains a invalid length";
    private static final String ALL_DIGITS_EQUALS_MESSAGE = "The provided CPF contains all the same digits.";
    private static final String INVALID_CPF_MESSAGE = "The provided a invalid CPF. Please, send a valid CPF!";
    private static final String NULL_DOCUMENT_PROVIDED = "The provided CPF are null.";
    @Override
    public void validate(User user) {
        var document = user.getDocument();
        if(Objects.isNull(document)) throw new NullCpfException(NULL_DOCUMENT_PROVIDED);
        var cpf = document.getCpf();
        var digits = cpf.replaceAll("\\D", "");
        if(digits.length() != 11) throw new CpfLengthInvalidException(INVALID_LENGTH_MESSAGE);
        if(cpf.chars().distinct().count() == 1) throw new CpfAllDigitsEqualException(ALL_DIGITS_EQUALS_MESSAGE);
        if(!check(digits)) throw new InvalidCpfException(INVALID_CPF_MESSAGE);
    }

    private boolean check(String digits) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            sum1 += digit * (10 - i);
            sum2 += digit * (11 - i);
        }
        int digit10 = Character.getNumericValue(digits.charAt(9));
        sum2 += digit10 * 2;

        int check1 = 11 - (sum1 % 11);
        check1 = (check1 >= 10) ? 0 : check1;

        int check2 = 11 - (sum2 % 11);
        check2 = (check2 >= 10) ? 0 : check2;

        return (check1 == digit10) && (check2 == Character.getNumericValue(digits.charAt(10)));
    }
}
