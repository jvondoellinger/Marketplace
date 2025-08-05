package zjg.marketplace.core.interfaces.services.security;

import zjg.marketplace.core.exceptions.security.token.ErrorOnGenerateTokenException;
import zjg.marketplace.core.exceptions.validations.token.ExpiredTokenException;
import zjg.marketplace.core.exceptions.validations.token.InvalidTokenException;
import zjg.marketplace.core.valueObjects.security.RoleAccess;
import zjg.marketplace.core.valueObjects.security.Token;

public interface TokenAuthenticator {
    Token.EncryptedToken encrypt(String identifier, RoleAccess role) throws ErrorOnGenerateTokenException;
    Token.DecryptedToken decrypt(String token) throws InvalidTokenException, ExpiredTokenException;
}
