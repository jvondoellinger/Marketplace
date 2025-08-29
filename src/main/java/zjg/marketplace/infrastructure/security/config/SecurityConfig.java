package zjg.marketplace.infrastructure.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import zjg.marketplace.core.security.access.*;
import zjg.marketplace.presentation.filter.JwtJweAuthWebFilter;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
    private static final String[] roles = extractRules();
    private final JwtJweAuthWebFilter filter;

      public SecurityConfig(JwtJweAuthWebFilter filter) {
            this.filter = filter;
      }

      // ! Parasitorio
    // ! O filtro obriga a ter um token
    // ! Aki não pasa pelo filtro, ou seja, perde autonomia
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
            return http
                  .addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION)
                  .authorizeExchange(exchanges -> exchanges
                              .pathMatchers(
                                      "/api/guest/**"
                              )
                              .permitAll()
                              .anyExchange()
                              .hasAnyRole(roles)
                  )
                  .csrf(ServerHttpSecurity.CsrfSpec::disable)
                  .build();
    }

    private static String[] extractRules() {
        var list = RoleAccessDiscovery.getRoleAccess();
        return list.stream()
                .map(RoleAccess::getRule)
                .toArray(String[]::new);
    }
}
