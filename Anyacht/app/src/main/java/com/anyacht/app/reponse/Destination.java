package com.anyacht.app.reponse;

import com.anyacht.app.model.DestinationDetails;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Destination extends BasicResponse {

    @SerializedName("details")
    ArrayList<DestinationDetails> details;

    public ArrayList<DestinationDetails> getdetails() {
        return details;
    }

}
