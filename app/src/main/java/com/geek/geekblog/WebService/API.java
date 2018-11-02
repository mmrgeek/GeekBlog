package com.geek.geekblog.WebService;

import com.geek.geekblog.Models.ChatRoom;
import com.geek.geekblog.Models.LoginResponse;
import com.geek.geekblog.Models.MainResponse;
import com.geek.geekblog.Models.Message;
import com.geek.geekblog.Models.Post;
import com.geek.geekblog.Models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {
    @POST("register-user.php")
    Call<MainResponse> RegisterUser(@Body User user);
    @POST("login-user.php")
    Call<LoginResponse> LoginUser(@Body User user);
    @POST("get-all-posts.php")
    Call<ArrayList<Post>> getAllPosts();
    @POST("get-all-chat-rooms.php")
    Call<ArrayList<ChatRoom>> getAllChats();
    @POST("add-post.php")
    Call<MainResponse> AddPost(@Body Post post);
    @POST("add-chat-rooms.php")
    Call<MainResponse> AddChat(@Body ChatRoom chatRoom);
    @POST("get-messages.php")
    Call<ArrayList<Message>> getMessages(@Body ChatRoom room);
    @POST("delete-chat-room.php")
    Call<MainResponse> deleteChatRoom(@Body ChatRoom chatRoom);
    @POST("add-message.php")
    Call<MainResponse> addMessage(@Body Message message);
}
