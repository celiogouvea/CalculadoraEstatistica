package cgtechnology.com.calculadoraestatistica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TelaTabelaSimples extends AppCompatActivity {

    ArrayList<DadosTabelaSimples> registros = new ArrayList<>();
    ArrayList<String> valores = new ArrayList<>();
    Adaptador1 adaptador = null;
    ListView lista = null;

    DBManager db = new DBManager(this);

    //usado para formatar saida de numeros na tela
    DecimalFormat df = new DecimalFormat("######.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_tabela_simples);

        lista = (ListView) findViewById(R.id.lvFrequenciaSimples);

        ArrayList<String> itens = db.getAllItem();
        if (itens != null)
        {
            //chama metodo que apresentara a tabela ao usuario
            preencherLista();
        }
        else {
            Toast.makeText(TelaTabelaSimples.this,getResources().getString(R.string.twAvisoTabelaVazia), Toast.LENGTH_LONG).show();
            SystemClock.sleep(500);
            Intent intent = new Intent(this, TelaCadastro.class);
            startActivity(intent);
            this.finish();
        }
    }

    public void onStart() {
        super.onStart();
    }
    public void onStop() {
        super.onStop();
    }
    public void onResume() {
        super.onResume();
    }
    public void onPause() {
        super.onPause();
    }
    public void onDestroy() {
        super.onDestroy();
    }

    /************************************************+*
     * metodo de preencimento da lista
     *************************************************/
    public void preencherLista()
    {
        ArrayList<String> listaItens = new ArrayList<>();
        listaItens = db.getAllItem();
        ArrayList<String> conferir = new ArrayList<>();
        conferir.add("0");
        String tamanhoBanco = db.tBanco();
        int calculo = Integer.parseInt(tamanhoBanco)/2;
        String valor = null;
        float media = 0;
        float mediana = 0;
        float moda = 0;

        for (int i = 0; i < listaItens.size();i++)
        {

            conferir.add(listaItens.get(i));
            valor = db.valorIgual(listaItens.get(i));
            int f = i+1;



            if (i == calculo)
            {
                mediana = Float.parseFloat(listaItens.get(i));
            }

            if (i == 0)
            {
                registros.add(new DadosTabelaSimples(listaItens.get(i), valor));
                adaptador = new Adaptador1(this, registros);
                valores.add(valor);
                if (Float.parseFloat(valor)>moda)
                {
                    moda = Float.parseFloat(listaItens.get(f));
                }
                valor = null;
            }
            if (i >0 && Integer.parseInt(listaItens.get(i)) > Integer.parseInt(conferir.get(i)))
            {
                registros.add(new DadosTabelaSimples(listaItens.get(i), valor));
                adaptador = new Adaptador1(this, registros);
                valores.add(valor);
                if (Float.parseFloat(valor)>moda)
                {
                    moda = Float.parseFloat(listaItens.get(f));
                }
                valor = null;
            }
            else {
                valor = null;
            }
        }

        lista.setAdapter(adaptador);
        media = Integer.parseInt(db.tBanco())/2;
        String busca = db.valorEspeficico(""+media);
        Float resultadoMedia = Float.parseFloat(busca);

        TextView resuMedia = (TextView) findViewById(R.id.tvMediaSimples);
        resuMedia.setText(""+df.format(resultadoMedia));
        resuMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder editar = new AlertDialog.Builder(TelaTabelaSimples.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_apresentacao_calculo_media, null);
                editar.setView(mView);
                AlertDialog alert = editar.create();
                alert.show();
            }
        });

        TextView resuMediana = (TextView) findViewById(R.id.tvMedianaSimples);
        resuMediana.setText(""+df.format(mediana));
        resuMediana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder editar = new AlertDialog.Builder(TelaTabelaSimples.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_apresentacao_calculo_mediana_simples, null);
                editar.setView(mView);
                AlertDialog alert = editar.create();
                alert.show();
            }
        });


        TextView resuModa = (TextView) findViewById(R.id.tvModaSimples);
        resuModa.setText(""+df.format(moda));
        resuModa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder editar = new AlertDialog.Builder(TelaTabelaSimples.this);
                View mView = getLayoutInflater().inflate(R.layout.activity_apresentacao_calculo_moda_simples, null);
                editar.setView(mView);
                AlertDialog alert = editar.create();
                alert.show();
            }
        });


    }

    public void Graphic(View view) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("Lista",valores);
        Intent intent = new Intent(TelaTabelaSimples.this, Graphic.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,0);
    }

}



class Adaptador1 extends ArrayAdapter<DadosTabelaSimples> {
    //contrutor da classe
    public Adaptador1 (Context context, ArrayList<DadosTabelaSimples> list){
        super(context,0,list);
    }
    //Metodo de chamar todos os itens da da lista
    public View getView (int indice, View reciclada, ViewGroup pai){
        //Acessar objetos do registro
        DadosTabelaSimples dados = getItem(indice);

        //Buscar se a celulas vazias
        if (reciclada == null){
            reciclada = LayoutInflater.from(getContext()).inflate(R.layout.activity_registro_simples, pai,false);
        }


        TextView texto  = (TextView) reciclada.findViewById(R.id.tvTabelaSimplesValor);
        texto.setText(dados.valor+"");

        texto  = (TextView) reciclada.findViewById(R.id.tvTabelaSimplesQtd);
        texto.setText(dados.frequencia+"");

        return reciclada;

    }

}