package zjg.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(scanBasePackages = "zjg.marketplace.*")
@EnableReactiveMongoRepositories
@EnableMongoRepositories
@EnableWebFlux
@EnableCaching
public class MarketplaceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
	}

}
