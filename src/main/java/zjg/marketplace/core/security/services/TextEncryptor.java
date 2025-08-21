package zjg.marketplace.core.security.services;

import zjg.marketplace.core.user.auth.exceptions.IncorrectPasswordProvidedException;

public interface TextEncryptor {
    String encrypt(String text);
    boolean compare(String encoded, String text);
    void throwIfInvalid(String encoded, String text) throws IncorrectPasswordProvidedException;
}
