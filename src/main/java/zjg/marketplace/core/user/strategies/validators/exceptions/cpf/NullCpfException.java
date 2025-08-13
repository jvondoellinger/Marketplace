package zjg.marketplace.core.exceptions.validations.cpf;

public class NullCpfException extends CpfException {
    public NullCpfException() {
    }

    public NullCpfException(String message) {
        super(message);
    }

    public NullCpfException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullCpfException(Throwable cause) {
        super(cause);
    }
}
