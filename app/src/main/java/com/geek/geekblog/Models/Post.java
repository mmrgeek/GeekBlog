package com.geek.geekblog.Models;

import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Text;

import java.sql.Timestamp;

public class Post {
    @SerializedName("id")
    public int id;
    @SerializedName("user_id")
    public int user_id;
    @SerializedName("user_name")
    public String user_name;
    @SerializedName("content")
    public String content;
    @SerializedName("content_type")
    public int content_type;
    @SerializedName("timestamp")
    public String timestamp;
}
