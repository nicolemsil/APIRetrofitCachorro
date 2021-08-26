package br.unicamp.projetoapiretrofitdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    //declaração dos elementos da activity_main
    private GridView dogGridView;
    private GridViewAdapter adapter; //adapter
    private FloatingActionButton fab;

    private void populateGridView(List<Dog> listDog) {
        dogGridView = (GridView) findViewById(R.id.dogGridView);
        adapter = new GridViewAdapter(this, listDog); //parametros são o contexto e a resosta
        dogGridView.setAdapter(adapter); //fazer o dogGridView receber o adapter que tem o GridViewAdapter
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar myProgressBar =(ProgressBar) findViewById(R.id.myProgressBar);
        myProgressBar.setIndeterminate(true); //até que não tenha outro comando ele não vai parar
        myProgressBar.setVisibility(View.VISIBLE);

        //floating action button
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() { //fazer mudar para tela de cadastro ao clicar no botão
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroPetActivity.class); //tenho a iunteção de sair da classe main que eu estou no momento e ir para o cadastro do pet
                startActivity(intent); //startar o intent
            }
        });

        //trabalhando a parte do service
        Service service = RetrofirConfig.getRetrofitInstance().create(Service.class); //download do Json através do Retrofit

        //Tratar as informações de cima que vieram
        Call<List<Dog>> call = service.getDog();

        //enfileirar as informações que vieram do Json
        call.enqueue(new Callback<List<Dog>>() {
            @Override
            //esse é para caso tenha conseguido pegar todos os itens
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                if(response.isSuccessful()) //se o response teve sucesso, faço o pregress bar desaparecer
                {
                    myProgressBar.setVisibility(View.GONE);
                    populateGridView(response.body());
                }
                else { //pegar o o erro e com o error Body ver qual foi esse erro e coloca-lo no toast
                    String errorMessage = response.errorBody().toString();
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();;
                }
            }

            @Override
            //esse é para caso não tenha conseguido pegar cada um dos itens
            public void onFailure(Call<List<Dog>> call, Throwable t) { // se falhou, ele vai falar o que falhou
                myProgressBar.setVisibility(View.GONE);
                String messageProblem = t.getMessage();//vai pegar o problema que foi passado pelo parametro (Throwable t)
                Toast.makeText(MainActivity.this, messageProblem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}