package zjg.marketplace.application.encrypt.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zjg.marketplace.core.interfaces.security.TextEncryptor;

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
    public Boolean compare(String encoded, String text) {
        return encoder.matches(text, encoded);
    }
}
