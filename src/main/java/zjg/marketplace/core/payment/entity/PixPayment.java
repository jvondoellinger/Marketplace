package zjg.marketplace.core.entity.payment;

import org.springframework.data.mongodb.core.mapping.Document;
import zjg.marketplace.core.order.entity.Order;

@Document
public class PixPayment extends Payment {
    // Constructors
    public PixPayment(Order order) {
        super(order);
    }
    public PixPayment(Order order,String qrCodeBase64, String code) {
        super(order);
        this.qrCodeBase64 = qrCodeBase64;
        this.code = code;
    }
    // Properties
    private String qrCodeBase64; // (Está nessa entidade porquê AINDA nãoi havera outras formas de pagamento)
    private String code; //

    // Getter
    public String getQrCodeBase64() {
        return qrCodeBase64;
    }
    public String getCode() {
        return code;
    }

    // Setter
    protected void setQrCodeBase64(String qrCodeBase64) {
        this.qrCodeBase64 = qrCodeBase64;
    }
    protected void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "PixPayment{" +
                "qrCodeBase64='" + qrCodeBase64 + '\'' +
                '}';
    }
}
