package payment;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:42 PM
 */

public class PayProcess {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Pay with Credit Card
        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        context.executePayment(100);

        // Pay with PayPal
        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.executePayment(200);
    }
}