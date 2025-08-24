package zjg.marketplace;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import zjg.marketplace.order.services.OrderCommandServiceTest;
import zjg.marketplace.order.services.OrderQueryServiceTest;
import zjg.marketplace.product.services.ProductCommandServiceTest;
import zjg.marketplace.product.services.ProductQueryServiceTest;
import zjg.marketplace.user.services.UserCommandServiceTest;
import zjg.marketplace.user.services.UserQueryServiceTest;

@Suite
@SelectClasses({
        UserQueryServiceTest.class,
        UserCommandServiceTest.class,
        ProductQueryServiceTest.class,
        ProductCommandServiceTest.class,
        OrderQueryServiceTest.class,
        OrderCommandServiceTest.class,
})
public class SuiteTest {
}
