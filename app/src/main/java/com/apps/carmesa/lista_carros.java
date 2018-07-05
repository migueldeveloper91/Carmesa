package com.apps.carmesa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class lista_carros extends AppCompatActivity {
    ListView listviewDatosCarro;
    SQLite_OpenHelper helper;
    ArrayList<String> listaInformacion;
    ArrayList <Carros> listacarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_carros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_action_eye_open);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle(" Listado de Vehiculos");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_mode_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditarCarro.class);
                startActivity(i);
            }
        });

        helper = new SQLite_OpenHelper(this,"BDcarmesa",null,1);
        listviewDatosCarro = (ListView) findViewById(R.id.listviewdatosCarro);

        consultarListaPErsonas();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listviewDatosCarro.setAdapter(adapter);
        listviewDatosCarro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String info = "Placa: "+listacarro.get(i).getPlaca()+"\n";
                info+="Modelo: "+listacarro.get(i).getModelo()+"\n";
                info+="Marca: "+listacarro.get(i).getMarca()+"\n";
                info+="Color: "+listacarro.get(i).getColor()+"\n";

                Toast.makeText(getApplicationContext(),info,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void consultarListaPErsonas() {

        SQLiteDatabase db = helper.getReadableDatabase();

        Carros carro = null;
        listacarro = new ArrayList<Carros>();
        Cursor cursor = db.rawQuery("SELECT * FROM Carro ",null);

        while (cursor.moveToNext()){
            carro= new Carros();
            carro.setPlaca(cursor.getString(0));
            carro.setUsuarioCarro(cursor.getString(1));
            carro.setModelo(cursor.getString(2));
            carro.setMarca(cursor.getString(3));
            carro.setColor(cursor.getString(4));
            listacarro.add(carro);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion= new ArrayList<String>();
        for(int i =0;i< listacarro.size();i++){
            listaInformacion.add(listacarro.get(i).getPlaca()+" - " + listacarro.get(i).getMarca());
        }
    }
}