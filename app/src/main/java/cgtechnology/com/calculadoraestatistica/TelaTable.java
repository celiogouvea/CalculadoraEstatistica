package cgtechnology.com.calculadoraestatistica;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    double d = 0.0001;//variavel para a utima laco de repetição
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

    float classe[] = null;
    float valorAcumolado[] = null;


    String resuMedia;
    String resuMediana;
    String resuModa;

    TextView media = null;
    TextView mediana = null;
    TextView moda = null;




    //Cria o array de dados que sera utilizado para popular o arraylist
    ArrayList<DadosTabela> vetRegistros = new ArrayList<>();
    Adaptador adaptador = null;
    ListView vrLista = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_table);

        //Insere dados no array
        vrLista = (ListView) findViewById(R.id.listViewItensTable);

        //chama metodo que apresentara a tabela ao usuario
        criacaoTable();
        //set textView para MMM
        media = (TextView) findViewById(R.id.tvResuMedia);
        media.setText(resuMedia);
        mediana = (TextView) findViewById(R.id.tvResuMediana);
        mediana.setText(resuMediana);
        moda = (TextView) findViewById(R.id.tvResuModa);
        moda.setText(resuModa);

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


    /***********************************************
     * Metodos implementados
     *********************************************/
    //metodo de criação da tabela
    public void criacaoTable() {
        valorMin(); //chamar metodo de nenor valor no banco
        valorMax(); //chamar metodo de maior valor no banco
        tClasse();  //chamar metodo de definir o tamanho de da classe
        calculo();  //chamar metodo de calculo do intervalo

        String iten; // item que recebera quantidade de elementos do
        DBManager dbMAnager = new DBManager(this); // intanciando a classe de gerenciador do banco de dados
        vInicial = vMin; //vIncicial recebe vMin para passar para metodo da classe do gerenciador definir o valor inicial do intervalo
        vFinal = vMin + intervalo;//vFinal recebe vMin mais o intervalo para passar para o metodo da classe de gerenciador definir o valor final do intervalo

        //laço de reptição pra criar a tabelade dados
        for (int i = 0; i < tTable; i++) {

            iten = dbMAnager.getItemTable(vInicial, vFinal);// chamando metodo da classe de gerenciador
            preencher(iten); //chamando metodo de atribuição de valores e adicionando a suas classes



            //Insere dados no array
            vetRegistros.add(new DadosTabela(i + 1, " " +df.format(vInicial) + "  |-->  " +df.format(vFinal),
                    "  " + iten, "  " + df.format(fac), "   " + df.format(fr), "   " + df.format(frac) ));

            adaptador = new Adaptador(this, vetRegistros);//inserindo dados na classe de adaptador para apresentação no view

            vInicial = vFinal; // novo valor inicial para a proxima iteração do laço de reptição
            if (i == (tTable-2)){
                vFinal = (float) ((vFinal+d)+intervalo);
            }
            else{
                vFinal = vFinal + intervalo;//novo valor fical para a proxima iteração do laço de repetição
            }
        }
        vrLista.setAdapter(adaptador);// colocando os dados na tela
        //
        mediaModaMediana();      //chamar metodo de media, mediana e moda
    }

    //metodo de atribuição de valores nas variaveis
    public void preencher(String i) {
        String busca = db.tBanco();
        int k = Integer.parseInt(busca.toString());
        fac = fac + Float.parseFloat(i.toString());
        fr = (100 *Float.parseFloat(i.toString()) / k);
        frac = frac + fr;
    }

    //metodo de busca do menor valor no banco
    public void valorMin() {
        String busca;
        busca = db.vMIM();
        vMin = Float.parseFloat(busca.toString());
    }

    //metodo de busca do maior valor no banco
    public void valorMax() {
        String busca;
        busca = db.vMAX();
        vMax = Float.parseFloat(busca.toString());
    }

    //metodo para calcular o intervalo
    public void calculo() {
        calculado = vMax - vMin;
        intervalo = (calculado) / tTable;
    }

    //metodo de definir o tamanho maximo de classes que a tabela deverar ter
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
    //calculo de tendenncia de medias centrais
    public void mediaModaMediana()
    {
        //media
        String busca = null;
        busca = db.valorItem();
        float total = Float.parseFloat(busca);
        resuMedia = ""+ total / 2;


        //mediana
        String buscaTdb = db.tBanco();
        int f = Integer.parseInt(buscaTdb.toString());
        int i = (int)total/f;




        //moda
       // resuModa =""+((3*reusltadoMediana)-(2*total));
    }

    public void Graphic(View view)
    {
        Intent intent = new Intent(TelaTable.this, Graphic.class);
        startActivity(intent);
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