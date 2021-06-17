/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 1:45 PM
 */

public class Client {
    public static void main(String[] args) {
        Device radio = new Radio();
        RemoteControl radioRemote = new RemoteControl(radio);
        radioRemote.turnOn();
        radioRemote.setVolume(10);
        radioRemote.turnOff();

        Device tv = new TV();
        AdvancedRemoteControl tvRemote = new AdvancedRemoteControl(tv);
        tvRemote.turnOn();
        tvRemote.setVolume(20);
        tvRemote.mute();
        tvRemote.turnOff();
    }
}