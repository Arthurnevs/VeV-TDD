package org.br.com.ufcg.Models;
import java.util.ArrayList;
import java.util.List;

public class ProcessadorDeBoletos {
        private Fatura fatura;
        private List<Pagamento> pagamentos;

        public void ProcessadorBoletos(Fatura fatura){
            this.fatura = fatura;
            this.pagamentos = new ArrayList<>();
        }
}
