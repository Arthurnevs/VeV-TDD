package org.br.com.ufcg.Models;

import java.time.LocalDate;

public class Conta {
    private LocalDate data;
    private double valorPago;
    private String codigo;

    private String idFatura;

    private String tipoPagamento;

    public Conta(LocalDate data, double valor, String codigo, String tipoPagamento){
        this.data = data;
        this.valorPago = valor;
        this.codigo = codigo;
        this.tipoPagamento = tipoPagamento;
    }

    public double getValorPago(){
        return this.valorPago;
    }

    public String getTipoPagamento(){
        return this.tipoPagamento;
    }

    public LocalDate getData(){
        return this.data;
    }

    public void setValorPago(double valor){
        this.valorPago = valor;
    }
}
