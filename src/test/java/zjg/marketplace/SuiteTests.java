package zjg.marketplace;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import zjg.marketplace.application.cleanup.CleanCacheOnStartTests;
import zjg.marketplace.core.product.repository.query.ProductRepositoryQuery;

@Suite
@SelectClasses({
        CleanCacheOnStartTests.class,
        ProductRepositoryQuery.class
})
public class SuiteTests {
}
