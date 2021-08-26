package br.unicamp.projetoapiretrofitdog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    //metodo para pegar a rota
    @GET("/api/dog/get")
    Call<List<Dog>> getDog(); //o método getDog vai retornar uma lista de Dogs usando a classe pronta do retrofit (call)

    @GET("/api/dog/getNome/:{nome}") //get feito através do nome
    Call<Dog> selecionaNome(@Path("nome") String nome); //o parametro é nome, da String nome que vai ser passado através do método selecionenome

}
