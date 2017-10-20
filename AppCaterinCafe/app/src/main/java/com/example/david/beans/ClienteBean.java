package com.example.david.beans;

import java.io.Serializable;

/**
 * Created by David on 07/10/2017.
 */

public class ClienteBean implements Serializable {
    private int ID_CLIENTE;
    private String NOMBRE;
    private int ID_ESTADO;
    private String USUARIO;
    private String CLAVE;
    private double SALDO;
    private int INDICA_AUTORIZA;
    private String FECHA_REGISTRO;
    private String FECHA_MODIFICA;
    private int ID_CARGO;

    public int getID_CARGO() {
        return ID_CARGO;
    }

    public void setID_CARGO(int ID_CARGO) {
        this.ID_CARGO = ID_CARGO;
    }

    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    public int getID_ESTADO() {
        return ID_ESTADO;
    }

    public void setID_ESTADO(int ID_ESTADO) {
        this.ID_ESTADO = ID_ESTADO;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getCLAVE() {
        return CLAVE;
    }

    public void setCLAVE(String CLAVE) {
        this.CLAVE = CLAVE;
    }

    public double getSALDO() {
        return SALDO;
    }

    public void setSALDO(double SALDO) {
        this.SALDO = SALDO;
    }

    public int getINDICA_AUTORIZA() {
        return INDICA_AUTORIZA;
    }

    public void setINDICA_AUTORIZA(int INDICA_AUTORIZA) {
        this.INDICA_AUTORIZA = INDICA_AUTORIZA;
    }

    public String getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(String FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

    public String getFECHA_MODIFICA() {
        return FECHA_MODIFICA;
    }

    public void setFECHA_MODIFICA(String FECHA_MODIFICA) {
        this.FECHA_MODIFICA = FECHA_MODIFICA;
    }
}
