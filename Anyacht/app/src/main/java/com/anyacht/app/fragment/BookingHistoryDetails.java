package com.anyacht.app.fragment;

import java.io.Serializable;

public class BookingHistoryDetails implements Serializable{

    private String checkin;
    private String checkout;
    private String id;
    private String title;
    private String id_destination;
    private String checked;
    private String rank;
    private String max_children;
    private String max_adults;
    private String max_people;
    private String price;
    private String quantity;
    private String descr;
    private String file_id;
    private String file;
    private String dest;
    private String paymentstatus;

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getId_destination() {
        return id_destination;
    }

    public String getChecked() {
        return checked;
    }

    public String getRank() {
        return rank;
    }

    public String getMax_children() {
        return max_children;
    }

    public String getMax_adults() {
        return max_adults;
    }

    public String getMax_people() {
        return max_people;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDescr() {
        return descr;
    }

    public String getFile_id() {
        return file_id;
    }

    public String getFile() {
        return file;
    }

    public String getDest() {
        return dest;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }
}
