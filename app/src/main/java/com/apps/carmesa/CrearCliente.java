package com.apps.carmesa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CrearCliente extends AppCompatActivity {

    protected EditText etusr, etpass,etpass1,etnombre,etapellido,etdireccion,ettelfijo,ettelmovil,etdocumento;
    Button btncrearusr;
   SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BDcarmesa",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cliente);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_person_add);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle(" Crear Cliente");

        etusr = (EditText) findViewById(R.id.etusrc);
        etpass = (EditText) findViewById(R.id.etpassc);
        etpass1 = (EditText) findViewById(R.id.etpass1c);
        etdocumento = (EditText) findViewById(R.id.etdocumento);
        etnombre= (EditText) findViewById(R.id.etnombre);
        etapellido= (EditText) findViewById(R.id.etapellido);
        etdireccion= (EditText) findViewById(R.id.etdireccion);
        ettelfijo= (EditText) findViewById(R.id.ettelfijo);
        ettelmovil= (EditText) findViewById(R.id.ettelmovil);
        btncrearusr = (Button) findViewById(R.id.btncrearusr);


        btncrearusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etusr.getText().toString().isEmpty() || etpass.getText().toString().isEmpty() || etpass1.getText().toString().isEmpty() ||
                    etnombre.getText().toString().isEmpty() || etapellido.getText().toString().isEmpty() || etdireccion.getText().toString().isEmpty()||
                    ettelfijo.getText().toString().isEmpty() || ettelmovil.getText().toString().isEmpty() || etdocumento.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Porfavor ingrese todos los campos", Toast.LENGTH_SHORT).show();
                }else {

                    SQLiteDatabase db = helper.getReadableDatabase();
                    String[] parametros = {etusr.getText().toString()};
                    String[] campos = {"Usuario", "Password", "Documento", "Nombre", "Apellido", "Direccion", "Telfijo", "Telmovil"};

                    try {
                        Cursor cursor = db.query("Usuario", campos, "Usuario" + "=?", parametros, null, null, null);
                        cursor.moveToFirst();

                        if (cursor.getCount() != 0) {
                            Toast.makeText(getApplicationContext(), "Usuario ya existe", Toast.LENGTH_SHORT).show();
                        } else {
                            helper.getWritableDatabase();
                            helper.IngresarDatos(String.valueOf(etusr.getText()), String.valueOf(etpass.getText()),
                                    Long.parseLong(etdocumento.getText().toString()), String.valueOf(etnombre.getText()),
                                    String.valueOf(etapellido.getText()), String.valueOf(etdireccion.getText()),
                                    Long.parseLong(ettelfijo.getText().toString()), Long.parseLong(ettelmovil.getText().toString()));
                            helper.close();

                            Toast.makeText(getApplicationContext(), "Cliente Creado", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(getApplicationContext(), VehiculoCliente.class);
                            i.putExtra("Username", etusr.getText().toString());
                            startActivity(i);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    //Fin OnCreate
}
