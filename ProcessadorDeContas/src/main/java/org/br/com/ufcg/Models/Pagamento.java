package org.br.com.ufcg.Models;

import java.time.LocalDate;

public class Pagamento {
    private LocalDate data;
    private double valor;
    private String tipoPagamento;

    public double getValor(){
        return valor;
    }

    public LocalDate getData(){
        return data;
    }

    public String getTipoPagamento(){
        return tipoPagamento;
    }
}
