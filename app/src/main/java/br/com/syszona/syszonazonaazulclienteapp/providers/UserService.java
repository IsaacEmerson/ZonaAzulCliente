package br.com.syszona.syszonazonaazulclienteapp.providers;

import br.com.syszona.syszonazonaazulclienteapp.models.ActiveCard;
import br.com.syszona.syszonazonaazulclienteapp.models.AddressesList;
import br.com.syszona.syszonazonaazulclienteapp.models.Balance;
import br.com.syszona.syszonazonaazulclienteapp.models.CardList;
import br.com.syszona.syszonazonaazulclienteapp.models.CitiesList;
import br.com.syszona.syszonazonaazulclienteapp.models.HistoricList;
import br.com.syszona.syszonazonaazulclienteapp.models.PlaquesList;
import br.com.syszona.syszonazonaazulclienteapp.models.RateList;
import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.models.User;
import br.com.syszona.syszonazonaazulclienteapp.models.UserData;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST("login")
    Call<Token> login(@Query("email") String email, @Query("password") String password);

    @GET("user")
    Call<User> getUser(@Header("Authorization") String authHeader);

    @GET("userData")
    Call<UserData> getUserData(@Header("Authorization") String authHeader);

    @GET("balance")
    Call<Balance> getUserBalance(@Header("Authorization") String authHeader);

    @GET("cards")
    Call<CardList> getUserCards(@Header("Authorization") String authHeader);

    @GET("plaques")
    Call<PlaquesList> getUserPlaques(@Header("Authorization") String authHeader);

    @GET("cities")
    Call<CitiesList> getCities(@Header("Authorization") String authHeader);

    @GET("historics")
    Call<HistoricList> getUserHistoric(@Header("Authorization") String authHeader,@Query("page") int page);

    @GET("addresses")
    Call<AddressesList> getAddresses(@Header("Authorization") String authHeader,@Query("city_id") int city_id);

    @GET("rates")
    Call<RateList> getRatesFromCity(@Query("city_id") int city_id,@Header("Authorization") String authHeader);

    @POST("activeCard")
    Call<ActiveCard> activeCard(@Header("Authorization") String authHeader, @Query("city_id") int city_id
    , @Query("rate_id") int rate_id, @Query("plaque_id") int plaque_id);

    @POST("plaques")
    Call<ActiveCard> addPlaque(@Header("Authorization") String authHeader, @Query("plaque") String plaque, @Query("vehicle_id") int vehicle_id);

    @DELETE("plaques")
    Call<ActiveCard> deletePlaque(@Header("Authorization") String authHeader,@Query("id") int id);

    @POST("refresh")
    Call<Token> refreshToken(@Header("Authorization") String authHeader);
}
