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

import javax.xml.transform.Source;

public class lista_clientes extends AppCompatActivity {
    ListView listviewDatosCliente;
    SQLite_OpenHelper helper;
    ArrayList <String> listaInformacion;
    ArrayList <Usuarios> listaUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_action_eye_open);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle(" Listado de Clientes");

        helper = new SQLite_OpenHelper(this,"BDcarmesa",null,1);
        listviewDatosCliente = (ListView) findViewById(R.id.listviewdatos);


        consultarListaPErsonas();


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listviewDatosCliente.setAdapter(adapter);
        listviewDatosCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String info = "Usuario: "+listaUsuario.get(i).getUsuario()+"\n";
                info+="Nombres: "+listaUsuario.get(i).getNombre()+"\n";
                info+="Apellidos: "+listaUsuario.get(i).getApellido()+"\n";
                info+="Documento: "+listaUsuario.get(i).getDocumento()+"\n";
                info+="Direccion: "+listaUsuario.get(i).getDireccion()+"\n";
                info+="Telefono Fijo: "+listaUsuario.get(i).getTelFijo()+"\n";
                info+="Telefono Movil: "+listaUsuario.get(i).getTelMovil()+"\n";


                Toast.makeText(getApplicationContext(),info,Toast.LENGTH_LONG).show();

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_mode_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditarCliente.class);
                startActivity(i);
            }
        });
    }

    private void consultarListaPErsonas() {
        SQLiteDatabase db = helper.getReadableDatabase();

        Usuarios usuario = null;
        listaUsuario = new ArrayList<Usuarios>();
        Cursor cursor = db.rawQuery("SELECT * FROM Usuario",null);

        while (cursor.moveToNext()){
            usuario= new Usuarios();
            usuario.setUsuario(cursor.getString(0));
            usuario.setPass(cursor.getString(1));
            usuario.setDocumento(cursor.getInt(2));
            usuario.setNombre(cursor.getString(3));
            usuario.setApellido(cursor.getString(4));
            usuario.setDireccion(cursor.getString(5));
            usuario.setTelFijo(cursor.getInt(6));
            usuario.setTelMovil(cursor.getInt(7));
            
            listaUsuario.add(usuario);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaInformacion= new ArrayList<String>();
        for(int i =0;i< listaUsuario.size();i++){
            listaInformacion.add(listaUsuario.get(i).getDocumento()+" - " + listaUsuario.get(i).getNombre()+" "+listaUsuario.get(i).getApellido());
        }
    }

}
