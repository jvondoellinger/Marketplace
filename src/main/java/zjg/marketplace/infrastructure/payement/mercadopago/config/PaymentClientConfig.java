package zjg.marketplace.infrastructure.payement.mercadopago.config;

import com.mercadopago.MercadoPagoConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class PaymentClientConfig {
    @Value("${MERCADO_PAGO_SECRET_KEY}")
    private String PRIVATE_ACCESS_KEY;

    @PostConstruct
    public void paymentClient() {
        MercadoPagoConfig.setAccessToken(PRIVATE_ACCESS_KEY);
    }
}
