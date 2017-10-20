package com.example.david.appcaterincafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.david.beans.CargosBean;
import com.example.david.beans.ClienteBean;
import com.example.david.dao.ClienteDAO;
import com.example.david.dao.TablesDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtUsuario, edtClave, edtNombre;
    Button btnRegistrar;
    RadioGroup rdgAutoriza;
    RadioButton rbtSi, rbtNo;
    Spinner spnCargos;
    ArrayList<CargosBean> cargos;
    int rbtSelected;

    private void cargarSpinners(){
        TablesDAO dao =  new TablesDAO(this);
        cargos = dao.cargos();
        ArrayAdapter<CargosBean> adapterC = new ArrayAdapter<CargosBean>(this,android.R.layout.simple_list_item_1, cargos);
        spnCargos.setAdapter(adapterC);
    }

    private void iniciarComponentes(){
       edtUsuario = (EditText) findViewById(R.id.edtUsuario);
       edtClave = (EditText) findViewById(R.id.edtClave);
       edtNombre = (EditText) findViewById(R.id.edtNomApe);
       spnCargos = (Spinner) findViewById(R.id.spnCargos);
       btnRegistrar = (Button) findViewById(R.id.btnRegister);
       rdgAutoriza = (RadioGroup) findViewById(R.id.rdgAutoriza);
       rbtNo = (RadioButton) findViewById(R.id.rbtNo);
       rbtSi = (RadioButton) findViewById(R.id.rbtSi);
        btnRegistrar.setOnClickListener(this);
        rdgAutoriza.check(R.id.rbtNo);
    }

    void validarCampos(){
        if(edtUsuario.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese usuario",Toast.LENGTH_SHORT).show();
            return;
        }else if (edtClave.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese contraseña",Toast.LENGTH_SHORT).show();
            return;
        }else if (edtNombre.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese nombres",Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        iniciarComponentes();
        cargarSpinners();
    }

    @Override
    public void onClick(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dt = new Date();
        String fechActual = sdf.format(dt);
        if(view == btnRegistrar){
            validarCampos();
            if(rbtSi.isChecked()){
                rbtSelected = 1;
            }else if(rbtNo.isChecked()){
                rbtSelected = 2;
            }
            ClienteDAO dao = new ClienteDAO(this);
            ClienteBean bean = new ClienteBean();
            bean.setNOMBRE(edtNombre.getText().toString());
            bean.setCLAVE(edtClave.getText().toString());
            bean.setUSUARIO(edtUsuario.getText().toString());
            bean.setINDICA_AUTORIZA(rbtSelected);
            bean.setID_ESTADO(1);
            bean.setID_CARGO(((CargosBean)spnCargos.getItemAtPosition(spnCargos.getSelectedItemPosition())).getID_CARGO());
            bean.setFECHA_REGISTRO(fechActual);
            bean.setSALDO(0.0);
            int result = dao.registrarCliente(bean);
            if(result>0){
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(this, "CLIENTE REGISTRADO", Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(this, "CLIENTE NO REGISTRADO", Toast.LENGTH_SHORT).show();
        }
    }
}
