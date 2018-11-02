package com.geek.geekblog.Models;

import com.google.gson.annotations.SerializedName;
import java.sql.Timestamp;

public class Message {
    @SerializedName("id")
    public int id;
    @SerializedName("user_id")
    public int user_id;
    @SerializedName("room_id")
    public int room_id;
    @SerializedName("user_name")
    public String user_name;
    @SerializedName("type")
    public int type;
    @SerializedName("content")
    public String content;
    @SerializedName("timestamp")
    public String timestamp;
}
