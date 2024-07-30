package org.br.com.ufcg.Models;

import java.time.LocalDate;

public class Fatura {
    private LocalDate data;
    private double valor;
    private String nomeCliente;

    public Fatura(LocalDate data, double valor, String nomeCliente){
        this.data = data;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
    }

    public LocalDate getData(){
        return this.data;
    }

    public double getValor(){
        return this.valor;
    }

    public String getNomeCliente(){
        return this.nomeCliente;
    }

}
