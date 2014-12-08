package com.izv.basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 06/12/2014.
 */
public class GestorPartido {

    private Ayudante abd;
    private SQLiteDatabase bd;


    public GestorPartido(Context c) {
        abd = new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void openRead() {
        bd = abd.getReadableDatabase();
    }

    public void close() {
        abd.close();
    }

    public long insert(Partido objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaPartido.ID_JUGADOR, objeto.getIdjugador());
        valores.put(Contrato.TablaPartido.VALORACION, objeto.getValoracion());
        valores.put(Contrato.TablaPartido.CONTRINCANTE, objeto.getContrincante());
        long id = bd.insert(Contrato.TablaPartido.TABLA,null, valores);
        //id es el codigo autonumerico
        return id;
    }
    public int delete(Partido objeto) {
        String condicion = Contrato.TablaPartido._ID + " = ? ";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.delete(
                Contrato.TablaPartido.TABLA, condicion,argumentos);
        return cuenta;
    }

    public int update(Partido objeto) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaPartido.ID_JUGADOR, objeto.getIdjugador());
        valores.put(Contrato.TablaPartido.VALORACION, objeto.getValoracion());
        valores.put(Contrato.TablaPartido.CONTRINCANTE, objeto.getContrincante());
        String condicion = Contrato.TablaPartido._ID + " = ?";
        String[] argumentos = { objeto.getId() + "" };
        int cuenta = bd.update(Contrato.TablaPartido.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

    public List<Partido> select(){
        return select(null, null, null);

    }

    public List<Partido> select(String condicion,String[] parametros, String orden) {
        List<Partido> alj = new ArrayList<Partido>();

        //un elemento que me permite recorrer el resultado de la consulta
        Cursor cursor = bd.query(Contrato.TablaPartido.TABLA, null,
                condicion, parametros, null, null, orden);//select *from jugador where condicion
        // bd.query(tabla, columnas,condicion, parametroCondicion, groupBy, having, orderBy);
        cursor.moveToFirst();
        Partido objeto;
        while (!cursor.isAfterLast()) {
            objeto = getRow(cursor);
            alj.add(objeto);
            cursor.moveToNext();
        }
        cursor.close();
        return alj;
    }



    //Obtiene todos los partidos
    public static Partido getRow(Cursor c) {
        Partido objeto = new Partido();
        objeto.setId(c.getLong(0));
        objeto.setIdjugador(c.getLong(1));
        objeto.setContrincante(c.getString(2));
        objeto.setValoracion(c.getString(3));
        return objeto;
    }


    public Partido getRow(long id){
        List<Partido> lp=select(Contrato.TablaPartido._ID + "= ?",new String[]{id + ""},null);
        if(!lp.isEmpty())
            return lp.get(0);
        return null;
    }

    //Nos devuelve el cursor de la consulta
    public Cursor getCursor(String condicion,String[] parametros, String orden) {
        Cursor cursor= bd.query(Contrato.TablaPartido.TABLA, null, condicion, parametros,null, null, orden);
        return cursor;
    }

}
