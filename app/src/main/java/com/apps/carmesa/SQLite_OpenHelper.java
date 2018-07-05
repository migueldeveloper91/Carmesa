package com.apps.carmesa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite_OpenHelper extends SQLiteOpenHelper {
    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "Create Table Administrador (Usuario text primary key not null, Password text not null);";
        String query2 = "Create Table Usuario (Usuario text primary Key not null, Password text not null, " +
                        "Documento integer, Nombre text, Apellido text, Direccion text, Telfijo integer, Telmovil integer);";
        String query3 = "Create Table Fotos (Id_Foto integer, Imagen blob, Usuario1 text," +
                        " FOREIGN KEY (Usuario1) REFERENCES Usuario (Usuario));";
        String query4 = "Create Table Carro(Placa text primary key not null, UsuarioCarro text," +
                        "Modelo text, Marca text, Color text,FechaIngreso text, MotivoIngreso text, " +
                        "EstadoMotor text, EstadoSuspension text, FechaSalida text, DetalleSalida text, " +
                        "Garantia integer, ObservacionCliente text," +
                        "FOREIGN KEY (UsuarioCarro) REFERENCES Usuario (Usuario));";



        String queryAdmin="INSERT INTO Administrador (Usuario,Password) VALUES ('admin','admin123')";

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(queryAdmin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Administrador");
        db.execSQL("DROP TABLE IF EXISTS Usuario");
        db.execSQL("DROP TABLE IF EXISTS Fotos");
        db.execSQL("DROP TABLE IF EXISTS Carro");
        onCreate(db);

    }

    //Abrir base de datos
    public void abrirBD(){
        this.getWritableDatabase();
    }

    public void leerBD(){
        this.getReadableDatabase();
    }
    //cerrar base de datos
    public void cerrarBD(){
        this.close();
    }

    //Ingresar datos a la base de datos

    public void IngresarDatos(String Usuario, String Pass){
        ContentValues valores = new ContentValues();
        valores.put("Usuario",Usuario);
        valores.put("Password", Pass);
        this.getWritableDatabase().insert("Administrador",null,valores);
    }

    public void IngresarDatos(String Usuario, String Password,long Documento,
                              String Nombre, String Apellido, String Direccion,
                              long Telfijo, long Telmovil){
        ContentValues valores = new ContentValues();
        valores.put("Usuario",Usuario);
        valores.put("Password",Password);
        valores.put("Documento",Documento);
        valores.put("Nombre",Nombre);
        valores.put("Apellido",Apellido);
        valores.put("Direccion",Direccion);
        valores.put("Telfijo",Telfijo);
        valores.put("Telmovil",Telmovil);
        this.getWritableDatabase().insert("Usuario",null,valores);
    }
    public void IngresarDatos(String Placa, String UsuarioCarro,String Modelo, String Marca, String Color,
                              String FechaIngreso, String MotivoIngreso, String EstadoMotor,
                              String EstadoSuspension, String FechaSalida,String DetalleSalida, int Garantia ){
        ContentValues valores = new ContentValues();
        valores.put("Placa",Placa);
        valores.put("UsuarioCarro",UsuarioCarro);
        valores.put("Modelo",Modelo);
        valores.put("Marca",Marca);
        valores.put("Color",Color);
        valores.put("FechaIngreso",FechaIngreso);
        valores.put("MotivoIngreso",MotivoIngreso);
        valores.put("EstadoMotor",EstadoMotor);
        valores.put("EstadoSuspension",EstadoSuspension);
        valores.put("FechaSalida",FechaSalida);
        valores.put("DetalleSalida",DetalleSalida);
        valores.put("Garantia",Garantia);
        this.getWritableDatabase().insert("Carro",null,valores);
    }

    //Validar datos de ingreso
    public Cursor validador(String Usuario,String Password) throws SQLException{
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("Administrador", new String[]{"Usuario","Password"},
                "Usuario like '"+ Usuario +"' and Password like '"+ Password + "'",
                null,null,null, null
        );
        return mcursor;
    }

    //Validar datos de ingreso cliente
    public Cursor validadorCliente(String Usuario,String Password) throws SQLException{
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("Usuario", new String[]{"Usuario","Password"},"Usuario like '"+ Usuario +"' and Password like '"+ Password + "'",
                null,null,null, null
        );
        return mcursor;
    }


    /*
    public Cursor validador(int Documento) throws SQLException{
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("Usuario", new String[]{"Usuario", "Password","Documento", "Nombre", "Apellido","Direccion","Telfijo","Telmovil"},
                "Documento like '"+ Documento, null,null,null, null);
        return mcursor;
    }*/
}
