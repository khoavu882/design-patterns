/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 3:03 PM
 */

interface Product {
    void accept(TaxVisitor visitor);
    double getPrice();
}
