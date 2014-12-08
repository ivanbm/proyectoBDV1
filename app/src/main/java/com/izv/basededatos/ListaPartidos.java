package com.izv.basededatos;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;


public class ListaPartidos extends Activity {
    private ClaseAdaptadorPartidos cap;
    private ListView lv2;
    private GestorJoinJugadorPartido gjp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_partidos);

        gjp = new GestorJoinJugadorPartido(this);
        lv2 = (ListView) findViewById(R.id.listView2);
    }

    @Override
    protected void onResume() {
        gjp.open();
        super.onResume();

        lv2 = (ListView) findViewById(R.id.listView2);
        Cursor c = gjp.getCursor(null,null,null);
        cap = new ClaseAdaptadorPartidos(this, c);
        lv2.setAdapter(cap);


    }

    @Override
    protected void onPause() {
        super.onPause();
        gjp.close();
    }
}
