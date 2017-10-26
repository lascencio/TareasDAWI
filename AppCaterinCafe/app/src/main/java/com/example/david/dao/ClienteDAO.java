package com.example.david.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.david.beans.ClienteBean;
import com.example.david.conexion.MiConexion;
import com.example.david.utils.Utilitarios;

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
            sql.execSQL("insert into CLIENTE values (null,?,?,?,?,?,?,?,?,?,null);", new Object[]{bean.getNOMBRE(), "ACTIVO", bean.getCORREO(), bean.getUSUARIO(), bean.getID_CARGO(),
                    bean.getCLAVE(), 0.0, bean.getINDICA_AUTORIZA(), Utilitarios.fechaActual()});
            sql.execSQL("insert into USUARIO values (null, ?,?,?,?,'CONS');", new Object[]{bean.getNOMBRE(),bean.getUSUARIO(), bean.getCLAVE(), bean.getCORREO()});
            result = 1;
        } catch (Exception e) {
            result = -1;
            e.getStackTrace();
        }

        return result;
    }

    public int actualizarCliente(ClienteBean bean) {
        int result = 0;
        MiConexion cn = new MiConexion(context, null, null, 1);
        SQLiteDatabase sql = cn.getReadableDatabase();
        try {
            sql.execSQL("update CLIENTE set NOMBRE = ?,CORREO = ?,ID_CARGO=?, SALDO=?, INDICA_AUTORIZA = ?, FECHA_MODIFICA = ? where ID_CLIENTE = ?;",
                    new Object[]{bean.getNOMBRE(), bean.getCORREO(),bean.getID_CARGO(), bean.getSALDO(), bean.getINDICA_AUTORIZA(), Utilitarios.fechaActual(),bean.getID_CLIENTE()});
            result = 1;
        } catch (Exception e) {
            result = -1;
            e.getStackTrace();
        }

        return result;
    }

    public ClienteBean consultarCliente(String user, String pwd) {
        ClienteBean cliente = null;
        MiConexion cn = new MiConexion(context, null, null, 1);
        SQLiteDatabase sql = cn.getReadableDatabase();
        Cursor cur = sql.rawQuery("select * from CLIENTE where USUARIO = ? and CLAVE = ?", new String[]{user,pwd});
        if(cur.moveToNext()){
            cliente = new ClienteBean();
            cliente.setNOMBRE(cur.getString(1));
            cliente.setID_ESTADO(cur.getString(2));
            cliente.setCORREO(cur.getString(3));
            cliente.setUSUARIO(cur.getString(4));
            cliente.setID_CARGO(cur.getInt(5));
            cliente.setCLAVE(cur.getString(6));
            cliente.setSALDO(cur.getDouble(7));
            cliente.setINDICA_AUTORIZA(cur.getInt(8));
            cliente.setFECHA_REGISTRO(cur.getString(9));
            cliente.setFECHA_MODIFICA(cur.getString(10));
        }
        return cliente;
    }


}
