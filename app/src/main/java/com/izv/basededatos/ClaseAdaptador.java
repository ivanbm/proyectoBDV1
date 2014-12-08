package com.izv.basededatos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ClaseAdaptador extends CursorAdapter {
    private TextView tv1,tv2,tv3;


    public ClaseAdaptador(Context context, Cursor c) {
        super(context, c,true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup vg) {
        LayoutInflater i = LayoutInflater.from(vg.getContext());
        View v = i.inflate(R.layout.lista_detalle, vg, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        GestorJugador gj=new GestorJugador(context);
        Jugador j;
        j=gj.getRow(cursor);

        tv1=(TextView) view.findViewById(R.id.tvnombre);
        tv2=(TextView) view.findViewById(R.id.tvtelefono);
        tv3=(TextView) view.findViewById(R.id.tvfecha);

        tv1.setText(j.getNombre());
        tv2.setText(j.getTelefono());
        tv3.setText(j.getFnac());
    }

}
