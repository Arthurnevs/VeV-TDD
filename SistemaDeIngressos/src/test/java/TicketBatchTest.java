import org.junit.Test;

import com.example.Ticket;
import com.example.TicketBatch;
import com.example.TicketType;

import java.util.List;
import static org.junit.Assert.*;

public class TicketBatchTest {

    @Test
    public void testCreateTicketBatch() {
        TicketBatch batch = new TicketBatch(1, 0.15);
        assertEquals(1, batch.getId());
        assertEquals(0.15, batch.getDiscount(), 0.01);
        assertTrue(batch.getTickets().isEmpty());
    }

    @Test
    public void testAddTicketsToBatch() {
        TicketBatch batch = new TicketBatch(1, 0.15);
        Ticket ticket1 = new Ticket(1, TicketType.NORMAL);
        Ticket ticket2 = new Ticket(2, TicketType.VIP);
        batch.addTicket(ticket1);
        batch.addTicket(ticket2);
        List<Ticket> tickets = batch.getTickets();
        assertEquals(2, tickets.size());
        assertEquals(ticket1, tickets.get(0));
        assertEquals(ticket2, tickets.get(1));
    }
}
