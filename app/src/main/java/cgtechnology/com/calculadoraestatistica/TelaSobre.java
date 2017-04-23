package cgtechnology.com.calculadoraestatistica;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by pdj_3 on 09/02/2017.
 */

public class   TelaSobre extends AppCompatActivity {


    ImageView imagem = null;
    RelativeLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sobre);


        imagem = (ImageView) findViewById(R.id.ivUnitins);
        layout = (RelativeLayout) findViewById(R.id.layoutSobre);
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
