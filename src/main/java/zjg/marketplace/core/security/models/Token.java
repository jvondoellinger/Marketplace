package zjg.marketplace.core.security.models;

import zjg.marketplace.core.security.access.RoleAccess;

import java.util.Date;

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
        private RoleAccess role;
        private Date expireAt;

        public String getUserId() {
            return userId;
        }
        public Date getExpireAt() {
            return expireAt;
        }
        public RoleAccess getRole() {
            return role;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
        public void setExpireAt(Date expireAt) {
            this.expireAt = expireAt;
        }
        private void setRole(RoleAccess role) {
            this.role = role;
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
        public DecryptedTokenBuilder role(RoleAccess role) {
            token.setRole(role);
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
