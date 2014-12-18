package com.izv.basededatos;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Ivan on 17/12/2014.
 */
public class AnadirPartido extends Activity {
    private long id;
    private GestorPartido gp;
    private EditText cont, valo;
    private ClaseAdaptador ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_partido);

        gp=new GestorPartido(this);
        id = getIntent().getExtras().getLong("id");
    }


    @Override
    protected void onPause() {
        super.onPause();
        gp.close();
    }

    @Override
    protected void onResume() {
        gp.open();
        super.onResume();
        Cursor c = gp.getCursor(null,null,null);
        ca = new ClaseAdaptador(this, c);
    }

    public void anadirPartido(View v){
        String con,val;

        cont = (EditText)findViewById(R.id.etContrincante);
        valo = (EditText)findViewById(R.id.etValoracion);

        con = cont.getText().toString();
        val = valo.getText().toString();

        System.out.println(id + " - "+con+ " - "+val);

        Partido p = new Partido(id,con,val);
        long res = gp.insert(p);

        ca.getCursor().close();
        ca.changeCursor(gp.getCursor(null,null,null));

        Toast.makeText(this,"Partido insertado, id:"+res,Toast.LENGTH_SHORT).show();
    }
}
