package com.izv.basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 07/12/2014.
 */
public class GestorJoinJugadorPartido {

    private Ayudante abd;
    private SQLiteDatabase bd;


    public GestorJoinJugadorPartido(Context c) {
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



    public List<JoinJugadoPartido> select(long idj) {

        List<JoinJugadoPartido> aljp = new ArrayList<JoinJugadoPartido>();

        //un elemento que me permite recorrer el resultado de la consulta
        /*Cursor cursor = bd.rawQuery("select j.nombre, p.contrincante, p.valoracion from " +
                Contrato.TablaJugador.TABLA + " j join " + Contrato.TablaPartido.TABLA + " p on j.id=p.idjugador where p.idjugador="+idj, null);
        */
        Cursor cursor = bd.rawQuery("select * from "+Contrato.TablaPartido.TABLA, null);
        cursor.moveToFirst();

        JoinJugadoPartido ojp;
        while (!cursor.isAfterLast()) {
            ojp = getRowJP(cursor);
            aljp.add(ojp);
            cursor.moveToNext();
        }
        cursor.close();
        return aljp;
    }


    public static JoinJugadoPartido getRowJP(Cursor c) {
        JoinJugadoPartido objeto = new JoinJugadoPartido();
        objeto.setNombre(c.getString(1));
        objeto.setContrincante(c.getString(2));
        objeto.setValoracion(c.getString(3));
        System.out.println("AAA "+c.getString(0)+ " - "+c.getString(1)+" - "+c.getString(2)+" - "+c.getString(3));
        return objeto;
    }

    public JoinJugadoPartido getRow(long id){
        List<JoinJugadoPartido> lp=select(id);
        if(!lp.isEmpty())
            return lp.get(0);
        return null;
    }

    public Cursor getCursor(String condicion,String[] parametros, String orden) {
        //Cursor cursor= bd.rawQuery("select j.nombre, p.contrincante, p.valoracion from " + Contrato.TablaJugador.TABLA + " j join " + Contrato.TablaPartido.TABLA + " p on j."+Contrato.TablaJugador._ID+" = p." + Contrato.TablaPartido.ID_JUGADOR, null);
        Cursor cursor= bd.rawQuery("select * from " + Contrato.TablaPartido.TABLA, null);
        //Cursor cursor = bd.rawQuery("SELECT nombre, contrincante, valoracion FROM "+Contrato.TablaJugador.TABLA+" j INNER JOIN "+Contrato.TablaPartido.TABLA+" p WHERE j."+Contrato.TablaJugador._ID+" = p."+Contrato.TablaPartido.ID_JUGADOR,null);
        return cursor;
    }

}
