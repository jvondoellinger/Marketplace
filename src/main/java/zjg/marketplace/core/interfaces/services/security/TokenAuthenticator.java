package zjg.marketplace.core.interfaces.services.security;

import zjg.marketplace.core.exceptions.security.token.ErrorOnGenerateTokenException;
import zjg.marketplace.core.exceptions.validations.token.ExpiredTokenException;
import zjg.marketplace.core.exceptions.validations.token.InvalidTokenException;
import zjg.marketplace.core.valueObject.security.Token;

public interface TokenAuthenticator {
    Token.EncryptedToken generate(String identifier) throws ErrorOnGenerateTokenException;
    Token.DecryptedToken validate(String token) throws InvalidTokenException, ExpiredTokenException;
}
