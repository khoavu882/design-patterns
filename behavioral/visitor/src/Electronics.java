/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 3:04 PM
 */

class Electronics implements Product {
    private double price;

    public Electronics(double price) {
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
