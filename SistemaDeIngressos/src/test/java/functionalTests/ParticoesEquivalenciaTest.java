package functionalTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.example.Ticket;
import com.example.TicketBatch;
import com.example.TicketType;

public class ParticoesEquivalenciaTest {
    public String validaDescontoTipo(TicketType tipo, double desconto) {
        if (tipo.equals(TicketType.MEIA_ENTRADA) && desconto > 0) {
            return "ERRO";
        }

        return "Válido";
    }

    @Test
    public void testTipoNormal() {
        try {
            TicketBatch batch = new TicketBatch(1, 0.15); // Lote com desconto
            Ticket ticket = new Ticket(1, TicketType.NORMAL);
            ticket.setSold(true);
            batch.addTicket(ticket);
        } catch (Exception e) {
            fail("ERRO");
        }
    }

    @Test
    public void testTipoVIP() {
        try {
            TicketBatch batch = new TicketBatch(1, 0.15); // Lote com desconto
            Ticket ticket = new Ticket(1, TicketType.VIP);
            ticket.setSold(true);
            batch.addTicket(ticket);
        } catch (Exception e) {
            fail("ERRO");
        }
    }

    @Test
    public void testTipoMeiaEntrada() {
        try {
            TicketBatch batch = new TicketBatch(1, 0.15); // Lote com desconto e meia entrada
            Ticket ticket = new Ticket(1, TicketType.MEIA_ENTRADA);
            ticket.setSold(true);
            batch.addTicket(ticket);
            fail("ERRO"); // Deve falhar
        } catch (Exception e) {}
    }
}
