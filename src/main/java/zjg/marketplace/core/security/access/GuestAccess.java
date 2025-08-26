package zjg.marketplace.core.security.access;

public class GuestAccess implements RoleAccess {
      @Override
      public String getRule() {
            return "GUEST";
      }
}
