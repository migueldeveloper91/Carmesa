package com.apps.carmesa;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class EditarCarro extends AppCompatActivity {
    private TextInputEditText eteplaca, etemodelo, etemarca, etecolor,etefechaingreso,etedescripcioning, etefechaentrega, etedescripcionentrega,etemotor,etesuspension;
    private Spinner spgarantia;
    private Button btnfechaing, btnfechasal, btneditarcar, btnbuscarcar,btneliminarcar;
    String usrCarro;
    int dia,mes,ano;
    Calendar c;
    private SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BDcarmesa",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_carro);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.ic_mode_edit);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle(" Editar Datos del Carro");

        eteplaca = (TextInputEditText) findViewById(R.id.eteplaca);
        etemodelo = (TextInputEditText) findViewById(R.id.etemodelo);
        etemarca= (TextInputEditText) findViewById(R.id.etemarca);
        etecolor= (TextInputEditText) findViewById(R.id.etecolor);
        etefechaingreso= (TextInputEditText) findViewById(R.id.etefechaingreso);
        etedescripcioning = (TextInputEditText) findViewById(R.id.etedescripcioning);
        etemotor = (TextInputEditText) findViewById(R.id.etemotor);
        etesuspension = (TextInputEditText) findViewById(R.id.etesuspension);
        etefechaentrega= (TextInputEditText) findViewById(R.id.etefechasalida);
        etedescripcionentrega= (TextInputEditText) findViewById(R.id.etedetallesalida);
        spgarantia = (Spinner) findViewById(R.id.spegarantia);
        btnfechaing = (Button) findViewById(R.id.btnefechaingreso);
        btnfechasal = (Button) findViewById(R.id.btnefechasalida);
        btneditarcar = (Button) findViewById(R.id.btneditarcar);
        btnbuscarcar =(Button) findViewById(R.id.btnbuscarcar);
        btneliminarcar =(Button) findViewById(R.id.btneliminarcar);

        final ArrayAdapter<CharSequence> adaptador= ArrayAdapter.createFromResource(this, R.array.garantias ,android.R.layout.simple_spinner_item);
        spgarantia.setAdapter(adaptador);

        c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        ano = c.get(Calendar.YEAR);

        btnfechaing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditarCarro.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i, int i1, int i2) {
                        i1 = i1 + 1;
                        etefechaingreso.setText(i + "/" + i1 + "/" + i2);
                    }
                }, ano, mes, dia);
                datePickerDialog.show();
            }
        });

        btnfechasal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditarCarro.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i, int i1, int i2) {
                        i1 = i1 + 1;
                        etefechaentrega.setText(i + "/" + i1 + "/" + i2);
                    }
                }, ano, mes, dia);
                datePickerDialog.show();
            }
        });

        btnbuscarcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!eteplaca.getText().toString().equals("")) {
                    SQLiteDatabase db = helper.getReadableDatabase();
                    String[] parametrosCarro = {eteplaca.getText().toString()};
                    String[] camposCarro = {"Placa" , "UsuarioCarro", "Modelo" , "Marca","Color","FechaIngreso" ,"MotivoIngreso" ,"EstadoMotor" , "EstadoSuspension" , "FechaSalida", "DetalleSalida" , "Garantia"};


                    Cursor cursorCarro = db.query("Carro", camposCarro, "Placa" + "=?", parametrosCarro, null, null, null);
                    cursorCarro.moveToFirst();

                    if (cursorCarro.getCount() > 0) {
                        eteplaca.setText(cursorCarro.getString(0));
                        usrCarro = cursorCarro.getString(1);
                        etemodelo.setText(cursorCarro.getString(2));
                        etemarca.setText(cursorCarro.getString(3));
                        etecolor.setText(cursorCarro.getString(4));
                        etefechaingreso.setText(cursorCarro.getString(5));
                        etedescripcioning.setText(cursorCarro.getString(6));
                        etemotor.setText(cursorCarro.getString(7));
                        etesuspension.setText(cursorCarro.getString(8));
                        etefechaentrega.setText(cursorCarro.getString(9));
                        etedescripcionentrega.setText(cursorCarro.getString(10));
                    } else {
                        Toast.makeText(getApplicationContext(), "Carro no encontrado", Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                }else{
                    Toast.makeText(getApplicationContext(), "Ingresar la placa del Carro" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    btneditarcar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!eteplaca.getText().toString().equals("")) {
                SQLiteDatabase db = helper.getWritableDatabase();
                String[] editarparametros = {eteplaca.getText().toString()};

                ContentValues values = new ContentValues();
                values.put("Placa", eteplaca.getText().toString());
                values.put("Modelo", etemodelo.getText().toString());
                values.put("Marca", etemarca.getText().toString());
                values.put("Color", etecolor.getText().toString());
                values.put("FechaIngreso", etefechaingreso.getText().toString());
                values.put("MotivoIngreso", etedescripcioning.getText().toString());
                values.put("EstadoMotor", etemotor.getText().toString());
                values.put("EstadoSuspension", etesuspension.getText().toString());
                values.put("FechaSalida", etefechaentrega.getText().toString());
                values.put("DetalleSalida", etedescripcionentrega.getText().toString());
                values.put("Garantia", spgarantia.getSelectedItem().toString());

                db.update("Carro", values, "Placa" + "=?", editarparametros);
                Toast.makeText(getApplicationContext(), "Datos del Carro Actualizado", Toast.LENGTH_LONG).show();

                eteplaca.setText("");
                etemodelo.setText("");
                etemarca.setText("");
                etecolor.setText("");
                etefechaingreso.setText("");
                etedescripcioning.setText("");
                etemotor.setText("");
                etesuspension.setText("");
                etefechaentrega.setText("");
                etedescripcionentrega.setText("");
            }else{
                Toast.makeText(getApplicationContext(), "Ingresar la placa del Carro" , Toast.LENGTH_SHORT).show();
            }


        }
    });

    btneliminarcar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDatabase db = helper.getWritableDatabase();
            String[] eliminarparametros = {eteplaca.getText().toString()};
            String[] eliminarparametross = {usrCarro};
            db.delete("Carro", "Placa" + "=?", eliminarparametros);
            db.delete("Usuario", "Usuario" + "=?", eliminarparametross);
            Toast.makeText(getApplicationContext(), "Carro Eliminado", Toast.LENGTH_LONG).show();

            eteplaca.setText("");
            etemodelo.setText("");
            etemarca.setText("");
            etecolor.setText("");
            etefechaingreso.setText("");
            etedescripcioning.setText("");
            etemotor.setText("");
            etesuspension.setText("");
            etefechaentrega.setText("");
            etedescripcionentrega.setText("");

        }
    });


    }
}
