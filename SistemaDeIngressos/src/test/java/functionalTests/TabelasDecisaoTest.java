package functionalTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.Show;
import com.example.ShowReport;
import com.example.Ticket;
import com.example.TicketBatch;
import com.example.TicketType;

public class TabelasDecisaoTest {
    @Test
    public void testReceitaMaiorQueDespesas() {
        Show show = new Show("2024-12-31", "Madonna", 1000.0, 2000.0, true);
        TicketBatch batch = new TicketBatch(1, 0.15);
        
        for (int i = 1; i <= 100; i++) {
            Ticket vipTicket = new Ticket(i, TicketType.VIP);
            vipTicket.setSold(true);
            batch.addTicket(vipTicket);
        }

        for (int i = 101; i <= 500; i++) {
            Ticket normalTicket = new Ticket(i, TicketType.NORMAL);
            normalTicket.setSold(true);
            batch.addTicket(normalTicket);
        }
        
        show.addTicketBatch(batch);

        ShowReport report = show.generateReport();
        assertEquals("LUCRO", report.getFinancialStatus());
    }

    @Test
    public void testReceitaIgualAsDespesas() {
        Show show = new Show("2024-12-31", "Madonna", 1000.0, 2000.0, false);
        TicketBatch batch = new TicketBatch(1, 0);

        for (int i = 1; i <= 300; i++) {
            Ticket normalTicket = new Ticket(i, TicketType.NORMAL);
            normalTicket.setSold(true);
            batch.addTicket(normalTicket);
        }
        
        show.addTicketBatch(batch);

        ShowReport report = show.generateReport();
        assertEquals("ESTÁVEL", report.getFinancialStatus());
    }

    @Test
    public void testReceitaMenorQueDespesas() {
        Show show = new Show("2024-12-31", "Madonna", 1000.0, 2000.0, false);
        TicketBatch batch = new TicketBatch(1, 0);

        for (int i = 1; i <= 100; i++) {
            Ticket normalTicket = new Ticket(i, TicketType.NORMAL);
            normalTicket.setSold(true);
            batch.addTicket(normalTicket);
        }
        
        show.addTicketBatch(batch);

        ShowReport report = show.generateReport();
        assertEquals("PREJUÍZO", report.getFinancialStatus());
    }
}
