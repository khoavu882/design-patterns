/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 1:59 PM
 */

public class ObserverExample {
    public static void main(String[] args) {
        Subject blog = new Subject();

        User alice = new User("Alice");
        User bob = new User("Bob");

        blog.addObserver(alice);
        blog.addObserver(bob);

        blog.notifyObservers("Có bài viết mới!");
    }
}
