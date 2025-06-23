package zjg.marketplace.core.entity.payment;

import org.springframework.data.mongodb.core.mapping.Document;
import zjg.marketplace.core.entity.order.Order;

import java.math.BigDecimal;

@Document
public class PixPayment extends Payment {
    // Constructors
    public PixPayment(Order order, BigDecimal amount) {
        super(order, amount);
    }
    public PixPayment(Order order, BigDecimal amount, String qrCodeBase64) {
        super(order, amount);
        this.qrCodeBase64 = qrCodeBase64;
    }
    // Properties
    private String qrCodeBase64; // (Está nessa entidade porquê AINDA nãoi havera outras formas de pagamento)

    // Getter
    public String getQrCodeBase64() {
        return qrCodeBase64;
    }

    // Setter
    protected void setQrCodeBase64(String qrCodeBase64) {
        this.qrCodeBase64 = qrCodeBase64;
    }
}
