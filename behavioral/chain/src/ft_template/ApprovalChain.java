package ft_template;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 2:08 PM
 */

public class ApprovalChain {
    public static void main(String[] args) {
        // Create a ticket
        Ticket ticket = new Ticket("Sample Ticket");

        // Add approvals for the ticket
        ticket.addApproval(new Approval(1, true)); // L1 (can bypass)
        ticket.addApproval(new Approval(2, true)); // L2 (can bypass)
        ticket.addApproval(new Approval(3, false)); // L3 (cannot bypass)

        // Add execution actions for the ticket
        ticket.addApprovalExecute(new ApprovalExecute(1, "Notify Manager"));
        ticket.addApprovalExecute(new ApprovalExecute(2, "Update Database"));
        ticket.addApprovalExecute(new ApprovalExecute(3, "Send Email"));

        // Create the chain of handlers
        ApprovalHandler l1Handler = new L1ApprovalHandler();
        ApprovalHandler l2Handler = new L2ApprovalHandler();
        ApprovalHandler l3Handler = new L3ApprovalHandler();

        // Set up the chain
        l1Handler.setNextHandler(l2Handler);
        l2Handler.setNextHandler(l3Handler);

        // Process the ticket through the chain
        l1Handler.handleRequest(ticket);

        if (ticket.isFullyApproved()) {
            System.out.println("Ticket is fully approved.");
        } else {
            System.out.println("Ticket is not fully approved.");
        }
    }
}
