package br.unicamp.projetoapiretrofitdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroPetActivity extends AppCompatActivity {
    EditText edtNome;
    EditText edtRaca;
    EditText edtImagem;
    Button btnConsultarNome;
    Button btnInserir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);

        //reconehcer os obj

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtRaca = (EditText) findViewById(R.id.edtRaca);
        edtImagem = (EditText) findViewById(R.id.edtImagem);

        btnConsultarNome = (Button) findViewById(R.id.btnConsultarNome);
        btnConsultarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtNome.getText().toString() == "")
                    Toast.makeText(CadastroPetActivity.this, "Preencha campo >nome< para consultar Pet", Toast.LENGTH_SHORT).show();
                else
                    consultarPetNome();

            }
        });
    }

    private void consultarPetNome() {
        Service  service =  RetrofirConfig.getRetrofitInstance().create(Service.class);//Service = classe que tem a rota, falo que vou usar a classe RetrofitConfig que
                                                                                    // pega o Ip de onde ta node, faz toda a parte de conexao do backend e de conversao
                                                                                    // para json - getRetrofitInstance(): pegar o ip do node -
        Call<Dog> call = service.selecionaNome(edtNome.getText().toString()); //pego objeto service do tipo Service e uso um de seus métodos - classe seleciona nome
        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) { //vai pegar as resposta e se deu certo e conseguiu enfileirar entra ness
                if (response.isSuccessful())
                {
                    Dog dog = response.body(); //recebe o corpo da resposta que veio (um json)
                    edtNome.setText(dog.getNome()); //seta o nome, pegando a informação que veio do dog e fazendo getNome para pegar só o nome
                    edtRaca.setText(dog.getRaca());
                    edtImagem.setText(dog.getImagem());
                }
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) { //vai pegar as resposta e se deu errado e não conseguiu enfileirar entra ness
                Toast.makeText(CadastroPetActivity.this, "Ocorreu um erro" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });//enfileira os objetos achado em call
    }
}