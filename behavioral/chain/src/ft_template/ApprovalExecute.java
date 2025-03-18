package ft_template;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:20 PM
 */

public class ApprovalExecute {
    private int level; // L1, L2, L3
    private String action;

    public ApprovalExecute(String action) {
        this.action = action;
    }

    public int getLevel() {
        return level;
    }

    public void execute() {
        System.out.println("Executing action: " + action);
    }

    public ApprovalExecute(int level, String action) {
        this.level = level;
        this.action = action;
    }
}
