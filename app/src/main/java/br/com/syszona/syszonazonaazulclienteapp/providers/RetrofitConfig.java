package br.com.syszona.syszonazonaazulclienteapp.providers;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    //private final String endpoint = "http://syszona.com.br/api/";
    private final String endpoint = "http://192.168.1.15/estacionamento-zona-azul/public/api/";
    //private final String endpoint = "http://zona-azul-teste.herokuapp.com/api/";

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(endpoint+"client/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
    public RetrofitConfig(int i) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public UserService getUserService() {
        return this.retrofit.create(UserService.class);
    }
}
