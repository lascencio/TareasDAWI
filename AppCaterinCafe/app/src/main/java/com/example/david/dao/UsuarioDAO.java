package com.example.david.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.david.beans.UsuarioBean;
import com.example.david.conexion.MiConexion;

public class UsuarioDAO {
    Context context;

    public UsuarioDAO(Context context) {
        this.context = context;
    }

    public UsuarioBean loguin(String user, String pwd) {
        UsuarioBean bean = null;
        MiConexion cn = new MiConexion(context,null,null,1);
        SQLiteDatabase sql = cn.getReadableDatabase();
        Cursor cur = sql.rawQuery("select * from USUARIO where USUARIO = ? and CLAVE_WEB = ?", new String[]{user, pwd});
        if(cur.moveToNext()){
            bean = new UsuarioBean();
            bean.setID_USUARIO(cur.getInt(0));
            bean.setNOMBRE(cur.getString(1));
            bean.setUSUARIO(cur.getString(2));
            bean.setCLAVE_WEB(cur.getString(3));
            bean.setCORREO(cur.getString(4));
            bean.setNIVEL_ACCESO(cur.getString(5));
        }
        return bean;
    }
}
