import org.junit.Test;

import com.example.Ticket;
import com.example.TicketSystem;
import com.example.TicketType;

import static org.junit.Assert.*;

public class TicketSystemTest {

    @Test
    public void testSellTicket() {
        TicketSystem ticketSystem = new TicketSystem();
        Ticket ticket = new Ticket(1, TicketType.NORMAL);
        ticketSystem.sellTicket(ticket);
        assertTrue(ticket.isSold());
    }
}
