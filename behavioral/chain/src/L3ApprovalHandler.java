/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:22 PM
 */

public class L3ApprovalHandler implements ApprovalHandler {
    @Override
    public void handleRequest(Ticket ticket) {
        for (Approval approval : ticket.getApprovals()) {
            if (approval.getLevel() == 3 && !approval.isApproved()) {
                approval.approve();
                System.out.println("L3 Approval: Ticket approved by L3.");
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
