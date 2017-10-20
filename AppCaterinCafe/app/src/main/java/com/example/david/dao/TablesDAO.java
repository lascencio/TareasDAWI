package com.example.david.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.david.beans.CargosBean;
import com.example.david.conexion.MiConexion;

import java.util.ArrayList;

/**
 * Created by David on 09/10/2017.
 */

public class TablesDAO {
    Context context;

    public TablesDAO(Context context) {
        this.context = context;
    }

    public ArrayList<CargosBean> cargos(){
        ArrayList<CargosBean> lista = new ArrayList<CargosBean>();
        MiConexion cn = new MiConexion(context, null, null,1);
        SQLiteDatabase sql = cn.getReadableDatabase();
        Cursor cur = sql.rawQuery("select * from CARGO",null);
        CargosBean bean = null;
        while(cur.moveToNext()){
            bean = new CargosBean();
            bean.setID_CARGO(cur.getInt(0));
            bean.setDESCRIPCION(cur.getString(1));
            lista.add(bean);
        }
        return lista;
    }
}
