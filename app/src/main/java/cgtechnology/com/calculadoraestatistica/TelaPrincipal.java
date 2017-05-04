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
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TelaPrincipal extends AppCompatActivity {

    ImageView imagem = null;
    RelativeLayout layout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        imagem = (ImageView) findViewById(R.id.ivFundo);
        layout = (RelativeLayout) findViewById(R.id.activity_tela_principal);
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
    //Metodo botão para troca da tela sobre
    public void trocarTelaSB(View view){
        Intent intent = new Intent(TelaPrincipal.this, TelaSobre.class);
        startActivity(intent);
    }

    //Metodo botão para troca de tela cadastro
    public void trocarTelaCadastro(View view){
        Intent intent = new Intent(TelaPrincipal.this, TelaCadastro.class);
        startActivity(intent);
    }

    //metodo para adicionar cor ao layout
    public void corLayout(){
        BitmapDrawable drawable = (BitmapDrawable) imagem.getDrawable();
        final Bitmap bitmap = drawable.getBitmap();

        Palette.Builder builder = new Palette.Builder(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getLightVibrantSwatch();
                if (vibrant != null)
                {
                    layout.setBackgroundColor(vibrant.getRgb());
                }
            }
        });
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
                Intent intent = new Intent(TelaPrincipal.this, TelaTabelaSimples.class);
                startActivity(intent);
            }
        });

        continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPrincipal.this, TelaTable.class);
                startActivity(intent);
            }
        });

        editar.setView(mView);
        AlertDialog alert = editar.create();
        alert.show();
    }

}
