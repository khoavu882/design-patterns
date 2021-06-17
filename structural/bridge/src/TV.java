/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 1:44 PM
 */

public class TV implements Device {
    private int volume;

    @Override
    public void turnOn() {
        System.out.println("TV is turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println("TV is turned off.");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("TV volume set to " + volume);
    }
}
