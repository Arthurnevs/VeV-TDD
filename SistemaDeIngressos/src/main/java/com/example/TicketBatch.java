package com.example;

import java.util.ArrayList;
import java.util.List;

public class TicketBatch {
    private int id;
    private double discount;
    private List<Ticket> tickets;

    public TicketBatch(int id, double discount) {
        this.id = id;
        this.discount = discount;
        this.tickets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public double getDiscount() {
        return discount;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
