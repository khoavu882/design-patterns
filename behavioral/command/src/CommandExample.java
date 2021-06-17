/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 2:05 PM
 */

public class CommandExample {
    public static void main(String[] args) {
        Light light = new Light();
        RemoteControl remote = new RemoteControl();

        remote.executeCommand(new TurnOnCommand(light));
        remote.executeCommand(new TurnOffCommand(light));
    }
}
