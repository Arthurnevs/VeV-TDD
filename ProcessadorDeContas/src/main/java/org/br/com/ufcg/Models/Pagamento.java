package org.br.com.ufcg.Models;

import java.time.LocalDate;

public class Pagamento {
    private double valor;
    private String tipoPagamento;

    private String idFatura;

    public Pagamento(double valor, String tipoPagamento, String idFatura){
        this.valor = valor;
        this.tipoPagamento = tipoPagamento;
        this.idFatura = idFatura;
    }

    public double getValor(){
        return valor;
    }

    public String getTipoPagamento(){
        return tipoPagamento;
    }
}
