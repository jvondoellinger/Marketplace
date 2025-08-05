package zjg.marketplace.application.dto.product;

import java.math.BigDecimal;

public class ProductInput {
    private String title;
    private String description;
    private BigDecimal amount;

    // *******************************************************
    // Getter
    public BigDecimal getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }
    public String getTitle() {
        return title;
    }

    // *******************************************************
    // Setter
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // *******************************************************
}
