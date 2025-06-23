package zjg.marketplace.application.security.encrypt.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zjg.marketplace.core.exceptions.security.user.IncorrectPasswordProvidedException;
import zjg.marketplace.core.interfaces.services.security.TextEncryptor;

import java.util.Objects;

@Service
public class BcryptTextEncryptor implements TextEncryptor {
    private final BCryptPasswordEncoder encoder;
    public BcryptTextEncryptor() {
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encrypt(String plainText) {
        if(Objects.isNull(plainText)) return null;
        if(plainText.isBlank()) return null;
        return encoder.encode(plainText);
    }

    @Override
    public boolean compare(String encoded, String text) {
        return encoder.matches(text, encoded);
    }

    @Override
    public void throwIfInvalid(String encoded, String text) throws IncorrectPasswordProvidedException {
        if(!encoder.matches(text, encoded)){
            throw new IncorrectPasswordProvidedException();
        }
    }
}
