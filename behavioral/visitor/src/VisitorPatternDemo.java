/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 3:03 PM
 */

public class VisitorPatternDemo {
    public static void main(String[] args) {
        Product medicine = new Medicine(100);
        Product electronics = new Electronics(1000);

        TaxVisitor taxCalculator = new TaxCalculator();

        medicine.accept(taxCalculator);
        electronics.accept(taxCalculator);
    }
}
