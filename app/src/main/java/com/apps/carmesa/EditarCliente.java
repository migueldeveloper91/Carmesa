package com.apps.carmesa;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarCliente extends AppCompatActivity {
    private EditText etusr, etpass,etpass1,etnombre,etapellido,etdireccion,ettelfijo,ettelmovil,etdocumento;
    private Button btnbuscar,btneditar,btnelminar;

    private SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BDcarmesa",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.ic_mode_edit);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle(" Editar Cliente");

        etusr = (EditText) findViewById(R.id.eteusrc);
        etpass = (EditText) findViewById(R.id.etepassc);
        etdocumento = (EditText) findViewById(R.id.etedocumento);
        etnombre= (EditText) findViewById(R.id.etenombre);
        etapellido= (EditText) findViewById(R.id.eteapellido);
        etdireccion= (EditText) findViewById(R.id.etedireccion);
        ettelfijo= (EditText) findViewById(R.id.etetelfijo);
        ettelmovil= (EditText) findViewById(R.id.etetelmovil);
        btnbuscar = (Button) findViewById(R.id.btneBuscar);
        btneditar = (Button) findViewById(R.id.btneeditarusr);
        btnelminar = (Button) findViewById(R.id.btneeliminarusr);

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!etdocumento.getText().toString().equals("")) {
                    SQLiteDatabase db = helper.getReadableDatabase();
                    String[] parametros = {etdocumento.getText().toString()};
                    String[] campos = {"Usuario", "Password", "Documento", "Nombre", "Apellido", "Direccion", "Telfijo", "Telmovil"};


                    Cursor cursor = db.query("Usuario", campos, "Documento" + "=?", parametros, null, null, null);
                    cursor.moveToFirst();
                    if (cursor.getCount() > 0) {
                        etusr.setText(cursor.getString(0));
                        etpass.setText(cursor.getString(1));
                        etdocumento.setText(cursor.getString(2));
                        etnombre.setText(cursor.getString(3));
                        etapellido.setText(cursor.getString(4));
                        etdireccion.setText(cursor.getString(5));
                        ettelfijo.setText(cursor.getString(6));
                        ettelmovil.setText(cursor.getString(7));


                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();

                    }
                    db.close();
                }else{
                    Toast.makeText(getApplicationContext(), "Ingresar el numero de documento del Cliente", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etdocumento.getText().toString().equals("")) {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    String[] editarparametros = {etdocumento.getText().toString()};


                    ContentValues valores = new ContentValues();
                    valores.put("Password", etpass.getText().toString());
                    valores.put("Documento", etdocumento.getText().toString());
                    valores.put("Nombre", etnombre.getText().toString());
                    valores.put("Apellido", etapellido.getText().toString());
                    valores.put("Direccion", etdireccion.getText().toString());
                    valores.put("Telfijo", ettelfijo.getText().toString());
                    valores.put("Telmovil", ettelmovil.getText().toString());

                    db.update("Usuario", valores, "Documento" + "=?", editarparametros);
                    Toast.makeText(getApplicationContext(), "Cliente Actualizado", Toast.LENGTH_LONG).show();

                    etdocumento.setText("");
                    etusr.setText("");
                    etpass.setText("");
                    etnombre.setText("");
                    etapellido.setText("");
                    etdireccion.setText("");
                    ettelfijo.setText("");
                    ettelmovil.setText("");
                    db.close();

                }else{
                    Toast.makeText(getApplicationContext(), "Ingresar el numero de documento del Cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnelminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etdocumento.getText().toString().equals("")) {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    String[] eliminarparametros = {etdocumento.getText().toString()};
                    db.delete("Usuario", "Documento" + "=?", eliminarparametros);
                    Toast.makeText(getApplicationContext(), "Cliente Eliminado", Toast.LENGTH_LONG).show();


                    etdocumento.setText("");
                    etusr.setText("");
                    etpass.setText("");
                    etnombre.setText("");
                    etapellido.setText("");
                    etdireccion.setText("");
                    ettelfijo.setText("");
                    ettelmovil.setText("");
                    db.close();
                }else{
                    Toast.makeText(getApplicationContext(), "Ingresar el numero de documento del Cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
