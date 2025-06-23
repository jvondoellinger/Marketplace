package zjg.marketplace.core.factory;

import org.springframework.stereotype.Service;
import zjg.marketplace.anotation.BadCode;
import zjg.marketplace.core.entity.Product;
import zjg.marketplace.core.entity.ProductBuilder;
import java.math.BigDecimal;

// Não me parece um código limpo! Depois vou melhorar isso!

@BadCode
@Service
public class ProductFactory {
    public Product factory(String title, String description, BigDecimal amount, String path) {
        var product = ProductBuilder.builder()
                .title(title)
                .description(description)
                .amount(amount)
                .imagePath(path)
                .build();
        return product;
    }
}
