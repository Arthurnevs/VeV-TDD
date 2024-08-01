package org.br.com.ufcg.Models;

import java.time.LocalDate;

public class Fatura {

    private String id;
    private LocalDate data;
    private double valor;
    private String nomeCliente;

    private double valorAbatido;

    private String status;

    public Fatura(String id,LocalDate data, double valor, String nomeCliente){
        this.data = data;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
        this.id = id;
        this.valorAbatido = 0;
        this.status = "PENDENTE";
    }

    public void setValorAbatido(double valor){
        this.valorAbatido = valor;
    }

    public double getValorAbatido(){
        return this.valorAbatido;
    }

    public LocalDate getData(){
        return this.data;
    }

    public double getValor(){
        return this.valor;
    }

    public String getId(){
        return this.id;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getNomeCliente(){
        return this.nomeCliente;
    }

}
