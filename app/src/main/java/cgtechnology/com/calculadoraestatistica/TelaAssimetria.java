package cgtechnology.com.calculadoraestatistica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TelaAssimetria extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_assimetria);


    }


    //Acionar quando botão de voltar for chamado
    @Override
    public void onBackPressed() {
        //encerra activity
        this.finish();
    }
}