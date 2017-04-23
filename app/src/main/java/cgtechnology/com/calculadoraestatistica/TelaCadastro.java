package cgtechnology.com.calculadoraestatistica;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by pdj_3 on 10/02/2017.
 */

public class TelaCadastro extends AppCompatActivity {


    private EditText dados;
    float valor;
    DBManager dbMAnager = new DBManager(this);

    ImageView imagem = null;
    RelativeLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        dados = (EditText) findViewById(R.id.etCadastro);
        imagem = (ImageView) findViewById(R.id.ibRol);
        layout = (RelativeLayout) findViewById(R.id.layoutCadastro);
        corLayout();
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
            dados.setText("");
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
    //Metodo botão para troca de tela rol
    public void trocarTelaLista(View view){
        Intent intent = new Intent(this, TelaRol.class);
        startActivity(intent);
    }
    //Metodo botão para troca de tela tabela
    public void trocarTelaTabela(View view){
        Intent intent = new Intent(this, TelaTable.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        this.finish();
    }


    public void corLayout(){
        BitmapDrawable drawable = (BitmapDrawable) imagem.getDrawable();
        final Bitmap bitmap = drawable.getBitmap();

        Palette.Builder builder = new Palette.Builder(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getLightMutedSwatch();
                if (vibrant != null)
                {
                    layout.setBackgroundColor(vibrant.getRgb());
                }
            }
        });
    }
}
