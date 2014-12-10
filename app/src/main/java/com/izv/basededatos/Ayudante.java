package com.izv.basededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "futbol.sqlite";
    public static final int DATABASE_VERSION = 3;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

        @Override//Lo que ejecutara cuando el usuario instala la aplicacion y le mete la base de datos
        public void onCreate(SQLiteDatabase db) {

            String sql;
            sql="create table "+Contrato.TablaJugador.TABLA+
                    " (" + Contrato.TablaJugador._ID +
                    " integer primary key autoincrement, " +
                    Contrato.TablaJugador.NOMBRE + " text, " +
                    Contrato.TablaJugador.TELEFONO + " text, " +
                    Contrato.TablaJugador.FNAC+" text)";
            Log.v("sql", sql);
            db.execSQL(sql);

            sql="create table "+Contrato.TablaPartido.TABLA+
                    " (" + Contrato.TablaPartido._ID +
                    " integer primary key autoincrement, " +
                    Contrato.TablaPartido.ID_JUGADOR + " integer unique, " +
                    Contrato.TablaPartido.CONTRINCANTE + " text unique, " +
                    Contrato.TablaPartido.VALORACION+" text)";
            Log.v("sql", sql);
            db.execSQL(sql);
        }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //1º crear tablas de respaldo(identicas)
        String sql="CREATE TABLE respaldo (id integer, nombre text, telefono text, valoracion integer, fnac text)";
        db.execSQL(sql);
        //2º copio los datos de mis tablas en las de respaldo
        sql="insert into respaldo select * from "+Contrato.TablaJugador.TABLA;
        db.execSQL(sql);
        //3º borro las tablas originales
        sql = "drop table " + Contrato.TablaJugador.TABLA;
        db.execSQL(sql);
        //4º creo las tablas nuevas (onCreate)
        onCreate(db);
        //5º copio en las nuevas tablas los datos de las tablas de respaldo
        sql="INSERT INTO "+Contrato.TablaJugador.TABLA+" ("+Contrato.TablaJugador.NOMBRE +" , "+
                Contrato.TablaJugador.TELEFONO+" , "+Contrato.TablaJugador.FNAC +") SELECT nombre, telefono, fnac FROM respaldo";
        db.execSQL(sql);

        sql="INSERT INTO "+Contrato.TablaPartido.TABLA+" ("+Contrato.TablaPartido.ID_JUGADOR+" , "
                +Contrato.TablaPartido.VALORACION+
                " ) SELECT"+Contrato.TablaJugador._ID+", valoracion FROM respaldo r";
        db.execSQL(sql);
        //6º borro las tablas de respaldo
        sql = "drop table respaldo";
        db.execSQL(sql);

    }
}

