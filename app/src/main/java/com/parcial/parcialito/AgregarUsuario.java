
package com.parcial.parcialito;



import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
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
import java.sql.SQLException;
import java.sql.Statement;

public class AgregarUsuario extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinner2;
    private EditText txtCorreo;
    private EditText txtContraseña;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCelular;
    private Button btnGuardar;
    private int carr = 1;


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


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (txtNombre.getText().toString().isEmpty() && txtCorreo.getText().toString().isEmpty() && txtContraseña.getText().toString().isEmpty() && txtNombre.getText().toString().isEmpty() && txtApellido.getText().toString().isEmpty() && txtCelular.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Todos los campos vacíos, por favor ingrese los datos requeridos", Toast.LENGTH_LONG).show();
                } else {
                    AgregarRegistro();
                    AgregarRegistro();
                    txtCorreo.setText("");
                    txtContraseña.setText("");
                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtCelular.setText("");



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
        String sql="insert into usuario(pk_loginName, password, nombre, apellido, fk_rol, activo, fk_carrera, celular, sexo) values('" + usuario + "', '" + txtContraseña.getText().toString()+ "', '" +txtNombre.getText().toString() + "', '" + txtApellido.getText().toString() + "', 1, 1, "+carr+",r '"+txtCelular.getText().toString()+"','"+spinner.getSelectedItem()+"')";
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


}


