package com.example.david.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MiConexion extends SQLiteOpenHelper {
    public MiConexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, "DB9", factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CARGO = "create table CARGO (ID_CARGO integer primary key autoincrement, DESCRIPCION text);";
        db.execSQL(CARGO);

        String NIVEL_ACCESO = "create table NIVEL_ACCESO (ID_NIVEL_ACCESO integer primary key autoincrement, DESCRIPCION text);";
        db.execSQL(NIVEL_ACCESO);

        db.execSQL("insert into NIVEL_ACCESO values (null, 'TODO');");
        db.execSQL("insert into NIVEL_ACCESO values (null, 'MANTENIMIENTOS');");
        db.execSQL("insert into NIVEL_ACCESO values (null, 'CONSULTA');");

        db.execSQL("insert into CARGO values (null, 'EMPLEADO');");
        db.execSQL("insert into CARGO values (null, 'JEFE PROYECTOS');");
        db.execSQL("insert into CARGO values (null, 'RR.HH.');");
        db.execSQL("insert into CARGO values (null, 'ADMINISTRACION');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
