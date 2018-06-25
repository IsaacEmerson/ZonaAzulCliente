package br.com.syszona.syszonazonaazulclienteapp.providers;

import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST("login")
    Call<Token> login(@Query("email") String email, @Query("password") String password);

    @GET("me")
    Call<User> getUser(@Header("Authorization") String authHeader);
}
