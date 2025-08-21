package zjg.marketplace;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import zjg.marketplace.application.cleanup.CleanCacheOnStartTests;
import zjg.marketplace.core.product.repository.query.ProductRepositoryQuery;

@SpringBootTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class MarketplaceApplicationTests {

	@Test
	void contextLoads() {
	}

}
