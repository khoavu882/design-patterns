/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 3:02 PM
 */

interface TaxVisitor {
    void visit(Medicine medicine);  // 💊 Thuốc
    void visit(Electronics electronics); // 📱 Đồ điện tử
}
