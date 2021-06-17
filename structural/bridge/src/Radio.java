/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 1:43 PM
 */

public class Radio implements Device {
    private int volume;

    @Override
    public void turnOn() {
        System.out.println("Radio is turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Radio is turned off.");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Radio volume set to " + volume);
    }
}
