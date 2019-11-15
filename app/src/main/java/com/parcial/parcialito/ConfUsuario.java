
package com.parcial.parcialito;

import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConfUsuario extends AppCompatActivity {
    private TextView txtVista;
    private EditText txtCelular;
    private EditText txtLogroH;
    private Button btnGuardar;
    private Connection conn;
    private Statement st = null;
    private Spinner spinner;
    private int carr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_usuario);

        txtVista = (TextView) findViewById(R.id.txtVista);
        txtCelular = (EditText) findViewById(R.id.txtCelular);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        txtLogroH = (EditText) findViewById(R.id.txtLogroH);

        // Funcion que carga sesión actual
        String loginName = cargarUsuario();
        txtVista.setText(loginName);



        //Funcion para utilizar Spinner
        spinner = findViewById(R.id.spCiclo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.semestre,R.layout.spinner_item_modificar);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carr = position;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });




        // Funcion para boton guardar
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

        if (txtCelular.getText().toString().isEmpty() && txtLogroH.getText().toString().isEmpty() && carr==0 ) {
            Toast.makeText(getApplicationContext(), "Llene algún campo para modificar", Toast.LENGTH_LONG).show();
        }

        else if(txtCelular.getText().toString().isEmpty() && carr==0) {
            sql = "update usuario set logroH= '"+ txtLogroH.getText().toString() +"'  where pk_loginName ='" +loginName+ "'";
        }
        else if (txtLogroH.getText().toString().isEmpty() && carr==0){
            sql = "update usuario set Celular= '"+ txtCelular.getText().toString() + "' where pk_loginName ='"+loginName+"'";

        }
        else if (txtCelular.getText().toString().isEmpty() &&txtLogroH.getText().toString().isEmpty() ){
            sql = "update usuario set ciclo=  "+carr+"  where pk_loginName ='"+loginName+"'";
        }

        else if (txtLogroH.getText().toString().isEmpty() ||txtCelular.getText().toString().isEmpty() || carr == 0 ) {
         if (txtLogroH.getText().toString().isEmpty()) {
                sql = "update usuario set  ciclo= " + carr + ",  Celular= '" + txtCelular.getText().toString() + "' where pk_loginName ='" + loginName + "'";


            } else if (txtCelular.getText().toString().isEmpty()) {

             sql = "update usuario set logroH= '"+ txtLogroH.getText().toString() + "', ciclo= "+carr+" where pk_loginName ='" +loginName +"'";

            }

              else if (carr == 0) {
                Toast.makeText(getApplicationContext(), "Llene algún campo para modificar", Toast.LENGTH_LONG).show();


            }
        }

        else {
            sql = "update usuario set logroH= '"+ txtLogroH.getText().toString() + "', ciclo= "+carr+",  Celular= '" + txtCelular.getText().toString() + "' where pk_loginName ='" +loginName +"'";

        }


        try {
            Conexion conexion = new Conexion();
            conn = conexion.connect();

            pst = conn.prepareStatement(sql);
            pst.executeUpdate();

            Toast.makeText(getApplicationContext(),"Has realizado un cambio",Toast.LENGTH_SHORT).show();
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