package zjg.marketplace.application.security.authenticator.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.stereotype.Service;
import zjg.marketplace.application.config.app.AppInfo;
import zjg.marketplace.application.security.authenticator.config.ApiKeyConfig;
import zjg.marketplace.core.exceptions.security.token.ErrorOnGenerateTokenException;
import zjg.marketplace.core.exceptions.validations.token.InvalidTokenException;
import zjg.marketplace.core.interfaces.services.security.TokenAuthenticator;
import zjg.marketplace.core.valueObjects.security.RoleAccess;
import zjg.marketplace.core.valueObjects.security.Token;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenService implements TokenAuthenticator {
    private final ApiKeyConfig config;
    private final AppInfo info;
    private DirectEncrypter directEncrypter;
    private DirectDecrypter directDecrypter;

    public TokenService(ApiKeyConfig config, AppInfo info) {
        this.config = config;
        this.info = info;
        init();
    }

    @Override
    public Token.EncryptedToken encrypt(String identifier, RoleAccess role) throws ErrorOnGenerateTokenException{
        var exp = Date.from(Instant.now().plus(Duration.ofHours(1)));
        var claimSet = new JWTClaimsSet.Builder()
                .subject(identifier)
                .claim("role", role.toString())
                .issuer(info.getAppName())
                .expirationTime(exp)
                .build();
        var header = new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM)
                .contentType("JWT")
                .build();
        var encryptedJwt = new EncryptedJWT(header, claimSet);
        String token = null;
        try{
            encryptedJwt.encrypt(directEncrypter);
            token = encryptedJwt.serialize();
        } catch (JOSEException e) {
            throw new ErrorOnGenerateTokenException(e);
        }
        return Token.EncryptedTokenBuilder.builder()
                .token(token)
                .build();
    }

    @Override
    public Token.DecryptedToken decrypt(String token) throws InvalidTokenException {
        try{
            var encryptedJwe = EncryptedJWT.parse(token);
            encryptedJwe.decrypt(directDecrypter);
            var claimSet = encryptedJwe.getJWTClaimsSet();;
            var decryptedToken = Token.DecryptedTokenBuilder.builder()
                    .userId(claimSet.getSubject())
                    .expireAt(claimSet.getExpirationTime())
                    .role(RoleAccess.valueOf(claimSet.getStringClaim("role")))
                    .build();
            if(decryptedToken.getExpireAt().before(new Date())) throw new InvalidTokenException();
            return decryptedToken;
        } catch (JOSEException | ParseException e) {
            throw new InvalidTokenException(e);
        }
    }

    private void init() {
        try {
            var bytes = Base64.getDecoder().decode(config.getApiKey());
            directEncrypter = new DirectEncrypter(bytes);
            directDecrypter = new DirectDecrypter(bytes);
        } catch (KeyLengthException e) {
            throw new RuntimeException(e);
        }
    }
}
