package com.geek.geekblog.Models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("status")
    public int status;
    @SerializedName("message")
    public String message;
    @SerializedName("user")
    public ResponseUser user;
}
