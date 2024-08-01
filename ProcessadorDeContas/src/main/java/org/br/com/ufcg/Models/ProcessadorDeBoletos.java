package org.br.com.ufcg.Models;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProcessadorDeBoletos {
        private Fatura fatura;
        private List<Pagamento> pagamentos;

        public ProcessadorDeBoletos(Fatura fatura){
            this.fatura = fatura;
            this.pagamentos = new ArrayList<>();
        }

        public void processarBoletos(List<Conta> contas){
            double valorBoletos = 0;

            for(Conta conta: contas ){
                if (!this.contaValida(conta)){
                    continue;
                }

                if (conta.getTipoPagamento().equals("BOLETO") && LocalDate.now().isAfter(conta.getData())) {
                    conta.setValorPago(conta.getValorPago() * 1.10);
                }

                Pagamento pagamento = new Pagamento(conta.getValorPago(), conta.getTipoPagamento(), this.fatura.getId());
                this.pagamentos.add(pagamento);
                valorBoletos += pagamento.getValor();
            }

            this.fatura.setValorAbatido(valorBoletos);
            if(fatura.getValorAbatido() >= fatura.getValor()){
                fatura.setStatus("PAGA");
            }
        }

        public boolean contaValida(Conta conta){
            LocalDate dataAtual = LocalDate.now();
            if (conta.getTipoPagamento().equals("BOLETO")){
                return (conta.getValorPago() > 0.01 && conta.getValorPago() < 5000) && !conta.getData().isAfter(fatura.getData());
            }

            if (conta.getTipoPagamento().equals("CARTAO_CREDITO")){
                LocalDate dataLimite = dataAtual.minusDays(15);
                return conta.getData().isBefore(dataLimite);
            }

            return !conta.getData().isAfter(dataAtual);
        }
}
