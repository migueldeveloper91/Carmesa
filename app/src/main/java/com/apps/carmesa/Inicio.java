package com.apps.carmesa;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {
Button btnCrear, btnEditar, btnEditarCarro, btnlistaClientes,btnlistaCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.ic_action_user);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle(" Crear Cliente");

        btnCrear = (Button) findViewById(R.id.btnCrear);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEditarCarro = (Button) findViewById(R.id.btnEditarCarro);
        btnlistaClientes = (Button) findViewById(R.id.btnlistaClientes);
        btnlistaCarros = (Button)findViewById(R.id.btnlistaCarros);


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CrearCliente.class);
                startActivity(i);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditarCliente.class);
                startActivity(i);
            }
        });


        btnEditarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditarCarro.class);
                startActivity(i);
            }
        });

        btnlistaClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),lista_clientes.class);
                startActivity(i);
            }
        });
        btnlistaCarros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),lista_carros.class);
                startActivity(i);
            }
        });
    }
    //Fin On create
}
