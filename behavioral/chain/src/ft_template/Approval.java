package ft_template;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:20 PM
 */

public class Approval {
    private int level; // L1, L2, L3
    private boolean isApproved;
    private boolean canBypass; // Flag to indicate if this level can be bypassed

    public Approval(int level, boolean canBypass) {
        this.level = level;
        this.isApproved = false;
        this.canBypass = canBypass;
    }

    public int getLevel() {
        return level;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public boolean canBypass() {
        return canBypass;
    }

    public void approve() {
        this.isApproved = true;
    }
}
