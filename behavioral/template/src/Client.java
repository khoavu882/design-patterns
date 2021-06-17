/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 2:47 PM
 */

public class Client {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();

        System.out.println();

        game = new Football();
        game.play();
    }
}
