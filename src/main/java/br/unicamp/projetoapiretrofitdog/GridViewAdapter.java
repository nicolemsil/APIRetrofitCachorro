package br.unicamp.projetoapiretrofitdog;

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

public class GridViewAdapter extends BaseAdapter { //a principio da erro mas é so da alt + enter que ele cria os metodos necessario

    private List<Dog> listaDog;
    private Context context; //pegar o contexto

    public GridViewAdapter(Context context, List<Dog> recebeParametroListaDog /*informação do json*/) {
        this.listaDog = recebeParametroListaDog;
        this.context = context;
    }

    @Override
    public int getCount() { //contar quantos itens o grid vai ter - pra isso ele vai precisar receber alguns parâmetros - então e disso que crio o private List<Dog> listaDog;

        return listaDog.size();
    }

    @Override
    public Object getItem(int posicao) { //posicao em que o item esta
        return listaDog.get(posicao);
    }

    @Override
    public long getItemId(int posicao) { //pegar o id
        return posicao;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null) //se a view ainda não foi criada vai ser crida
        {
            view = LayoutInflater.from(context).inflate(R.layout.layout_gridview, viewGroup, false); //false pq nao tem que anexar nada nesse ultimo parametro
        }

        TextView tvNome = view.findViewById(R.id.tvNome);
        TextView tvRaca = view.findViewById(R.id.tvRaca);
        ImageView dogImageView = view.findViewById(R.id.dogImageView);

        final Dog thisDog = listaDog.get(position); /*pegar a posicao de cada item da listaDog*/

        //settando as informaçãos nas variáveis
        tvNome.setText(thisDog.getNome());
        tvRaca.setText(thisDog.getRaca());

        if(thisDog.getImagem() != null && thisDog.getImagem().length() > 0) //se tiver a imagem...
        {
            Picasso.get().load(thisDog.getImagem()).into(dogImageView);
        }
        else
            Toast.makeText(context, "Não carregou a imagem", Toast.LENGTH_SHORT).show();

        //fazer aparecer um toastzinho quando clicar nas imagens
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, thisDog.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
