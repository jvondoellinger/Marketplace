package zjg.marketplace.application.order.dto;

import zjg.marketplace.core.product.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderOutputDto(List<String> productId,
                             String state,
                             BigDecimal amount) {}
