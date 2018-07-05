package com.apps.carmesa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;


public class Login extends AppCompatActivity {

    private EditText etUsr, etPass;

    LottieAnimationView mianimacion;
    private Button btnIngresar;
    private RadioButton rbUsuario, rbAdmin;
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BDcarmesa",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsr = (EditText) findViewById(R.id.etUsr);
        etPass = (EditText) findViewById(R.id.etPass);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        rbUsuario = (RadioButton) findViewById(R.id.rbUsr);
        rbAdmin =(RadioButton) findViewById(R.id.rbAdmin);
        rbUsuario.setText("Usuario");
        rbAdmin.setText("Admin");
        mianimacion = (LottieAnimationView) findViewById(R.id.animacion);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.abrirBD();

                mianimacion.loop(false);
                mianimacion.setSpeed(1);
                mianimacion.playAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        validar();
                        //Verificar si esta seleccionado admin
                        if (rbAdmin.isChecked()) {
                            try {
                                Cursor cursor = helper.validador(etUsr.getText().toString(), etPass.getText().toString());
                                if (cursor.getCount() > 0 && rbAdmin.getText() == "Admin") {
                                    Intent i = new Intent(getApplicationContext(), Inicio.class);
                                    startActivity(i);
                                    etUsr.setText("");
                                    etPass.setText("");
                                    etUsr.findFocus();
                                } else {

                                    Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña erronea", Toast.LENGTH_SHORT).show();
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }else if(rbUsuario.isChecked()) {
                            try {
                                Cursor cursor = helper.validadorCliente(etUsr.getText().toString(), etPass.getText().toString());
                                if (cursor.getCount() >0 && rbUsuario.getText() == "Usuario"){

                                    Intent i = new Intent(getApplicationContext(), InicioUsr.class);
                                    i.putExtra("Username", etUsr.getText().toString());
                                    startActivity(i);

                                   /* etUsr.setText("");
                                    etPass.setText("");
                                    etUsr.findFocus();*/

                                }else {
                                    Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña erronea", Toast.LENGTH_SHORT).show();

                                }
                            }catch (SQLException e){
                                e.printStackTrace();
                            }
                        }
                    }
                },1750);
            }
        });
    }
    private void validar(){
        etUsr.setError(null);
        etPass.setError(null);

        String usuario = etUsr.getText().toString();
        String pass = etPass.getText().toString();

        if (TextUtils.isEmpty(usuario)){
            etUsr.setError(getString(R.string.error_validar));
            etUsr.requestFocus();
        }
        if (TextUtils.isEmpty(pass)){
            etPass.setError(getString(R.string.error_validar));
            etPass.requestFocus();
        }

    }

}
