/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 2:05 PM
 */

class TurnOffCommand implements Command {
    private Light light;

    public TurnOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOff();
    }
}
