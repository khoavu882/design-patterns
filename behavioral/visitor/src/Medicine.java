/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 3:03 PM
 */

class Medicine implements Product {
    private double price;

    public Medicine(double price) {
        this.price = price;
    }

    @Override
    public void accept(TaxVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public double getPrice() {
        return price;
    }
}
