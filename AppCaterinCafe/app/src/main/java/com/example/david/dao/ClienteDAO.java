package com.example.david.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.david.beans.ClienteBean;
import com.example.david.conexion.MiConexion;

/**
 * Created by David on 09/10/2017.
 */

public class ClienteDAO {
    Context context;

    public ClienteDAO(Context context) {
        this.context = context;
    }

    public int registrarCliente(ClienteBean bean) {
        int result = 0;
        MiConexion cn = new MiConexion(context, null, null, 1);
        SQLiteDatabase sql = cn.getReadableDatabase();
        try {
            sql.execSQL("insert into CLIENTE values (null,?,?,?,?,?,?,?,?,null);", new Object[]{bean.getNOMBRE(), bean.getID_ESTADO(), bean.getUSUARIO(), bean.getID_CARGO(),
                    bean.getCLAVE(), bean.getSALDO(), bean.getINDICA_AUTORIZA(), bean.getFECHA_REGISTRO()});
            sql.execSQL("insert into USUARIO values (null, ?,?);", new Object[]{ bean.getUSUARIO(), bean.getCLAVE()});
            result = 1;
        } catch (Exception e) {
            result = -1;
        }

        return result;
    }


}
