package br.unicamp.apiretrofitdog;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GridView monitorGridView;
    private GridViewAdapter adapter;


    private void populateGridView(List<Monitor> listMonitor) {
        monitorGridView = (GridView) findViewById(R.id.monitorGridView);
        adapter = new GridViewAdapter(this,listMonitor);
        monitorGridView.setAdapter(adapter);
    }

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar myProgressBar = (ProgressBar)findViewById(R.id.myProgressBar);
        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE); //pra poder aparecer na tela

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("APIRetrofit");
        toolbar.setSubtitle("Monitores");
        toolbar.setLogo(R.mipmap.ic_launcher);

        //parte do servidor
        //dowload do JSON via Retrofig
        Service service = RetrofitConfig.getRetrofitInstance().create(Service.class);

        //que vai pegar a rota
        Call<List<Monitor>> call = service.getMonitor();

        //faz uma fila do que recebeu do json
        call. enqueue(new Callback<List<Monitor>>() {
            @Override
            //se realmente conseguir pegar cada um desses ítens
            public void onResponse(Call<List<Monitor>> call, Response<List<Monitor>> response) {

                if(response.isSuccessful()){
                    myProgressBar.setVisibility(View.GONE);
                    populateGridView(response.body()); //colocar as informações do json lá no gridview
                }
                else {
                    String errorMessage = response.errorBody().toString();//ver qual erro que deu no response
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            //e esse se ele não conseguiu pegar cada um dos itens
            public void onFailure(Call<List<Monitor>> call, Throwable t) {

                myProgressBar.setVisibility(View.GONE); //para não ficar rodando já que deu erro
                String messageProblem = t.getMessage().toString();
                Toast.makeText(MainActivity.this, messageProblem, Toast.LENGTH_SHORT).show();

            }


        });
    }
}