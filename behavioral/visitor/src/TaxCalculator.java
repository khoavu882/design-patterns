/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 3:02 PM
 */

class TaxCalculator implements TaxVisitor {
    @Override
    public void visit(Medicine medicine) {
        double tax = medicine.getPrice() * 0.05; // 💊 Thuế 5%
        System.out.println("💊 Tax for Medicine: " + tax);
    }

    @Override
    public void visit(Electronics electronics) {
        double tax = electronics.getPrice() * 0.18; // 📱 Thuế 18%
        System.out.println("📱 Tax for Electronics: " + tax);
    }
}