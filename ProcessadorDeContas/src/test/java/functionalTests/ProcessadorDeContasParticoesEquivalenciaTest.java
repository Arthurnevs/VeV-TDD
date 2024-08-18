package functionalTests;

import org.br.com.ufcg.Models.Conta;
import org.br.com.ufcg.Models.Fatura;
import org.br.com.ufcg.Models.ProcessadorDeBoletos;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProcessadorDeContasParticoesEquivalenciaTest {

    @Test
    public void testBoletoComValorDentroDaFaixaValida() {
        Fatura fatura = new Fatura("1", LocalDate.now(), 1000, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta(LocalDate.now(), 100.00, "123", "BOLETO")); // Valor dentro da faixa válida

        processador.processarBoletos(contas);
        assertEquals("PENDENTE", fatura.getStatus()); // Deve considerar o pagamento, mas ainda não atinge o valor da fatura
    }

    @Test
    public void testBoletoComValorAcimaDoLimitePermitido() {
        Fatura fatura = new Fatura("1", LocalDate.now(), 6000, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta(LocalDate.now(), 6000.00, "123", "BOLETO")); // Valor acima do limite permitido

        processador.processarBoletos(contas);
        assertEquals("PENDENTE", fatura.getStatus()); // ERRO: Não deve considerar o boleto com valor acima do limite
    }

    @Test
    public void testCartaoCreditoForaDoPrazo() {
        Fatura fatura = new Fatura("1", LocalDate.now().plusDays(15), 1500, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta(LocalDate.now().minusDays(10), 1000.00, "123", "CARTAO_CREDITO")); // Data fora do prazo de 15 dias antes da fatura

        processador.processarBoletos(contas);
        assertEquals("PENDENTE", fatura.getStatus()); // Não deve considerar o pagamento via cartão de crédito fora do prazo
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
