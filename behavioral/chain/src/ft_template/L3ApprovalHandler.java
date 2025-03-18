package ft_template;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:22 PM
 */

public class L3ApprovalHandler extends ApprovalHandler {
    @Override
    protected int getLevel() {
        return 3;
    }

    @Override
    protected void processRequest(Ticket ticket) {
        for (Approval approval : ticket.getApprovals()) {
            if (approval.getLevel() == getLevel() && !approval.isApproved()) {
                // Custom logic for L3
                if (requiresManualIntervention(ticket)) {
                    System.out.println("L3 Approval: Manual intervention required.");
                } else {
                    approval.approve();
                    System.out.println("L3 Approval: Ticket approved by L3.");
                }
                ticket.executeActions(getLevel());
            }
        }
    }

    private boolean requiresManualIntervention(Ticket ticket) {
        // Custom logic to determine if manual intervention is required
        System.out.println("Checking if manual intervention is required for L3...");
        return false; // Example: Always return false for simplicity
    }
}
