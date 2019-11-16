package com.parcial.parcialito;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parcial.parcialito.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class registrar extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinner2;
    private int carr;
    private int gen;
    private boolean existente;

    private EditText txtCorreo;
    private EditText txtContraseña;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCelular;
    private Button btnGuardar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        txtCorreo = (EditText) findViewById(R.id.txtUsuario);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtCelular = (EditText) findViewById(R.id.txtCelular);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);




        spinner = findViewById(R.id.spGenero);
        spinner2 = findViewById(R.id.spCarrera);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Genero,R.layout.spinner_item_modificar);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Carrera,R.layout.spinner_item_modificar);
        spinner2.setAdapter(adapter1);
        spinner.setAdapter(adapter);


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carr = spinner2.getSelectedItemPosition() +1;
                gen = spinner2.getSelectedItemPosition() +1;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });








        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (txtNombre.getText().toString().isEmpty() || txtCorreo.getText().toString().isEmpty() || txtContraseña.getText().toString().isEmpty() || txtNombre.getText().toString().isEmpty() || txtApellido.getText().toString().isEmpty() || txtCelular.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor ingrese los datos requeridos", Toast.LENGTH_LONG).show();
                } else {
                    AgregarRegistro(2);
                    if(!existente) {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(registrar.this);
                        final EditText hrs = new EditText(registrar.this);
                        hrs.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                        hrs.setHint("Ingresa el token ");
                        hrs.setMaxLines(1);
                        hrs.setInputType(InputType.TYPE_CLASS_NUMBER);
                        alerta.setMessage("Se ha enviado un código a tu correo, ingresa el código para identificar que eres tú: \n")
                                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                    @RequiresApi(api = Build.VERSION_CODES.O)
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //enviarToken();
                                        AgregarRegistro(1);

                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        alerta.setView(hrs);
                        AlertDialog instanciarAlerta = alerta.create();
                        instanciarAlerta.setTitle("Completa el registro");
                        instanciarAlerta.show();
                    }

                }


            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AgregarRegistro(int tipo){
        Connection conn = null;
        PreparedStatement pst = null;

        String[] cadena = txtCorreo.getText().toString().split("@");
        String usuario=cadena[0];
        String sql="";
        if (tipo == 1) {
            sql = "insert into usuario(pk_loginName, password, nombre, apellido, fk_rol, activo, fk_carrera, celular, sexo) values('" + usuario + "', '" + txtContraseña.getText().toString() + "', '" + txtNombre.getText().toString() + "', '" + txtApellido.getText().toString() + "', 2, 1, " + carr + ",'" + txtCelular.getText().toString() + "','" + spinner.getSelectedItem() + "')";

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
        else if (tipo == 2){
            Statement st = null;
            ResultSet rs = null;
            sql = "select pk_loginName from usuario where pk_loginName='" +usuario +"'";
            try {
                Conexion conexion = new Conexion();
                conn = conexion.connect();
                st = conn.createStatement();

                rs = st.executeQuery(sql);

                if(rs.first())
                {
                    String id = rs.getString("pk_loginName");
                    existente=false;
                    if (id.equals("")){
                        existente=false;
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Usuario ya registrado",Toast.LENGTH_SHORT).show();
                        existente=true;
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Usuario ya registrado",Toast.LENGTH_SHORT).show();
                    existente=true;
                }

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
                    if (st!=null) {
                        st.close();
                    }
                    if (rs!=null) {
                        rs.close();
                    }
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }


    }


}


