package zjg.marketplace.core.factory.product;

import org.springframework.stereotype.Service;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.core.field.path.CustomPath;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.entity.product.ProductBuilder;
import zjg.marketplace.core.factory.chain.ProductValidatorHandlerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Não me parece um código limpo! Depois vou melhorar isso!

@BadCode
@Service
public class ProductFactory {
    public Product factory(String title, String description, BigDecimal amount, List<CustomPath> paths) {
        var validator = ProductValidatorHandlerFactory.factory();
        var product = ProductBuilder.builder()
                .title(title)
                .description(description)
                .amount(amount)
                .imagePath(paths)
                .build();
        // Problemas com o path da imagem - ideal ter todos os paths antes da fabricação
        //validator.handle(product);
        return product;
    }
    public Product factory(String title, String description, BigDecimal amount, CustomPath path) {
        var validator = ProductValidatorHandlerFactory.factory();
        var product = ProductBuilder.builder()
                .title(title)
                .description(description)
                .amount(amount)
                .imagePath(new ArrayList<>())
                .imagePath(new ArrayList<>(List.of(path)))
                .build();
        // Problemas com o path da imagem - ideal ter todos os paths antes da fabricação
        //validator.handle(product);
        return product;
    }
}
