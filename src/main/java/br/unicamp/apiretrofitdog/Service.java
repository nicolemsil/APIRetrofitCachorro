//configuração do retrofit

package br.unicamp.apiretrofitdog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    //sendo uma anotação do tipo get
    //e dentro tem a rota que vai utilizar
    @GET("/api/monitor/get") //quando for utilizar esse getMonitor, tem que ser dessa forma, fazendo uma assinatura desse método
    Call<List<Monitor>> getMonitor(); //vai retornar uma lista de monitores, utilizando esse método pronto que é o CALL

}
