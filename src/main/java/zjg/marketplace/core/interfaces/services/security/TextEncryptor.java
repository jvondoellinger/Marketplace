package zjg.marketplace.core.interfaces.services.security;

import zjg.marketplace.core.exceptions.security.user.IncorrectPasswordProvidedException;

public interface TextEncryptor {
    String encrypt(String text);
    boolean compare(String encoded, String text);
    void throwIfInvalid(String encoded, String text) throws IncorrectPasswordProvidedException;
}
