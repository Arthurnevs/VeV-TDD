

import org.br.com.ufcg.Models.Fatura;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ProcessadorDeContasTest {
    @Test
    public void testCriarProcessadorContas(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = "29/07/2024";
        LocalDate data = LocalDate.parse(dataString, formatter);
        Fatura fatura = new Fatura(data,666.69,"Laryssa");
    }
}
