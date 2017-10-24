package com.example.david.appcaterincafe;

import android.app.ProgressDialog;
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
import com.example.david.conexion.ConexionWS;
import com.example.david.dao.ClienteDAO;
import com.example.david.dao.TablesDAO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtUsuario, edtClave, edtNombre,edtApellido,edtCorreo;
    Button btnRegistrar;
    RadioGroup rdgAutoriza;
    RadioButton rbtSi, rbtNo;
    Spinner spnCargos;
    ArrayList<CargosBean> cargos;
    int rbtSelected;
    ProgressDialog prgDialog;
    ConexionWS cnws = new ConexionWS();

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
       edtApellido = (EditText) findViewById(R.id.edtApellido);
       edtCorreo = (EditText) findViewById(R.id.edtCorreo);
       spnCargos = (Spinner) findViewById(R.id.spnCargos);
       btnRegistrar = (Button) findViewById(R.id.btnRegister);
       rdgAutoriza = (RadioGroup) findViewById(R.id.rdgAutoriza);
       rbtNo = (RadioButton) findViewById(R.id.rbtNo);
       rbtSi = (RadioButton) findViewById(R.id.rbtSi);
       btnRegistrar.setOnClickListener(this);
       rdgAutoriza.check(R.id.rbtNo);
       prgDialog = new ProgressDialog(this);
       prgDialog.setMessage("Registrando Usuario");
       prgDialog.setCancelable(false);
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
        /*
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
        }*/
        grabarCliente();
    }

    /**
     * Metodo para leer las cajas y enviarlo al WS
     */
    void grabarCliente(){
        RequestParams params = new RequestParams();
        params.put("nombre",edtNombre.getText().toString());
        params.put("apellido",edtApellido.getText().toString());
        params.put("correo", edtCorreo.getText().toString());
        params.put("usuario",edtUsuario.getText().toString());
        String claveMD5 = edtClave.getText().toString();
        try {
            params.put("clave", getMD5(claveMD5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("autoriza",validarCheckBoxAutoriza());
        params.put("rolid",0);
        crearUsuarioWS(params);
    }

    String validarCheckBoxAutoriza(){
        String estado = "";
        if(rbtSi.isChecked()){
            rbtSelected = 1;
            estado="SI";
        }else if(rbtNo.isChecked()){
            rbtSelected = 2;
            estado="NO";
        }
        return estado;
    }

    /**
     * Metodo para guardar Cliente con WS
     * @param params
     */
    private void crearUsuarioWS(RequestParams params){
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(cnws.urlservice+"registroCliente", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                prgDialog.dismiss();
                if(statusCode==201){
                    Toast.makeText(getApplicationContext(), "Registro con Exito!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "No se pudo guardar el registro!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                prgDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Ocurrio un error con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public String getMD5(String cadena) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(cadena.getBytes());

        int size = b.length;
        StringBuilder h = new StringBuilder(size);
        for (int i = 0; i < size; i++) {

            int u = b[i] & 255;

            if (u < 16)
            {
                h.append("0").append(Integer.toHexString(u));
            }
            else
            {
                h.append(Integer.toHexString(u));
            }
        }
        return h.toString();
    }
}
