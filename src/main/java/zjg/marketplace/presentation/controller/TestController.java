package zjg.marketplace.presentation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zjg.marketplace.core.logging.services.Logger;

//@RestController
@RequestMapping("/test")
public class TestController {
    private final Logger logger;

    public TestController(Logger logger) {
        this.logger = logger;
    }

    @PostMapping
    public void sendLog() {
        logger.info("");
    }
}
