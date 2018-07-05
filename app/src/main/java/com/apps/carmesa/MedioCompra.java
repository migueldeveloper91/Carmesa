package com.apps.carmesa;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MedioCompra extends AppCompatActivity {
TextView saldoTotal;
ListView list_milista;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medio_compra);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_action_cart);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("Factura");
        actionBar.setDisplayHomeAsUpEnabled(true);


        saldoTotal = (TextView) findViewById(R.id.tvSaldoTotal);
        ArrayList<datos> listas = new ArrayList<datos>();
        Bundle extras = getIntent().getExtras();
        list_milista = (ListView) findViewById(R.id.listNombre);
        ArrayList<String> precios = (ArrayList<String>) getIntent().getStringArrayListExtra("precios");
        ArrayList<String> titulo = (ArrayList<String>) getIntent().getStringArrayListExtra("titulos");
        String total = extras.getString("SaldoTotal");
        for (int i=0;i<precios.size();i++){
            listas.add(new datos(i,precios.get(i).toString(),titulo.get(i).toString()));
        }
        adaptador miadaptador = new adaptador(getApplicationContext(),listas);
        list_milista.setAdapter(miadaptador);

        saldoTotal.setText(total+"$");
    }
}
