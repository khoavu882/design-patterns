/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 3:16 PM
 */

public class ProxyExample {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();
        internet.connectTo("google.com");
        internet.connectTo("banned.com");
    }
}