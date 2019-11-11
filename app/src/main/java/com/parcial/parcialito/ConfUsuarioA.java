package com.parcial.parcialito;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConfUsuarioA extends AppCompatActivity {


    private TextView txtVista;
    private EditText txtCelular;
    private EditText txtLogroH;
    private Button btnGuardar;


    private Connection conn;
    private Statement st = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_usuario2);

        txtVista = (TextView) findViewById(R.id.txtVista);
        txtCelular = (EditText) findViewById(R.id.txtCelular);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        txtLogroH = (EditText) findViewById(R.id.txtLogroH);

        String loginName = cargarUsuario();
        txtVista.setText(loginName);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                AgregarRegistro();
                txtCelular.setText("");
                txtLogroH.setText("");

            }


        });





    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AgregarRegistro(){
        Connection conn = null;
        PreparedStatement pst = null;

        String loginName= cargarUsuario();
        String sql ="";


        if (txtCelular.getText().toString().isEmpty() && txtLogroH.getText().toString().isEmpty() ) {
            Toast.makeText(getApplicationContext(), "Llene algún campo para modificar", Toast.LENGTH_LONG).show();
        }

        else if (txtCelular.getText().toString().isEmpty()) {
            sql = "update usuario set logroH= '"+ txtLogroH.getText().toString() +"'  where pk_loginName ='" +loginName+ "'";
        }
        else if (txtLogroH.getText().toString().isEmpty()){
            sql = "update usuario set Celular= '"+ txtCelular.getText().toString() + "' where pk_loginName ='"+loginName+"'";

        }
        else {
            sql = "update usuario set logroH= '"+ txtLogroH.getText().toString() + "',  Celular= '" + txtCelular.getText().toString() + "' where pk_loginName ='" +loginName +"'";

        }
        try {
            Conexion conexion = new Conexion();
            conn = conexion.connect();

            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            Toast.makeText(getApplicationContext(),"REGISTRO EXITOSO",Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally
        {
            try
            {
                if (conn!=null) {
                    conn.close();
                }
                if (pst!=null) {
                    pst.close();
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }


    private String cargarUsuario() {
        SharedPreferences preferences = getSharedPreferences("username", MODE_PRIVATE);
        String loginName=preferences.getString("user","");
        return loginName;
    }


}



