package zjg.marketplace.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

@Deprecated
public class ThrowUtils {
    public static void throwIfTrue(Boolean bool, String message) {
        if(bool) {
            throw new RuntimeException(message);
        }
    }

    public static <E extends RuntimeException> void throwIfTrue(Boolean bool, String message, Class<E> exception ) {
        if(bool) {
            try{
                var constructor = exception.getConstructor(String.class);
                throw constructor.newInstance(message);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | RuntimeException ex) {
                throw new RuntimeException("Impossible instanciate this ewxception", ex);
            }
        }
    }

    public static <E extends RuntimeException> void throwIfTrue(Boolean bool, String message, Supplier<E> exceptionSupplier ) {
        if(bool) {
            throw exceptionSupplier.get();
        }
    }
}
