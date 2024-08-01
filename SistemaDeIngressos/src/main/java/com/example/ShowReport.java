package com.example;

public class ShowReport {
    private int normalTicketsSold;
    private int vipTicketsSold;
    private int halfTicketsSold;
    private double netRevenue;
    private String financialStatus;

    public ShowReport(int normalTicketsSold, int vipTicketsSold, int halfTicketsSold, double netRevenue, String financialStatus) {
        this.normalTicketsSold = normalTicketsSold;
        this.vipTicketsSold = vipTicketsSold;
        this.halfTicketsSold = halfTicketsSold;
        this.netRevenue = netRevenue;
        this.financialStatus = financialStatus;
    }

    public int getNormalTicketsSold() {
        return normalTicketsSold;
    }

    public int getVipTicketsSold() {
        return vipTicketsSold;
    }

    public int getHalfTicketsSold() {
        return halfTicketsSold;
    }

    public double getNetRevenue() {
        return netRevenue;
    }

    public String getFinancialStatus() {
        return financialStatus;
    }
}
