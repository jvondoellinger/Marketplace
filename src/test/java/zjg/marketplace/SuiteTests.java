package zjg.marketplace;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import zjg.marketplace.product.services.ProductCommandServiceTest;
import zjg.marketplace.product.services.ProductQueryServiceTest;

@Suite
@SelectClasses({
        ProductQueryServiceTest.class,
        ProductCommandServiceTest.class
})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SuiteTests {
}
