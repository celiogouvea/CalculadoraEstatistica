package cgtechnology.com.calculadoraestatistica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by pdj_3 on 10/02/2017.
 */

public class TelaRol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_rol);

        //Chamar o metodo que busca todos os dados do banco
        Banco();
    }
    public void onStart(){
        super.onStart();
    }
    public void onStop(){
        super.onStop();
    }
    public void onResume(){
        super.onResume();
    }
    public void onPause(){
        super.onPause();
    }
    public void onDestroy(){
        super.onDestroy();
    }

    /***********************************************
     * Metodos implementados
     * *********************************************/
    public void Banco(){
        try {
            //criar um arrayList para receber os dados da consulta
            ArrayList<String> itens = null;
            // intancia a classe gerenciador do banco
            DBManager dbMAnager = new DBManager(this);

            //Chama metodo da classe gerenciador do banco para busca dos dados
            itens = dbMAnager.getAllItem();
            if (itens != null)
            {
                //criar um adaptador para inserir os dados em um listView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, itens);
                //define que o listView existe
                ListView listview = (ListView) findViewById(R.id.listViewItens);
                //inserir os dados em tela
                listview.setAdapter(adapter);
            }
            else {
                Toast.makeText(TelaRol.this,"Sem Dados Armazenados", Toast.LENGTH_LONG).show();
                Thread.sleep(500);
                Intent intent = new Intent(this, TelaCadastro.class);
                startActivity(intent);
                this.finish();


            }
        }
        //Metodo para informar que ainda não exite dados no banco
        catch (Exception e){ // ainda precisa ser implementado erro
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setMessage("Erro ->"+e);
            alertDialog.show();
        }
    }

    //Acionar quando botão de voltar for chamado
    @Override
    public void onBackPressed()
    {
        //encerra activity
        this.finish();
    }


}
