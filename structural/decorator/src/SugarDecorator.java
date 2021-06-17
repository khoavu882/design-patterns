/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 1:51 PM
 */

class SugarDecorator implements Beverage {
    private Beverage beverage;

    public SugarDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Sugar";
    }

    public double cost() {
        return beverage.cost() + 0.5;
    }
}
