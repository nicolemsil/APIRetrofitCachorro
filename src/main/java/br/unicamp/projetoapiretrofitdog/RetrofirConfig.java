package br.unicamp.projetoapiretrofitdog;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofirConfig {
    private static final String BASE_URL = "http://192.168.0.157:3000/api/dog/get/"; //para poder passar o ip da conexao + a rota da api
    private static Retrofit retrofit;

    //metodo pra criar conexao
    public static Retrofit getRetrofitInstance(){                                //static pra poder chamar direto - retorna Retrofit
        if(retrofit == null)                                                    //se for nulo significa que não tem conexão, e se essa condição for verdadeira ele vai criar
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)                 /*instancia a classe retrofit*//*construir a instacnia*//*pega o caminho/conexao */
                    .addConverterFactory(GsonConverterFactory.create())         /*falar que vou usar a biblioteca retrofit pra conversao*/.build();
        }
            return retrofit;
    }
}
