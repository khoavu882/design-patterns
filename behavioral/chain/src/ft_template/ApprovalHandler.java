package ft_template;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:21 PM
 */

public abstract class ApprovalHandler {
    private ApprovalHandler nextHandler;

    public void setNextHandler(ApprovalHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(Ticket ticket) {
        processRequest(ticket);

        if (nextHandler != null) {
            nextHandler.handleRequest(ticket);
        }
    }

    protected void processRequest(Ticket ticket) {
        int level = getLevel();
        for (Approval approval : ticket.getApprovals()) {
            if (approval.getLevel() == level) {
                if (approval.canBypass()) {
                    approval.approve();
                    System.out.println("L" + level + " Approval: Auto-approved (bypassed).");
                } else if (!approval.isApproved()) {
                    approval.approve();
                    System.out.println("L" + level + " Approval: Ticket approved by L" + level + ".");
                }
                ticket.executeActions(level);
            }
        }
    }

    protected abstract int getLevel();
}

