package zjg.marketplace.application.mapper;

import org.springframework.stereotype.Service;
import zjg.marketplace.core.anotation.BadCode;
import zjg.marketplace.presentation.input.ProductInput;
import zjg.marketplace.core.field.path.CustomPath;
import zjg.marketplace.core.entity.product.Product;
import zjg.marketplace.core.entity.product.ProductBuilder;
import zjg.marketplace.core.factory.product.ProductFactory;

import java.util.List;
import java.util.Objects;


///  Mapeia propriedades de um input para uma entidade!
@Service
public class ProductMapper {
    final ProductFactory factory;
    private ProductMapper(ProductFactory factory) {
        this.factory = factory;
    }

    @BadCode
    public final Product map(ProductInput input) {
        Objects.requireNonNull(input, "Input cannot be null");
        return ProductBuilder.builder()
                .title(input.getTitle())
                .description(input.getDescription())
                .amount(input.getAmount())
                .build();
    }

    public final Product map(ProductInput input, List<CustomPath> paths) {
        Objects.requireNonNull(input, "Input cannot be null");
        return factory.factory(input.getTitle(),
                input.getDescription(),
                input.getAmount(),
                paths);
    }
}
