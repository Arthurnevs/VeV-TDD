package functionalTests;

import org.br.com.ufcg.Models.Conta;
import org.br.com.ufcg.Models.Fatura;
import org.br.com.ufcg.Models.ProcessadorDeBoletos;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProcessadorDeContasTabelasDecisaoTest {

    @Test
    public void testBoletosSomamValorIgualAoValorDaFatura() {
        Fatura fatura = new Fatura("1", LocalDate.now(), 1500, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta(LocalDate.now(), 500.00, "123", "BOLETO"));
        contas.add(new Conta(LocalDate.now(), 500.00, "456", "BOLETO"));
        contas.add(new Conta(LocalDate.now(), 500.00, "789", "BOLETO")); // Boletos somam exatamente o valor da fatura

        processador.processarBoletos(contas);
        assertEquals("PAGA", fatura.getStatus()); // Deve marcar a fatura como "PAGA"
    }

    @Test
    public void testCartaoCreditoForaDoPrazoDe15Dias() {
        Fatura fatura = new Fatura("1", LocalDate.now().plusDays(10), 1500, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta(LocalDate.now().minusDays(5), 1000.00, "123", "CARTAO_CREDITO")); // Data fora do prazo de 15 dias antes da fatura
        contas.add(new Conta(LocalDate.now(), 500.00, "456", "TRANSFERENCIA_BANCARIA"));

        processador.processarBoletos(contas);
        assertEquals("PENDENTE", fatura.getStatus()); // Não deve considerar o pagamento via cartão fora do prazo, mantendo a fatura "PENDENTE"
    }

    @Test
    public void testBoletoPagoComAtraso() {
        Fatura fatura = new Fatura("1", LocalDate.now(), 1500, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        LocalDate dataAtrasada = LocalDate.now().minusDays(5);
        contas.add(new Conta(dataAtrasada, 500.00, "123", "BOLETO")); // Boleto pago com atraso
        contas.add(new Conta(dataAtrasada, 1000.00, "456", "BOLETO")); // Boleto pago com atraso

        processador.processarBoletos(contas);
        assertEquals("PAGA", fatura.getStatus()); // Deve marcar a fatura como "PAGA" com o acréscimo de 10% nos boletos pagos com atraso
        assertEquals(500 * 1.10 + 1000 * 1.10, fatura.getValorAbatido(), 0.01); // Verifica se o valor foi acrescido corretamente
    }

    @Test
    public void testTransferenciaBancariaDentroDoPrazo() {
        Fatura fatura = new Fatura("1", LocalDate.now().plusDays(5), 700, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta(LocalDate.now(), 700.00, "123", "TRANSFERENCIA_BANCARIA")); // Transferência dentro do prazo

        processador.processarBoletos(contas);
        assertEquals("PAGA", fatura.getStatus()); // Deve considerar o pagamento e marcar a fatura como "PAGA"
    }
}
