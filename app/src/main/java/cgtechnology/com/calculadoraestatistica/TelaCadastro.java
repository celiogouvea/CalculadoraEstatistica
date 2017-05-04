package cgtechnology.com.calculadoraestatistica;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by pdj_3 on 10/02/2017.
 */

public class TelaCadastro extends AppCompatActivity {


    private EditText dados;
    private EditText qtd = null;
    float valor;
    DBManager dbMAnager = new DBManager(this);

    ImageView imagem = null;
    RelativeLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        dados = (EditText) findViewById(R.id.etCadastro);
        qtd = (EditText) findViewById(R.id.etQtd);
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
            valor = Float.parseFloat(dados.getText().toString());
            int q = Integer.parseInt(qtd.getText().toString());
                for (int i = 0; i<q; i++)
                {
                    dbMAnager.addItem(valor);
                }
                AlertDialog.Builder alertDialog;
                alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle(getResources().getString(R.string.avisoSalvar));
                dados.setText("");
                qtd.setText("1");
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

        AlertDialog.Builder editar = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.activity_troca_tabela, null);
        Button simples = (Button) mView.findViewById(R.id.btTabelaRol);
        Button continua = (Button) mView.findViewById(R.id.btTabelaContinua);

        simples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaCadastro.this, TelaTabelaSimples.class);
                startActivity(intent);
            }
        });

        continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaCadastro.this, TelaTable.class);
                startActivity(intent);
            }
        });

        editar.setView(mView);
        AlertDialog alert = editar.create();
        alert.show();
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
