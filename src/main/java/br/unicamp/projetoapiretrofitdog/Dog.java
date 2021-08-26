package br.unicamp.projetoapiretrofitdog;

import com.google.gson.annotations.SerializedName;

public class Dog {
    //atributos:
    @SerializedName("nome") //para poder nomear/identificar as tags do Json - o nome tem que ser igualzinho
    private String nome;

    @SerializedName("image") //para poder nomear as tags do Json
    private String imagem;

    @SerializedName("raca") //para poder nomear as tags do Json
    private String raca;

    //getter e setter de nome -----
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    //getter e setter de imagem -----
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    //getter e setter de raca -----
    public String getRaca() {
        return raca;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }


    public Dog(String nome, String raca, String imagem) {
        this.nome = nome;
        this.raca = raca;
        this.imagem = imagem;
    }
}
