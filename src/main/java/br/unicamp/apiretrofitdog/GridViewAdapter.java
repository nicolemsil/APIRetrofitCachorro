package br.unicamp.apiretrofitdog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

//implementação dos métodos
class GridViewAdapter extends BaseAdapter {

    //para saber quantos gridviews vai ter, terá que receber um parâmetro
    //vai receber a lista do json

    private List<Monitor> listaMonitor;
    private Context context;

    //construtor para o context

    public GridViewAdapter(Context context, List<Monitor> recebeParametrosListaMonitor){
        this.context = context;
        this.listaMonitor = recebeParametrosListaMonitor;
    }

    @Override
    public int getCount() {

        return listaMonitor.size(); //conta quantos itens foram recebidos
    }

    @Override
    public Object getItem(int posicao) {
        return listaMonitor.get(posicao); //posição que está o item de cada lista
    }

    @Override
    public long getItemId(int posicao) {

        return posicao; //também vai pegar a posição
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {

        //se estiver nulo quer dizer que ainda não foi criada
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_gridview,viewGroup,false);
        }

        TextView tvNome = view.findViewById(R.id.tvNome);
        TextView tvHorario = view.findViewById(R.id.tvHorario);
        ImageView monitorImageView = view.findViewById(R.id.monitorImageView);


        final Monitor thisMonitor = listaMonitor.get(posicao);

        tvNome.setText(thisMonitor.getNome());
        tvHorario.setText(thisMonitor.getHorario());

        if(thisMonitor.getImagem() != null && thisMonitor.getImagem().length() > 0){
            Picasso.get().load(thisMonitor.getImagem()).into(monitorImageView);
        }

        else{
            Toast.makeText(context, "Não carregou a imagem", Toast.LENGTH_SHORT).show();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, thisMonitor.getNome(), Toast.LENGTH_SHORT).show();

            }
        });

        return view; //o getView que vai construir tudo e vai retornar a view
    }
}
