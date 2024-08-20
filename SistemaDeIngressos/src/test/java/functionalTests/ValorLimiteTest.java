package functionalTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.example.Ticket;
import com.example.TicketBatch;
import com.example.TicketType;

public class ValorLimiteTest {
    public String validaDesconto(double desconto) {
        if (desconto / 100 < 0 || desconto / 100 > 25) {
            return "ERRO";
        }
        return "Válido";
    }

    @Test
    public void testPercentual19() {
        try {
            TicketBatch batch = new TicketBatch(1, 0.15);
        
            for (int i = 1; i <= 19; i++) {
                Ticket vipTicket = new Ticket(i, TicketType.VIP);
                vipTicket.setSold(true);
                batch.addTicket(vipTicket);
            }

            for (int i = 20; i <= 100; i++) {
                Ticket normalTicket = new Ticket(i, TicketType.NORMAL);
                normalTicket.setSold(true);
                batch.addTicket(normalTicket);
            }

            fail("ERRO"); // Deve falhar
        } catch (Exception e) {}
    }

    @Test
    public void testPercentual20() {
        try {
            TicketBatch batch = new TicketBatch(1, 0.15);
        
            for (int i = 1; i <= 20; i++) {
                Ticket vipTicket = new Ticket(i, TicketType.VIP);
                vipTicket.setSold(true);
                batch.addTicket(vipTicket);
            }

            for (int i = 21; i <= 100; i++) {
                Ticket normalTicket = new Ticket(i, TicketType.NORMAL);
                normalTicket.setSold(true);
                batch.addTicket(normalTicket);
            } // Deve ser válido
        } catch (Exception e) {
            fail("ERRO");
        }
    }

    @Test
    public void testPercentual30() {
        try {
            TicketBatch batch = new TicketBatch(1, 0.15);
        
            for (int i = 1; i <= 30; i++) {
                Ticket vipTicket = new Ticket(i, TicketType.VIP);
                vipTicket.setSold(true);
                batch.addTicket(vipTicket);
            }

            for (int i = 31; i <= 100; i++) {
                Ticket normalTicket = new Ticket(i, TicketType.NORMAL);
                normalTicket.setSold(true);
                batch.addTicket(normalTicket);
            } // Deve ser válido
        } catch (Exception e) {
            fail("ERRO");
        }
    }

    @Test
    public void testPercentual31() {
        try {
            TicketBatch batch = new TicketBatch(1, 0.15);
        
            for (int i = 1; i <= 31; i++) {
                Ticket vipTicket = new Ticket(i, TicketType.VIP);
                vipTicket.setSold(true);
                batch.addTicket(vipTicket);
            }

            for (int i = 32; i <= 100; i++) {
                Ticket normalTicket = new Ticket(i, TicketType.NORMAL);
                normalTicket.setSold(true);
                batch.addTicket(normalTicket);
            }

            fail("ERRO"); // Deve falhar
        } catch (Exception e) {}
    }

    @Test
    public void testDesconto0() {
        TicketBatch batch = new TicketBatch(1, 0);
        assertEquals("Válido", validaDesconto(batch.getDiscount()));
    }

    @Test
    public void testDesconto1() {
        TicketBatch batch = new TicketBatch(1, 0.01);
        assertEquals("Válido", validaDesconto(batch.getDiscount()));
    }

    @Test
    public void testDesconto25() {
        TicketBatch batch = new TicketBatch(1, 0.25);
        assertEquals("Válido", validaDesconto(batch.getDiscount()));
    }

    @Test
    public void testDesconto26() {
        TicketBatch batch = new TicketBatch(1, 0.26);
        assertEquals("ERRO", validaDesconto(batch.getDiscount()));
    }
}
