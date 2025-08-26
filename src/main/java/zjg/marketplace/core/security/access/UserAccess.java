package zjg.marketplace.core.security.access;

public class UserAccess implements RoleAccess {
      @Override
      public String getRule() {
            return "USER";
      }
}
