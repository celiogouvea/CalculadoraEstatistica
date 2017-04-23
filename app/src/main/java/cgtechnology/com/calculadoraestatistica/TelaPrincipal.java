package cgtechnology.com.calculadoraestatistica;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
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

}
