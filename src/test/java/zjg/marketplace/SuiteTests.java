package zjg.marketplace;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import zjg.marketplace.application.services.product.ProductCommandServiceTest;
import zjg.marketplace.application.services.product.ProductQueryServiceTest;

@Suite
@SelectClasses({
        ProductQueryServiceTest.class,
        ProductCommandServiceTest.class
})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SuiteTests {
}
