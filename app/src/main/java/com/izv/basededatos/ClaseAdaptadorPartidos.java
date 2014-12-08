package com.izv.basededatos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ClaseAdaptadorPartidos extends CursorAdapter {
    private TextView tv1,tv2,tv3;


    public ClaseAdaptadorPartidos(Context context, Cursor c) {
        super(context, c,true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup vg) {
        LayoutInflater i = LayoutInflater.from(vg.getContext());
        View v = i.inflate(R.layout.detalle_partidos, vg, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Cursor cu;

        GestorJoinJugadorPartido gj=new GestorJoinJugadorPartido(context);
        //cu=gj.getCursor();
        JoinJugadoPartido jjp;
        jjp=gj.getRowJP(cursor);

        tv1=(TextView) view.findViewById(R.id.tvpjugador);
        tv2=(TextView) view.findViewById(R.id.tvpcont);
        tv3=(TextView) view.findViewById(R.id.tvpval);

        //System.out.println(jjp.getNombre()+ " - "+jjp.getContrincante()+" - "+jjp.getValoracion());

        tv1.setText(jjp.getNombre());
        tv2.setText(jjp.getContrincante());
        tv3.setText(jjp.getValoracion());
    }

}
