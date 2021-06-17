/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 2:16 PM
 */

interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}