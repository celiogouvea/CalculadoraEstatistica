package cgtechnology.com.calculadoraestatistica;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
                itens.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return itens;
    }

    /***********************************************
     * Metodos implementados para a tabela
     * *********************************************/
    //busca do menor valor do banco
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

    //busca do maior valor do banco
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

    //busca do maior id do banco
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

    //busca da quantidade de itens dentro do intervalo de classe
    public String getItemTable(float minimo, float maximo){

        String Max = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT COUNT(item) FROM rool WHERE item >= '" + minimo + "' AND item <= '" + maximo + "' ";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            Max = cursor.getString(0);
        }
        return Max;
    }

    //busca de valor do intem de cada um no banco
    public String valorItem()
    {
        String Max = null;
        float calculo = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT SUM(item) FROM rool";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            calculo = Float.parseFloat(cursor.getString(0));
        }
        Max=""+calculo;
        return Max;
    }

    //busca dos valores iguais no banco
    public String valorIgual(String valor)
    {
        String quantidade = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT COUNT('"+valor+"') FROM  rool GROUP BY item HAVING COUNT(*) > 1";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null && cursor.moveToFirst()){
            quantidade = cursor.getString(0);
        }
        return quantidade;
    }

}
