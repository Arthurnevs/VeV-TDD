package com.example;

import java.util.ArrayList;
import java.util.List;

public class Show {
    private String date;
    private String artist;
    private double fee;
    private double infrastructureCost;
    private boolean specialDate;
    private List<TicketBatch> ticketBatches;

    public Show(String date, String artist, double fee, double infrastructureCost, boolean specialDate) {
        this.date = date;
        this.artist = artist;
        this.fee = fee;
        this.infrastructureCost = infrastructureCost;
        this.specialDate = specialDate;
        this.ticketBatches = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public String getArtist() {
        return artist;
    }

    public double getFee() {
        return fee;
    }

    public double getInfrastructureCost() {
        return infrastructureCost;
    }

    public boolean isSpecialDate() {
        return specialDate;
    }

    public void addTicketBatch(TicketBatch batch) {
        ticketBatches.add(batch);
    }

    public ShowReport generateReport() {
        int normalTicketsSold = 0;
        int vipTicketsSold = 0;
        int halfTicketsSold = 0;
        double revenue = 0.0;
        double expenses = fee;

        if (specialDate) {
            expenses += infrastructureCost + (infrastructureCost * 0.15);
        } else {
            expenses += infrastructureCost;
        }


        for (TicketBatch batch : ticketBatches) {
            for (Ticket ticket : batch.getTickets()) {
                if (ticket.isSold()) {
                    double price = 0.0;
                    switch (ticket.getType()) {
                        case NORMAL:
                            price = 10.0;
                            normalTicketsSold++;
                            break;
                        case VIP:
                            price = 20.0;
                            vipTicketsSold++;
                            break;
                        case MEIA_ENTRADA:
                            price = 5.0;
                            halfTicketsSold++;
                            break;
                    }
                    revenue += price - (price * batch.getDiscount());
                }
            }
        }

        System.out.println(revenue);

        double netRevenue = revenue - expenses;
        String financialStatus;
        if (netRevenue > 0) {
            financialStatus = "LUCRO";
        } else if (netRevenue == 0) {
            financialStatus = "ESTÁVEL";
        } else {
            financialStatus = "PREJUÍZO";
        }

        return new ShowReport(normalTicketsSold, vipTicketsSold, halfTicketsSold, netRevenue, financialStatus);
    }
}
