package functionalTests;

import org.br.com.ufcg.Models.Conta;
import org.br.com.ufcg.Models.Fatura;
import org.br.com.ufcg.Models.ProcessadorDeBoletos;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProcessadorBoletosJUnit5ValorLimiteTests {
    @Test
    public void testBoletoComValorZero() {
        Fatura fatura = new Fatura("1", LocalDate.now(), 5000, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta(LocalDate.now(), 0.00, "123", "BOLETO")); // Valor inválido: 0.00

        processador.processarBoletos(contas);
        assertEquals("PENDENTE", fatura.getStatus()); // ERRO: Não deve considerar boleto com valor 0.00
    }

    @Test
    public void testBoletoComValorMinimoValido() {
        Fatura fatura = new Fatura("1", LocalDate.now(), 5000, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta(LocalDate.now(), 0.01, "123", "BOLETO")); // Valor válido: 0.01

        processador.processarBoletos(contas);
        assertEquals("PENDENTE", fatura.getStatus()); // Deve considerar pagamento válido
    }

    @Test
    public void testBoletoComValorMaximoValido() {
        Fatura fatura = new Fatura("1", LocalDate.now(), 5000, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta(LocalDate.now(), 5000.00, "123", "BOLETO")); // Valor válido: 5000.00

        processador.processarBoletos(contas);
        assertEquals("PAGA", fatura.getStatus()); // Deve considerar pagamento válido
    }

    @Test
    public void testBoletoComValorAcimaDoLimite() {
        Fatura fatura = new Fatura("1", LocalDate.now(), 5000, "Cliente Teste");
        ProcessadorDeBoletos processador = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();
        contas.add(new Conta(LocalDate.now(), 5000.01, "123", "BOLETO")); // Valor inválido: 5000.01

        processador.processarBoletos(contas);
        assertEquals("PENDENTE", fatura.getStatus()); // ERRO: Não deve considerar boleto com valor acima do limite
    }
}
