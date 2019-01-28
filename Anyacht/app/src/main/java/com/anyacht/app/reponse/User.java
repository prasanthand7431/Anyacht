package com.anyacht.app.reponse;

import com.anyacht.app.model.UserDetails;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User extends BasicResponse {

    @SerializedName("details")
    ArrayList<UserDetails> details;

    public ArrayList<UserDetails> Login() {
        return details;
    }
}
