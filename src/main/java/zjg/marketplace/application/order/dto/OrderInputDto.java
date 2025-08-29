package zjg.marketplace.application.order.dto;

import java.util.List;

public record OrderInputDto (String userId,
        List<String> productsId) {}
