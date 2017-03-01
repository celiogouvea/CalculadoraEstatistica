package cgtechnology.com.calculadoraestatistica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pdj_3 on 09/02/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "teste";
    private static int DV_VERSIN = 1;

    private static String tabela = "CREATE TABLE IF NOT EXISTS rool(id INTEGER PRIMARY KEY AUTOINCREMENT, item FLOAT);";

    public DBHelper(Context context){

        super(context, DB_NAME, null, DV_VERSIN);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(tabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionAntiga, int versionAtual) {

    }
}
