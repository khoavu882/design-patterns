/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 2:46 PM
 */

public abstract class Game {
    // Template method
    public final void play() {
        initialize();
        startPlay();
        endPlay();
    }

    // Methods to be implemented by subclasses
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();
}
