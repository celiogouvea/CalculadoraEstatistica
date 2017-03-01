package cgtechnology.com.calculadoraestatistica;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by pdj_3 on 22/02/2017.
 */

public class TelaTable extends AppCompatActivity {

    //instancia o gerenciador do banco
    DBManager db = new DBManager(this);

    //usado para formatar saida de numeros na tela
    DecimalFormat df = new DecimalFormat("######.##");


    //valores usados nas formulas
    float vInicial; //recebe os valores minimo de cada classe e passa para o metodo de metodo da classe de gerenciador do banco
    float vFinal; //recebe os valores maximo de cada classe e passa para o metodo de metodo da classe de gerenciador do banco
    float vMin; //determina o menor valor do banco
    float vMax; //determina o maior valor do banco
    float calculado; // determina qual e amplitudo
    float tTable; // determina a quantidade de classe
    float intervalo; // determina qual será o intervalo de uma classe a outra
    float fac; // determina a frequencia acumulada
    float frac; // determina a frequencia relativa acumulada
    float fr; // determina a frequencia relativa




    //Cria o array de dados que sera utilizado para popular o arraylist
    ArrayList<DadosTabela> vetRegistros = new ArrayList<DadosTabela>();
    Adaptador adaptador = null;
    ListView vrLista = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_table);

        //Insere dados no array
        vrLista = (ListView) findViewById(R.id.listViewItensTable);

        //chama metodo que apresentara a tabela ao usuario
        criacaoTable();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    public void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
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


    /***********************************************
     * Metodos implementados
     *********************************************/
    public void criacaoTable() {
        valorMin();
        valorMax();
        tClasse();
        calculo();
        String iten = null;
        DBManager dbMAnager = new DBManager(this);
        vInicial = vMin;
        vFinal = vMin + intervalo;

        for (int i = 0; i < tTable; i++) {
            iten = dbMAnager.getItemTable(vInicial, vFinal,vMax);
            Log.i("INFO", " Valor Maximo = " + vMax);
            Log.i("INFO", " Valor Minimo = " + vMin);
            preencher(iten);

            //Insere dados no array
            vetRegistros.add(new DadosTabela(i + 1, " " +df.format(vInicial) + "  |-->  " +df.format(vFinal),
                    "  " + iten, "  " + df.format(fac), "   " + df.format(fr), "   " + df.format(frac) ));
            adaptador = new Adaptador(this, vetRegistros);
            vInicial = vFinal;
            vFinal = vFinal + intervalo;
            iten = null;
        }
        vrLista.setAdapter(adaptador);
    }

    public void preencher(String i) {
        fac = fac + Float.parseFloat(i.toString());
        fr = (100 *Float.parseFloat(i.toString()) / vMax);
        frac = frac + fr;
        Log.i("INFO", " frequencia acumulada = " + fac);
        Log.i("INFO", " frequencia relativa = " + fr);
        Log.i("INFO", " frequencia relativa acumulada = " + frac);
    }


    public void valorMin() {
        String busca;
        busca = db.vMIM();
        vMin = Float.parseFloat(busca.toString());
    }

    public void valorMax() {
        String busca;
        busca = db.vMAX();
        vMax = Float.parseFloat(busca.toString());
    }

    public void calculo() {
        calculado = vMax - vMin;
        intervalo = (calculado) / tTable;

        Log.i("INFO", " Intervalo = " + intervalo);
        Log.i("INFO", " Tamanho da Tabela = " + tTable);
        Log.i("INFO", " Amplitude = " + calculado);
    }


    public void tClasse() {
        String busca;
        double k;
        busca = db.tBanco();
        k = Math.sqrt(Double.parseDouble(busca));
        int aux = (int) k;
        if (((double) aux) == k) {
            tTable = (int) k;
        } else {
            tTable = (int) k + 1;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("TelaTable Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
}



class Adaptador extends ArrayAdapter<DadosTabela>{
    //contrutor da classe
    public Adaptador (Context context, ArrayList<DadosTabela> list){
        super(context,0,list);
    }
    //Metodo de chamar todos os itens da da lista
    public View getView (int indice, View reciclada, ViewGroup pai){
        //Acessar objetos do registro
        DadosTabela dados = getItem(indice);

        //Buscar se a celulas vazias
        if (reciclada == null){
            reciclada = LayoutInflater.from(getContext()).inflate(R.layout.activity_registro, pai,false);
        }



        TextView texto  = (TextView) reciclada.findViewById(R.id.classe);
        texto.setText(dados.classe+"");

        texto  = (TextView) reciclada.findViewById(R.id.intervalo);
        texto.setText(dados.intervalo+"");

        texto  = (TextView) reciclada.findViewById(R.id.freq);
        texto.setText(dados.Fi+"");

        texto  = (TextView) reciclada.findViewById(R.id.freqAcu);
        texto.setText(dados.Fac+"");

        texto  = (TextView) reciclada.findViewById(R.id.freqRel);
        texto.setText(dados.Fr+"");

        texto  = (TextView) reciclada.findViewById(R.id.freqAcuRel);
        texto.setText(dados.Frac+"");

        return reciclada;

    }

}

