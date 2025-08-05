package zjg.marketplace.presentation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zjg.marketplace.core.interfaces.services.logging.ILogger;

//@RestController
@RequestMapping("/test")
public class TestController {
    private final ILogger logger;

    public TestController(ILogger logger) {
        this.logger = logger;
    }

    @PostMapping
    public void sendLog() {
        logger.info("");
    }
}
