/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:21 PM
 */

public class L1ApprovalHandler implements ApprovalHandler {
    @Override
    public void handleRequest(Ticket ticket) {
        for (Approval approval : ticket.getApprovals()) {
            if (approval.getLevel() == 1) {
                if (approval.canBypass()) {
                    approval.approve();
                    System.out.println("L1 Approval: Auto-approved (bypassed).");
                } else if (!approval.isApproved()) {
                    approval.approve();
                    System.out.println("L1 Approval: Ticket approved by L1.");
                }
                executeActions(ticket);
            }
        }
    }

    private void executeActions(Ticket ticket) {
        for (ApprovalExecute execute : ticket.getApprovalExecutes()) {
            execute.execute();
        }
    }
}