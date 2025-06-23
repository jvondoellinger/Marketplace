package zjg.marketplace.application.mapper;

import org.springframework.stereotype.Service;
import zjg.marketplace.anotation.BadCode;
import zjg.marketplace.api.input.ProductInput;
import zjg.marketplace.api.input.ProductWithImageInput;
import zjg.marketplace.core.entity.Product;
import zjg.marketplace.core.entity.ProductBuilder;
import zjg.marketplace.core.factory.ProductFactory;

import java.util.Objects;


///  Mapeia propriedades de um input para uma entidade!
@Service
public class ProductMapper {
    private ProductMapper() {

    }
    @BadCode
    public final Product map(ProductInput input) {
        Objects.requireNonNull(input, "Input cannot be null");
        return new ProductFactory()
                .factory(input.getTitle(),
                        input.getDescription(),
                        input.getAmount(),
                        null);
    }

    @BadCode
    public final Product map(ProductInput input, String pathImage) {
        Objects.requireNonNull(input, "Input cannot be null");
        Objects.requireNonNull(pathImage, "Path cannot be null");
        return new ProductFactory()
                .factory(input.getTitle(),
                        input.getDescription(),
                        input.getAmount(),
                        pathImage);
    }

    public final Product map(ProductWithImageInput input, String path) {
        Objects.requireNonNull(input, "Input cannot be null");
        Objects.requireNonNull(path, "Path cannot be null");
        return ProductBuilder.builder()
                .title(input.getTitle())
                .description(input.getDescription())
                .amount(input.getAmount())
                .imagePath(path)
                .build();
    }
}
