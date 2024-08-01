

import org.br.com.ufcg.Models.Conta;
import org.br.com.ufcg.Models.Fatura;
import org.br.com.ufcg.Models.ProcessadorDeBoletos;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class ProcessadorDeContasTest {

    @Test
    public void testFaturaPagaComBoletosEmDia() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "29/07/2024";
        LocalDate data = LocalDate.parse(dataString, formatter);
        Fatura fatura = new Fatura("1", data,666.69,"Laryssa");
        ProcessadorDeBoletos processadorDeContas = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();

        contas.add(new Conta(LocalDate.now(), 500.00, "1233", "BOLETO"));

        contas.add(new Conta(LocalDate.now(), 400.00, "24983", "BOLETO"));

        contas.add(new Conta(LocalDate.now(), 600.00, "w893", "BOLETO"));

        processadorDeContas.processarBoletos(contas);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testFaturaPagaComCartaoETransferencia() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "29/07/2024";
        LocalDate data = LocalDate.parse(dataString, formatter);
        Fatura fatura = new Fatura("1", data,666.69,"Laryssa");
        ProcessadorDeBoletos processadorDeContas = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();

        contas.add(new Conta(LocalDate.now(), 700.00, "29834", "CARTAO_CREDITO"));
        contas.add(new Conta(LocalDate.now(), 800.00, "jindap", "TRANSFERENCIA_BANCARIA"));

        processadorDeContas.processarBoletos(contas);

        assertEquals("PAGA", fatura.getStatus());
    }

    @Test
    public void testFaturaPendenteComCartaoMuitoRecente() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "29/07/2024";
        LocalDate data = LocalDate.parse(dataString, formatter);
        Fatura fatura = new Fatura("1", data,4000,"Laryssa");
        ProcessadorDeBoletos processadorDeContas = new ProcessadorDeBoletos(fatura);

        ArrayList<Conta> contas = new ArrayList<>();

        contas.add(new Conta(LocalDate.now(), 700.00,"39u2", "CARTAO_CREDITO"));
        contas.add(new Conta(LocalDate.now(), 800.00, "39289423", "TRANSFERENCIA_BANCARIA"));

        processadorDeContas.processarBoletos(contas);

        assertEquals("PENDENTE", fatura.getStatus());
    }
}
