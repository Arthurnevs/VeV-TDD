import org.junit.Test;
import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void testCreateNormalTicket() {
        Ticket ticket = new Ticket(1, TicketType.NORMAL);
        assertEquals(1, ticket.getId());
        assertEquals(TicketType.NORMAL, ticket.getType());
        assertFalse(ticket.isSold());
    }

    @Test
    public void testCreateVipTicket() {
        Ticket ticket = new Ticket(2, TicketType.VIP);
        assertEquals(2, ticket.getId());
        assertEquals(TicketType.VIP, ticket.getType());
        assertFalse(ticket.isSold());
    }

    @Test
    public void testCreateMeiaEntradaTicket() {
        Ticket ticket = new Ticket(3, TicketType.MEIA_ENTRADA);
        assertEquals(3, ticket.getId());
        assertEquals(TicketType.MEIA_ENTRADA, ticket.getType());
        assertFalse(ticket.isSold());
    }
}
