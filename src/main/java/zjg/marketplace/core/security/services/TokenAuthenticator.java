package zjg.marketplace.core.security.services;

import zjg.marketplace.core.security.exceptions.ErrorOnGenerateTokenException;
import zjg.marketplace.core.security.exceptions.ExpiredTokenException;
import zjg.marketplace.core.security.exceptions.InvalidTokenException;
import zjg.marketplace.core.security.access.RoleAccess;
import zjg.marketplace.core.security.models.Token;

public interface TokenAuthenticator {
    Token.EncryptedToken encrypt(String identifier, RoleAccess role) throws ErrorOnGenerateTokenException;
    Token.DecryptedToken decrypt(String token) throws InvalidTokenException, ExpiredTokenException;
}
