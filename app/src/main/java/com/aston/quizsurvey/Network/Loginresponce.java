package com.aston.quizsurvey.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loginresponce {
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;
    @SerializedName("id")
    private int id;


    public Loginresponce(String message, String status, int id) {
        this.message = message;
        this.status = status;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
