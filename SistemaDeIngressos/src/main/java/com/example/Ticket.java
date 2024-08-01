package com.example;

public class Ticket {
    private int id;
    private TicketType type;
    private boolean sold;

    public Ticket(int id, TicketType type) {
        this.id = id;
        this.type = type;
        this.sold = false;
    }

    public int getId() {
        return id;
    }

    public TicketType getType() {
        return type;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
