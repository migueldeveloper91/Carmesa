package com.apps.carmesa;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;


public class VehiculoCliente extends AppCompatActivity  {
EditText etplaca, etmodelo, etmarca, etcolor,etfechaingreso,etdescripcioning, etfechaentrega, etdescripcionentrega,etmotor,etsuspension;
Spinner spgarantia;
Button btnfechaing, btnfechasal, btncrearcar;
int dia,mes,ano;
Calendar c;

ImageView foto1, foto2, foto3;
SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BDcarmesa",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculocliente);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_directions_car);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle(" Vehiculo del Cliente");

        Bundle extras = getIntent().getExtras();
        final String d1 = extras.getString("Username");

        etplaca = (EditText) findViewById(R.id.etplaca);
        etmodelo = (EditText) findViewById(R.id.etmodelo);
        etmarca= (EditText) findViewById(R.id.etmarca);
        etcolor= (EditText) findViewById(R.id.etcolor);
        etfechaingreso= (EditText) findViewById(R.id.etfechaingreso);
        etdescripcioning = (EditText) findViewById(R.id.etdescripcioning);
        etmotor = (EditText) findViewById(R.id.etmotor);
        etsuspension = (EditText) findViewById(R.id.etsuspension);
        etfechaentrega= (EditText) findViewById(R.id.etfechasalida);
        etdescripcionentrega= (EditText) findViewById(R.id.etdescripcionsal);
        spgarantia = (Spinner) findViewById(R.id.spgarantia);
        btnfechaing = (Button) findViewById(R.id.btnfechaingreso);
        btnfechasal = (Button) findViewById(R.id.btnfechasalida);
        btncrearcar = (Button) findViewById(R.id.btncrearcar);


        final ArrayAdapter<CharSequence>  adaptador= ArrayAdapter.createFromResource(this, R.array.garantias ,android.R.layout.simple_spinner_item);

        spgarantia.setAdapter(adaptador);

        c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        ano = c.get(Calendar.YEAR);

        btnfechaing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(VehiculoCliente.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i, int i1, int i2) {
                        i1 = i1 + 1;
                        etfechaingreso.setText(i + "/" + i1 + "/" + i2);
                    }
                }, ano, mes, dia);
                datePickerDialog.show();
            }
        });

        btnfechasal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(VehiculoCliente.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int i, int i1, int i2) {
                        i1 = i1 + 1;
                        etfechaentrega.setText(i + "/" + i1 + "/" + i2);
                    }
                }, ano, mes, dia);
                datePickerDialog.show();
            }
        });


        btncrearcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etplaca.getText().toString().isEmpty() || etmodelo.getText().toString().isEmpty() || etmarca.getText().toString().isEmpty() ||
                        etcolor.getText().toString().isEmpty() || etfechaingreso.getText().toString().isEmpty() || etdescripcioning.getText().toString().isEmpty()||
                        etmotor.getText().toString().isEmpty() || etsuspension.getText().toString().isEmpty() || etfechaentrega.getText().toString().isEmpty()
                        || etdescripcionentrega.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Porfavor ingrese todos los campos", Toast.LENGTH_SHORT).show();
                }else {

                    helper.abrirBD();
                    helper.IngresarDatos(String.valueOf(etplaca.getText().toString()), String.valueOf(d1.toString()), String.valueOf(etmodelo.getText().toString()), String.valueOf(etmarca.getText().toString()),
                            String.valueOf(etcolor.getText().toString()), String.valueOf(etfechaingreso.getText().toString()), String.valueOf(etdescripcioning.getText().toString()),
                            String.valueOf(etmotor.getText().toString()), String.valueOf(etsuspension.getText().toString()), String.valueOf(etfechaentrega.getText().toString()),
                            String.valueOf(etdescripcionentrega.getText().toString().toString()), Integer.parseInt(spgarantia.getSelectedItem().toString()));

                    helper.cerrarBD();

                    Toast.makeText(getApplicationContext(), "Vehiculo Ingresado", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Inicio.class);
                    startActivity(i);
                }

            }
        });

        foto1=(ImageView) findViewById(R.id.foto1);
        foto2=(ImageView) findViewById(R.id.foto2);
        foto3=(ImageView) findViewById(R.id.foto3);





        foto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent,"Seleccione la Apliccion"),10);
            }
        });

        foto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent,"Seleccione la Apliccion"),20);
            }
        });

        foto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent,"Seleccione la Apliccion"),30);
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 10){
            Uri path1 = data.getData();
            Picasso.get().load(path1).resize(720,1280).centerCrop().into(foto1);
        }else if (resultCode == RESULT_OK && requestCode == 20){
            Uri path2 = data.getData();
            Picasso.get().load(path2).resize(720,1280).centerCrop().into(foto2);
        }else if (resultCode == RESULT_OK && requestCode == 30){
            Uri path3 = data.getData();
            Picasso.get().load(path3).resize(720,1280).centerCrop().into(foto3);
        }
    }
}
