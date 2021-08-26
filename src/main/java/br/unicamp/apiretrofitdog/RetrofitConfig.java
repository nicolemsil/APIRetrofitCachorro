//configuração do retrofit

package br.unicamp.apiretrofitdog;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    //criação de variáveis

    //passar o número IP da conexão que vai utilizar
    private static final String BASE_URL = "http://192.168.15.9:3000/api/monitor/get/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){

        //se o retrofit estiver vazio tem que fazer a conexão
        //o método vai voltar a construção do retrofit
        if (retrofit == null ){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}
