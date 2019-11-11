package com.parcial.parcialito;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class registrar1 extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinner2;
    private int carr;
    private int gen;

    private EditText txtCorreo;
    private EditText txtContraseña;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCelular;
    private  EditText txtLogroH;
    private Button btnGuardar;
    private Connection conn;
    private Statement st = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);
        txtCorreo = (EditText) findViewById(R.id.txtUsuario);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtCelular = (EditText) findViewById(R.id.txtCelular);
        txtLogroH = (EditText) findViewById(R.id.txtLogroH);
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
                carr = position;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
                gen = position1;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });








        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (txtCorreo.getText().toString().isEmpty() && txtContraseña.getText().toString().isEmpty() &&  txtNombre.getText().toString().isEmpty() &&  txtApellido.getText().toString().isEmpty() && txtCelular.getText().toString().isEmpty() && carr == 0 && gen == 0 && txtLogroH.getText().toString().isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Todos los campos vacíos, por favor ingrese los datos requeridos", Toast.LENGTH_LONG).show();
                }

                else if (txtCorreo.getText().toString().isEmpty() || txtContraseña.getText().toString().isEmpty() ||  txtNombre.getText().toString().isEmpty() ||  txtApellido.getText().toString().isEmpty() || txtCelular.getText().toString().isEmpty() || carr == 0 || gen == 0 || txtLogroH.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Algún campo vacío, por favor ingrese todos los datos requeridos", Toast.LENGTH_LONG).show();
                }



                else {
                    if(isValidEmail(txtCorreo.getText().toString().trim())) {
                        AgregarRegistro();
                        txtCorreo.setText("");
                        txtContraseña.setText("");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtCelular.setText("");
                        txtLogroH.setText("");
                    }


                    else{ Toast.makeText(getApplicationContext(), "Correo electronico invalido, El correo son las iniciales de tu primer apellido y tu número de carné.", Toast.LENGTH_SHORT).show();
                        txtCorreo.setError("Ingrese un correo válido eje. jonh123465@uvg.edu.gt");


                    }

                }


            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AgregarRegistro(){
        Connection conn = null;
        PreparedStatement pst = null;

        String[] cadena = txtCorreo.getText().toString().split("@");
        String usuario=cadena[0];
        String sql="insert into usuario(pk_loginName, password, nombre, apellido, fk_rol, activo, logroH, fk_carrera, Celular, sexo) values('" + usuario + "', '" + txtContraseña.getText().toString()+ "', '" +txtNombre.getText().toString() + "', '" + txtApellido.getText().toString() + "', 2, 1, " +  Integer.parseInt(txtLogroH.getText().toString() )+ ","+carr+",'"+txtCelular.getText().toString()+"', "+gen+")";

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



    //_________________________METODOS_PARA_VALIDACIONES_________________________
    //_________________________VALIDACION EMAIL________________________________
    private boolean isValidEmail(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches(); }





}

