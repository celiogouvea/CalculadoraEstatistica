package cgtechnology.com.calculadoraestatistica;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaTabelaSimples extends AppCompatActivity {

    ArrayList<DadosTabelaSimples> registros = new ArrayList<>();
    Adaptador1 adaptador = null;
    ListView lista = null;
    DBManager db = new DBManager(this);

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
        String valor = null;

        for (int i = 0; i < listaItens.size();i++)
        {
            valor = db.valorIgual(listaItens.get(i));
            registros.add(new DadosTabelaSimples(listaItens.get(i),valor));
            adaptador = new Adaptador1(this,registros);
            valor = null;
        }
        lista.setAdapter(adaptador);
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