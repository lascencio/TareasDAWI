package com.example.david.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by David on 07/10/2017.
 */

public class MiConexion extends SQLiteOpenHelper {
    public MiConexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, "DB2", factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String ARTICULO = "create table ARTICULO (ID_ARTICULO integer primary key autoincrement, DESCRIPCION text, FAMILIA text, ID_ESTADO int, PRECIO_UNITARIO real," +
                "STOCK integer, VALIDA_STOCK text, USUARIO_REGISTRO text, FECHA_REGISTRO text,USUARIO_MODIFICA text, FECHA_MODIFICA text, RUTA_IMAGEN text);";
        db.execSQL(ARTICULO);

        String CLIENTE = "create table CLIENTE (ID_CLIENTE integer primary key autoincrement, NOMBRE text, ID_ESTADO integer, USUARIO text, ID_CARGO integer, " +
                "CLAVE text, SALDO real, INDICA_AUTORIZA integer, FECHA_REGISTRO text,FECHA_MODIFICA text);";
        db.execSQL(CLIENTE);

        String USUARIO = "create table USUARIO (ID_USUARIO integer primary key autoincrement, NOMBRE text, CLAVE_WEB text);";
        db.execSQL(USUARIO);

        String CARGO = "create table CARGO (ID_CARGO integer primary key autoincrement, DESCRIPCION text);";
        db.execSQL(CARGO);

        db.execSQL("insert into CARGO values (null, 'EMPLEADO');");
        db.execSQL("insert into CARGO values (null, 'JEFE PROYECTOS');");
        db.execSQL("insert into CARGO values (null, 'RR.HH.');");
        db.execSQL("insert into CARGO values (null, 'ADMINISTRACION');");

        db.execSQL("insert into USUARIO values (null, 'david01', 'term2');"); //AGREGARLES TIPO DE INGRESO
        db.execSQL("insert into USUARIO values (null, 'accel01', 'iron4');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
