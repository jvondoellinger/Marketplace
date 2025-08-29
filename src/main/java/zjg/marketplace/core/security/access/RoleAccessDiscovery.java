package zjg.marketplace.core.security.access;

import zjg.marketplace.core.anotation.BadCode;

import java.util.List;

@BadCode
public class RoleAccessDiscovery {
      private static final List<RoleAccess> list = List.of(
              new GuestAccess(),
              new UserAccess(),
              new AdminAccess()
      );

      public static final List<RoleAccess> getRoleAccess() {
            return list;
      }

      public static RoleAccess discovery(String role) {
            return list.stream()
                    .filter(x -> x.getRule().equals(role)).findFirst()
                    .orElseThrow(() -> new RuntimeException("Any roles found by this string value!"));
      }
}
