package zjg.marketplace.core.order.states.exceptions;

public class IllegalStateChangeException extends RuntimeException {
      public IllegalStateChangeException() {
      }

      public IllegalStateChangeException(String message) {
            super(message);
      }

      public IllegalStateChangeException(String message, Throwable cause) {
            super(message, cause);
      }

      public IllegalStateChangeException(Throwable cause) {
            super(cause);
      }

      public IllegalStateChangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
      }
}
