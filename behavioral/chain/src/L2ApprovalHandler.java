/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:22 PM
 */

public class L2ApprovalHandler implements ApprovalHandler {
    @Override
    public void handleRequest(Ticket ticket) {
        for (Approval approval : ticket.getApprovals()) {
            if (approval.getLevel() == 2) {
                if (approval.canBypass()) {
                    approval.approve();
                    System.out.println("L2 Approval: Auto-approved (bypassed).");
                } else if (!approval.isApproved()) {
                    approval.approve();
                    System.out.println("L2 Approval: Ticket approved by L2.");
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
