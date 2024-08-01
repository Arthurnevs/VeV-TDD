import org.junit.Test;

import com.example.Show;
import com.example.ShowReport;
import com.example.Ticket;
import com.example.TicketBatch;
import com.example.TicketType;

import static org.junit.Assert.*;

public class ShowReportTest {

    @Test
    public void testGenerateReport() {
        Show show = new Show("2024-12-31", "Madonna", 1000.0, 2000.0, true);
        TicketBatch batch = new TicketBatch(1, 0.15);
        Ticket normalTicket = new Ticket(1, TicketType.NORMAL);
        Ticket vipTicket = new Ticket(2, TicketType.VIP);
        Ticket halfTicket = new Ticket(3, TicketType.MEIA_ENTRADA);
        batch.addTicket(normalTicket);
        batch.addTicket(vipTicket);
        batch.addTicket(halfTicket);
        show.addTicketBatch(batch);

        normalTicket.setSold(true);
        vipTicket.setSold(true);
        halfTicket.setSold(true);

        ShowReport report = show.generateReport();
        assertEquals(1, report.getNormalTicketsSold());
        assertEquals(1, report.getVipTicketsSold());
        assertEquals(1, report.getHalfTicketsSold());
        assertEquals(4925.0, report.getNetRevenue(), 0.01);
        assertEquals("LUCRO", report.getFinancialStatus());
    }
}
