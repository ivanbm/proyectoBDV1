package com.izv.basededatos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {

    private GestorJugador gj;
    private GestorPartido gp;
    private ClaseAdaptador ca;
    private EditText etnombre, ettelefono, etvaloracion, etfecha, etcont;
    private ListView lv;
    private static int VER_PARTIDOS = 1;
    private static int ANADIR_PARTIDO = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gj=new GestorJugador(this);
        gp=new GestorPartido(this);
        etnombre=(EditText)findViewById(R.id.etNombre);
        ettelefono=(EditText)findViewById(R.id.etTelefono);
        /*etvaloracion=(EditText)findViewById(R.id.etValoracion);
        etfecha=(EditText)findViewById(R.id.etFnac);
        etcont=(EditText)findViewById(R.id.etCont);*/
        lv=(ListView)findViewById(R.id.listView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor c=(Cursor)lv.getItemAtPosition(position);
                Jugador j=GestorJugador.getRow(c);

                Intent i = new Intent(MainActivity.this, AnadirPartido.class);
                i.putExtra("id",j.getId());
                startActivityForResult(i, ANADIR_PARTIDO);
            }
        });

    }

    @Override
    protected void onResume() {
        gj.open();
        gp.open();
        super.onResume();
        final ListView lv= (ListView) findViewById(R.id.listView);
        Cursor c = gj.getCursor(null,null,null);
        ca = new ClaseAdaptador(this, c);
        lv.setAdapter(ca);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Cursor c=(Cursor)lv.getItemAtPosition(pos); //El elemento del cursor que estas haciendo click
                Jugador j=GestorJugador.getRow(c);
                gj.delete(j);
                ca.changeCursor(gj.getCursor(null,null,null));
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        gj.close();
        gp.close();
    }

    public void alta(View v){
        String nombre,telefono,fecha;
        nombre=etnombre.getText().toString();
        telefono=ettelefono.getText().toString();
        fecha=etfecha.getText().toString();
        Jugador j=new Jugador(nombre,telefono,fecha);
        long id=gj.insert(j);

        ca.getCursor().close();
        ca.changeCursor(gj.getCursor(null,null,null));

        Toast.makeText(this,"Jugador insertado, id:"+id,Toast.LENGTH_SHORT).show();
    }

    public void ver(View v){

        gj.open();
        Cursor c = gj.getCursor(null,null,null);
        ca = new ClaseAdaptador(this, c);
        lv= (ListView) findViewById(R.id.listView);
        lv.setAdapter(ca);
    }

    public void verPartidos(View v){

        Intent i = new Intent(this, ListaPartidos.class);
        startActivityForResult(i,VER_PARTIDOS);
    }



}
