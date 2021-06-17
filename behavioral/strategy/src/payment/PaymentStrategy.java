package payment;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:41 PM
 */

public interface PaymentStrategy {
    void pay(int amount);
}