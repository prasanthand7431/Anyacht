package com.anyacht.app.reponse;

import com.google.gson.annotations.SerializedName;

public class BasicResponse {

    @SerializedName("msg")
    public String msg;

    @SerializedName("status")
    public String status;

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }


}
