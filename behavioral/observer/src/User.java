/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 2:00 PM
 */

class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + " nhận thông báo: " + message);
    }
}
