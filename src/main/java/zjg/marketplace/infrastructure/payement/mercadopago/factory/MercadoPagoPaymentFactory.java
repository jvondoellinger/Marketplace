package zjg.marketplace.infrastructure.payement.mercadopago.factory;

import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import zjg.marketplace.core.order.entity.Order;
import zjg.marketplace.core.user.entity.User;

import java.rmi.UnexpectedException;

public class MercadoPagoPaymentFactory {
    public static Payment factoryPixPayment(User user, Order order) {
        var client = new PaymentClient();
        var identification = factoryIdentification(user);
        var payer = factoryPayer(user, identification);
        var request = PaymentCreateRequest.builder()
                .transactionAmount(order.getAmount())
                .description(String.format("Order: %s", order.getId()))
                .paymentMethodId("pix")
                .payer(payer)
                .build();
        try {
            var payment = client.create(request);
            if("pending".equals(payment.getStatus())){
                return payment;
            } else {
                throw new UnexpectedException("An unexpected error occurred with this request. Please try again later.");
            }
        } catch (MPApiException e) {
            System.out.println(e.getApiResponse().getContent());
            throw new RuntimeException(e);
        } catch (UnexpectedException e2) {
            throw new RuntimeException(e2);
        } catch (MPException e3) {
            throw new RuntimeException(e3);
        }
    }

    protected static PaymentPayerRequest factoryPayer(User user, IdentificationRequest identification) {
        return PaymentPayerRequest.builder()
                .email(user.getEmail())
                .firstName(user.findFirstName())
                .lastName(user.findLastName())
                .identification(identification)
                .build();
    }

    protected static IdentificationRequest factoryIdentification(User user) {
        return IdentificationRequest.builder()
                .type("cpf")
                .number(user.getDocument().getCpf())
                .build();
    }
}
