package com.anyacht.app.reponse;

import com.anyacht.app.model.BookingDetails;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Booking extends BasicResponse {


    @SerializedName("yacht_details")
    ArrayList<BookingDetails> yacht_details;

    public ArrayList<BookingDetails> getyacht_details() {
        return yacht_details;
    }
}
