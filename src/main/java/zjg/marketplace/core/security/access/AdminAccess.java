package zjg.marketplace.core.security.access;

public class AdminAccess implements RoleAccess {
      @Override
      public String getRule() {
            return "ADMIN";
      }
}
