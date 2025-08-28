package zjg.marketplace.presentation.config;

import java.util.List;

public class PublicPaths {
      private PublicPaths() {}
      public static final List<String> paths = List.of(
              "/api/guest",
              "/v3/api-docs",
              "/swagger-ui",
              "/webjars"
      );
}
