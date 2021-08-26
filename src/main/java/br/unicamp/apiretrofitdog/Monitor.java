package br.unicamp.apiretrofitdog;

import com.google.gson.annotations.SerializedName;

public class Monitor {

    //pegou a tag do json e instanciou
    @SerializedName("nome")
    private String nome;

    //pegou a tag do json e instanciou
    @SerializedName("image")
    private String imagem;

    //pegou a tag do json e instanciou
    @SerializedName("horario")
    private String horario;


    //criação dos métodos getters e setters

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getImagem() {

        return imagem;
    }

    public void setImagem(String imagem) {

        this.imagem = imagem;
    }

    public String getHorario() {

        return horario;
    }

    public void setHorario(String horario) {

        this.horario = horario;
    }

}
