package com.geek.geekblog.Models;

import com.google.gson.annotations.SerializedName;

public class ResponseUser {
    @SerializedName("id")
    public int id;
    @SerializedName("user_name")
    public String user_name;
    @SerializedName("user_email")
    public String user_email;
}
