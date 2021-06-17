/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 1:52 PM
 */

public class DecoratorExample {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription() + " - $" + coffee.cost());
    }
}
