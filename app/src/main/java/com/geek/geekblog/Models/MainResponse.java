package com.geek.geekblog.Models;

import com.google.gson.annotations.SerializedName;

public class MainResponse {
    @SerializedName("status")
    public int status;
    @SerializedName("message")
    public String message;
}
