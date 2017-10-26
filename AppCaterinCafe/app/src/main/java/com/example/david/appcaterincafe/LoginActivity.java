package com.example.david.appcaterincafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.beans.ClienteBean;
import com.example.david.beans.UsuarioBean;
import com.example.david.dao.ClienteDAO;
import com.example.david.dao.UsuarioDAO;
import com.example.david.utils.Session;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUser, edtPassword;
    TextView txtRegister;
    Button btnLogin;
    CheckBox chkIngreso;
    private Session session;

    private void iniciarComponentes() {
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        txtRegister = (TextView) findViewById(R.id.txtRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        chkIngreso = (CheckBox)findViewById(R.id.chkIngreso);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iniciarComponentes();
    }

    @Override
    public void onBackPressed() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("ALERTA!");
            builder.setMessage("¿Desea salir de la APP?").setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();;
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            if (validarCampos()) {
                if(chkIngreso.isChecked()){

                }else{

                }
            }
        }
        if (view == txtRegister) {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    }

    public boolean validarCampos() {
        boolean valid = true;
        if (edtUser.getText().toString().equals("")) {
            Toast.makeText(this, "Ingrese usuario", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (edtPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Ingrese contraseña", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }

}