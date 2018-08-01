package br.com.syszona.syszonazonaazulclienteapp.providers;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://zona-azul-teste.herokuapp.com/api/client/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
    public RetrofitConfig(int i) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://zona-azul-teste.herokuapp.com/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public UserService getUserService() {
        return this.retrofit.create(UserService.class);
    }
}
