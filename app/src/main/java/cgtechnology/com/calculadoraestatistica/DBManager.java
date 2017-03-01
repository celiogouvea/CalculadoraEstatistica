package cgtechnology.com.calculadoraestatistica;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by pdj_3 on 09/02/2017.
 */

public class DBManager {

    /****************************
    * Chamando a classe do banco
     ***************************/
    //instanciando a classe
    private static DBHelper dbHelper = null;

    //atribuindo o contexto a classe
    public DBManager(Context context){
        if (dbHelper == null){
            dbHelper = new DBHelper(context);
        }
    }


    /***************************************
    *Metodo de inserção de valor no banco
     ***************************************/
    public void addItem(float valor){
        String sql = "INSERT INTO rool(item) VALUES('"+valor+"')";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
    }

    /***********************************************
     * Metodos implementados o rol
     * *********************************************/

    public ArrayList<String> getAllItem(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM rool ORDER BY item";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> itens = null;
        if (cursor != null && cursor.moveToFirst()){
            itens = new ArrayList<String>();
            do {
                itens.add("Valor->"+cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return itens;
    }

    /***********************************************
     * Metodos implementados para a tabela
     * *********************************************/
    public String vMIM(){
        String Min = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT Min(item) FROM rool";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            Min = cursor.getString(0);
        }
        return Min;
    }
    public String vMAX(){
        String Max = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT Max(item) FROM rool";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            Max = cursor.getString(0);
        }
        return Max;
    }
    public String tBanco(){
        String Max = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT Max(id) FROM rool";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            Max = cursor.getString(0);
        }
        return Max;
    }
    public String getItemTable(float minimo, float maximo, float limite){
        if (limite != maximo) {
            String Max = null;
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String sql = "SELECT COUNT(item) FROM rool WHERE item >= '" + minimo + "' AND item < '" + maximo + "' ";
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                Max = cursor.getString(0);
            }
            return Max;
        }
        else {
            String Max = null;
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String sql = "SELECT COUNT(item) FROM rool WHERE item >= '" + minimo + "' AND item <= '" + maximo + "' ";
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                Max = cursor.getString(0);
            }
            return Max;
        }
    }




}
