package ft_template; /**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:21 PM
 */

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private String description;
    private List<Approval> approvals = new ArrayList<>();
    private List<ApprovalExecute> executes = new ArrayList<>();

    public Ticket(String description) {
        this.description = description;
    }

    public void addApproval(Approval approval) {
        approvals.add(approval);
    }

    public void addApprovalExecute(ApprovalExecute execute) {
        executes.add(execute);
    }

    public List<Approval> getApprovals() {
        return approvals;
    }

    public List<ApprovalExecute> getApprovalExecutes() {
        return executes;

    }

    public void executeActions(Integer level) {
        for (ApprovalExecute execute : executes) {
            if(execute.getLevel() == level) {
                execute.execute();
            }
        }
    }

    public boolean isFullyApproved() {
        for (Approval approval : approvals) {
            if (!approval.isApproved()) {
                return false;
            }
        }
        return true;
    }
}