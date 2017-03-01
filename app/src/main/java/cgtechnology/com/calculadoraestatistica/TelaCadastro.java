package cgtechnology.com.calculadoraestatistica;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by pdj_3 on 10/02/2017.
 */

public class TelaCadastro extends AppCompatActivity {


    private EditText dados;
    private BotaoSimNao event;
    float valor;
    String d;
    DBManager dbMAnager = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        dados = (EditText) findViewById(R.id.etCadastro);
        event = new BotaoSimNao();

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
    //metodos de salvar valores no banco
    public void salvar(View view){
        try {
            //tranforma campo de texto em um tipo float
            valor = Float.parseFloat(dados.getText().toString());
            // passa para o gerenciador do banco para salvar
            dbMAnager.addItem(valor);
            //criar um dialogo para apresentação para usuario decidir se ira continuar a salvar ou não
            AlertDialog.Builder alertDialog;
            alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(getResources().getString(R.string.avisoSalvar));
            alertDialog.setMessage(getResources().getString(R.string.avisoContinuar));
            alertDialog.setPositiveButton("Sim",event);
            alertDialog.setNegativeButton("Não",event);
            alertDialog.show();
        }
        //caso o usuario não tenha informado nenhum valor
        catch (Exception e){
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setMessage(getResources().getString(R.string.avisoCampoVazio));
            alertDialog.show();
        }
    }
    // Metodo do Botão voltar para a tela inicial
    public void trocarTelaSB(View view){
        Intent intent = new Intent(TelaCadastro.this, TelaPrincipal.class);
        startActivity(intent);
    }
}

/*************************************************************
*Classe de que implementa o alerta com usuario para inserir mais itens no banco
 ************************************************************/
class BotaoSimNao implements DialogInterface.OnClickListener{


    @Override
    public void onClick(DialogInterface dialogInterface, int codBotao) {
        Log.i("INFO",codBotao+"");
        if (codBotao == -2){

        }
    }


}
