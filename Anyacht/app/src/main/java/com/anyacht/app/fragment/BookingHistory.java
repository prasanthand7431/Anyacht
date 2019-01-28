package com.anyacht.app.fragment;

import com.anyacht.app.model.BookingDetails;
import com.anyacht.app.reponse.BasicResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BookingHistory extends BasicResponse{

    @SerializedName("details")
    ArrayList<BookingHistoryDetails> details;

    public ArrayList<BookingHistoryDetails> details() {
        return details;
    }
}
