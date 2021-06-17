/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:20 PM
 */

public class ApprovalExecute {
    private String action;

    public ApprovalExecute(String action) {
        this.action = action;
    }

    public void execute() {
        System.out.println("Executing action: " + action);
    }
}