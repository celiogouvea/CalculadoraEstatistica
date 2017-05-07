package cgtechnology.com.calculadoraestatistica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


}
