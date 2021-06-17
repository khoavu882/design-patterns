/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:21 PM
 */

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private String description;
    private List<Approval> approvals;
    private List<ApprovalExecute> approvalExecutes;

    public Ticket(String description) {
        this.description = description;
        this.approvals = new ArrayList<>();
        this.approvalExecutes = new ArrayList<>();
    }

    public void addApproval(Approval approval) {
        approvals.add(approval);
    }

    public void addApprovalExecute(ApprovalExecute approvalExecute) {
        approvalExecutes.add(approvalExecute);
    }

    public List<Approval> getApprovals() {
        return approvals;
    }

    public List<ApprovalExecute> getApprovalExecutes() {
        return approvalExecutes;
    }

    public String getDescription() {
        return description;
    }
}