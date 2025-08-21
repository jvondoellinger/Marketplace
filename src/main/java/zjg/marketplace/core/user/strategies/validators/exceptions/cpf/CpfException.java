package zjg.marketplace.core.user.strategies.validators.exceptions.cpf;


public abstract class CpfException extends RuntimeException {
    public CpfException() {
        super();
    }

    public CpfException(String message) {
        super(message);
    }

    public CpfException(String message, Throwable cause) {
        super(message, cause);
    }

    public CpfException(Throwable cause) {
        super(cause);
    }
}
