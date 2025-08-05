package zjg.marketplace.application.config.app;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppInfo {
    private final ApplicationContext context;
    public AppInfo(ApplicationContext context) {
        this.context = context;
    }

    public String getAppName() {
        return context.getEnvironment().getProperty("spring.application.name", "zjg_marketplace");
    }
}
