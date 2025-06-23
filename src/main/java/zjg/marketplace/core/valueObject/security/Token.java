package zjg.marketplace.core.valueObject.security;

import java.util.Date;
import java.util.Timer;
import java.util.UUID;

public class Token {


    public static class EncryptedToken {
        private EncryptedToken() {}
        private String token;

        public String getToken() {
            return token;
        }

        protected void setToken(String token) {
            this.token = token;
        }
    }
    public static class EncryptedTokenBuilder {
        private final EncryptedToken token;
        private EncryptedTokenBuilder() {
            token = new EncryptedToken();
        }
        public static EncryptedTokenBuilder builder() {
            return new EncryptedTokenBuilder();
        }
        public EncryptedTokenBuilder token(String token) {
            this.token.setToken(token);
            return this;
        }
        public EncryptedToken build() {
            return this.token;
        }
    }

    public static class DecryptedToken {
        private String userId;
        private Date expireAt;

        public String getUserId() {
            return userId;
        }

        public Date getExpireAt() {
            return expireAt;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setExpireAt(Date expireAt) {
            this.expireAt = expireAt;
        }
    }
    public static class DecryptedTokenBuilder {
        private final DecryptedToken token;
        private DecryptedTokenBuilder() {
            token = new DecryptedToken();
        }

        public static DecryptedTokenBuilder builder() {
            return new DecryptedTokenBuilder();
        }
        public DecryptedTokenBuilder userId(String identifier) {
            token.setUserId(identifier);
            return this;
        }
        public DecryptedTokenBuilder expireAt(Date date) {
            token.setExpireAt(date);
            return this;
        }
        public DecryptedToken build() {
            return token;
        }
    }
}
