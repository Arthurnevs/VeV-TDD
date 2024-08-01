package com.example;

public class Show {
    private String date;
    private String artist;
    private double fee;
    private double infrastructureCost;
    private boolean specialDate;

    public Show(String date, String artist, double fee, double infrastructureCost, boolean specialDate) {
        this.date = date;
        this.artist = artist;
        this.fee = fee;
        this.infrastructureCost = infrastructureCost;
        this.specialDate = specialDate;
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
}
