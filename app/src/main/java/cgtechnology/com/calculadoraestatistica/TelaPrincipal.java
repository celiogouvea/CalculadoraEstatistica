package cgtechnology.com.calculadoraestatistica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
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
    //Metodo botão para troca da tela sobre
    public void trocarTelaSB(View view){
        Intent intent = new Intent(TelaPrincipal.this, TelaSobre.class);
        startActivity(intent);
    }
    //Metodo botão para troca de tela rol
    public void trocarTelaLista(View view){
        Intent intent = new Intent(TelaPrincipal.this, TelaRol.class);
        startActivity(intent);
    }
    //Metodo botão para troca de tela cadastro
    public void trocarTelaCadastro(View view){
        Intent intent = new Intent(TelaPrincipal.this, TelaCadastro.class);
        startActivity(intent);
    }
    //Metodo botão para troca de tela tabela
    public void trocarTelaTabela(View view){
        Intent intent = new Intent(TelaPrincipal.this, TelaTable.class);
        startActivity(intent);
    }

}
