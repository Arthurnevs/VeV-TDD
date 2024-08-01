package org.br.com.ufcg.Models;

import java.time.LocalDate;

public class Conta {
    private LocalDate data;
    private double valorPago;
    private String codigo;

    private String idFatura;

    public Conta(LocalDate data, double valor, String codigo){
        this.data = data;
        this.valorPago = valor;
        this.codigo = codigo;
    }
}
