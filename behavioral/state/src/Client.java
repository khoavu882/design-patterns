/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 2:24 PM
 */

public class Client {
    public static void main(String[] args) {
        Context context = new Context();

        State startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        State stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
