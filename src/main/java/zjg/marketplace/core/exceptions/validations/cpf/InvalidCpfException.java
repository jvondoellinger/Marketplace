package zjg.marketplace.core.exceptions.validations.cpf;

public class InvalidCpfException extends CpfException {
    public InvalidCpfException() {
    }

    public InvalidCpfException(String message) {
        super(message);
    }

    public InvalidCpfException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCpfException(Throwable cause) {
        super(cause);
    }
}

