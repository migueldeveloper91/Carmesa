package com.apps.carmesa;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;

import java.util.ArrayList;
import java.util.List;


public class InicioUsr extends AppCompatActivity {

    private EditText edNombre, edDescripcion;
    private List <Producto> listaProductos;
    private Context ctx;
    ArrayAdapter<Producto> adapter;

    SQLite_OpenHelper helper;
    TextInputEditText etplaca, etmodelo, etmarca, etcolor,etfechaingreso,etdescripcion,
                      etmotor,etsuspension, etfechasalida,etdescripcionsal,etgarantia,etlusr ,etlpass,
                      etldocumento,etlnombres,etlapellidos,etldir,etlfijo,etlmovil, etobservacionclienteAdd,etobscliente;
    int valorTotal,valorProducto;

    String cadenaValor;
    Button btnAddObs,btnComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_usr);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.ic_action_user);
        actionBar.setDisplayUseLogoEnabled(true);


        helper = new SQLite_OpenHelper(this,"BDcarmesa",null,1);
        ctx = this;
        etplaca = (TextInputEditText) findViewById(R.id.etlplaca);
        etmodelo = (TextInputEditText) findViewById(R.id.etlmodelo);
        etmarca = (TextInputEditText) findViewById(R.id.etlmarca);
        etcolor = (TextInputEditText) findViewById(R.id.etlcolor);
        etfechaingreso = (TextInputEditText) findViewById(R.id.etlfechaingreso);
        etdescripcion = (TextInputEditText) findViewById(R.id.etldescripcion);
        etmotor = (TextInputEditText) findViewById(R.id.etlmotor);
        etsuspension = (TextInputEditText) findViewById(R.id.etlsuspension);
        etfechasalida = (TextInputEditText) findViewById(R.id.etlfechasalida);
        etdescripcionsal= (TextInputEditText) findViewById(R.id.etldescripcionsal);
        etgarantia= (TextInputEditText) findViewById(R.id.etlgarantia);
        etlusr = (TextInputEditText) findViewById(R.id.etlusername);
        etlpass = (TextInputEditText) findViewById(R.id.etlpass);
        etldocumento = (TextInputEditText) findViewById(R.id.etldocumento);
        etlnombres = (TextInputEditText) findViewById(R.id.etlnombres);
        etlapellidos = (TextInputEditText) findViewById(R.id.etlapellidos);
        etldir = (TextInputEditText) findViewById(R.id.etldir);
        etlfijo = (TextInputEditText) findViewById(R.id.etlfijo);
        etlmovil = (TextInputEditText) findViewById(R.id.etlmovil);
        etobservacionclienteAdd = (TextInputEditText) findViewById(R.id.etobservacionesAdd);
        btnAddObs = (Button) findViewById(R.id.btnAddObs);
        etobscliente = (TextInputEditText) findViewById(R.id.etobsCliente) ;
        btnComprar = (Button) findViewById(R.id.btnComprar);

        Bundle extras =getIntent().getExtras();
        final String d1 = extras.getString("Username");

        SQLiteDatabase db = helper.getReadableDatabase();

        //Datos del carro

        String[] parametros = {d1};
        String[] camposcar = {"Placa", "Modelo", "Marca", "Color", "FechaIngreso", "MotivoIngreso", "EstadoMotor","EstadoSuspension","FechaSalida","DetalleSalida","Garantia","ObservacionCliente"};

        Cursor cursor = db.query("Carro", camposcar, "UsuarioCarro" + "=?", parametros, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            etplaca.setText(cursor.getString(0));
            etmodelo.setText(cursor.getString(1));
            etmarca.setText(cursor.getString(2));
            etcolor.setText(cursor.getString(3));
            etfechaingreso.setText(cursor.getString(4));
            etdescripcion.setText(cursor.getString(5));
            etmotor.setText(cursor.getString(6));
            etsuspension.setText(cursor.getString(7));
            etfechasalida.setText(cursor.getString(8));
            etdescripcionsal.setText(cursor.getString(9));
            etgarantia.setText(cursor.getString(10) + " Meses.");
            etobscliente.setText(cursor.getString(11));
        }

        //Datos Personales del Usuario

        String[] camposusr = {"Usuario", "Password", "Documento", "Nombre", "Apellido", "Direccion", "Telfijo", "Telmovil"};

        Cursor cursorCar = db.query("Usuario", camposusr, "Usuario" + "=?", parametros, null, null, null);
        cursorCar.moveToFirst();
        if (cursorCar.getCount() > 0) {
            etlusr.setText(cursorCar.getString(0));
            etlpass.setText(cursorCar.getString(1));
            etldocumento.setText(cursorCar.getString(2));
            etlnombres.setText(cursorCar.getString(3));
            etlapellidos.setText(cursorCar.getString(4));
            etldir.setText(cursorCar.getString(5));
            etlfijo.setText(cursorCar.getString(6));
            etlmovil.setText(cursorCar.getString(7));
        }
        actionBar.setTitle(" Bienvenido "+etlnombres.getText().toString());
        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Carro", res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Datos Personales",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Productos",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);

        tabs.setCurrentTab(2);

        btnAddObs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etobservacionclienteAdd.getText().toString().equals("")) {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    String[] editarparametros = {d1};
                    String[] camposcar = {"ObservacionCliente"};
                    String obs = etobscliente.getText().toString();

                    ContentValues values = new ContentValues();
                    values.put("ObservacionCliente", etobservacionclienteAdd.getText().toString());
                    db.update("Carro", values, "UsuarioCarro" + "=?", editarparametros);

                    Cursor cursor = db.query("Carro", camposcar, "UsuarioCarro" + "=?", editarparametros, null, null, null);
                    cursor.moveToFirst();

                    if (cursor.getCount() > 0) {
                        etobscliente.setText("- "+cursor.getString(0)+" \n"+obs);
                    }
                    Toast.makeText(getApplicationContext(),"Observacion Enviada y/o Actualizada",Toast.LENGTH_SHORT).show();
                    etobservacionclienteAdd.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"Porfavor ingrese primero su observación ",Toast.LENGTH_SHORT).show();
                }
            }
        });

        addView();

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            int contador=0;
                for (int i=0; i < listaProductos.size();i++){
                    if (listaProductos.get(i).isSelected()){
                        contador = 1 + contador;
                    }
                }
                if (contador >= 1 ){
                    ejecutarArreglo();
                }else{
                    Toast.makeText(getApplicationContext(),"Por favor seleccione un producto para continuar",Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
//Metodo para el arreglo
    private void ejecutarArreglo() {
        valorTotal = 0;

        final ArrayList<String> lista = new ArrayList<>();
        final ArrayList<String> titulos = new ArrayList<>();


        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).isSelected()) {

                cadenaValor = listaProductos.get(i).getTitulo();
                valorProducto = Integer.parseInt(listaProductos.get(i).getValor());
                valorTotal = valorProducto + valorTotal;
                lista.add(listaProductos.get(i).getValor());
                titulos.add(listaProductos.get(i).getTitulo());
            }
            listaProductos.get(i).setSelected(false);
        }
        String cadena = String.valueOf(valorTotal);

        Toast.makeText(getApplicationContext(), String.valueOf(cadena), Toast.LENGTH_LONG).show();
        Intent compra = new Intent(getApplicationContext(), MedioCompra.class);
        compra.putExtra("SaldoTotal", cadena);
        compra.putExtra("titulos", titulos);
        compra.putExtra("precios", lista);
        startActivity(compra);

        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.info){

            Intent about = new Intent(getApplicationContext(), about.class);
            startActivity(about);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addView(){

        ListView listaView = (ListView) findViewById(R.id.listview1);
        adapter = new ListaAdapter(InicioUsr.this,getListProductos());
        listaView.setAdapter(adapter);
    }

    private List<Producto> getListProductos(){
        listaProductos = new ArrayList<Producto>();
        String []titulos;
        String []descripciones;
        String [] valores;

        titulos= ctx.getResources().getStringArray(R.array.Titulos);
        descripciones = ctx.getResources().getStringArray(R.array.Descripcion);
        valores = ctx.getResources().getStringArray(R.array.Valor);
        int[] imagenes = new int[]{
                 R.drawable.img1
                ,R.drawable.img2
                ,R.drawable.img3
                ,R.drawable.img4
                ,R.drawable.img5
                ,R.drawable.img6
                ,R.drawable.img7
                ,R.drawable.img8
                ,R.drawable.img9
                ,R.drawable.img10
                ,R.drawable.img11
                ,R.drawable.img12
                ,R.drawable.img13
                ,R.drawable.img14
                ,R.drawable.img15

        };

        for (int i= 0;i<imagenes.length;i++){
            listaProductos.add(get(titulos[i],descripciones[i],imagenes[i],valores[i]));

        }
        return listaProductos;
    }

    private Producto get(String titulo, String descripcion, int Imagen,String valor){
        return new Producto(titulo,descripcion, Imagen, valor);
    }

}
