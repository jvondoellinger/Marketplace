package zjg.marketplace.core.interfaces.security;

public interface TextEncryptor {
    String encrypt(String text);
    Boolean compare(String encoded, String text);
}
