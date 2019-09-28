package com.parcial.parcialito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
public class Registro extends AppCompatActivity {

    private EditText lblNombre;
    private EditText lblApellido;
    private EditText lblNickName;
    private EditText lblPassword;
    private EditText lblCorreo;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        lblNombre = (EditText) findViewById(R.id.lblNombre);
        lblApellido = (EditText) findViewById(R.id.lblApellido);
        lblNickName = (EditText) findViewById(R.id.lblNickName);
        lblPassword = (EditText) findViewById(R.id.lblPassword);
        lblCorreo = (EditText) findViewById(R.id.lblCorreo);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = validarLogin();
                if (lblNombre.getText().toString().equals("") || lblApellido.getText().toString().equals("") || lblNickName.getText().toString().equals("") ||lblPassword.getText().toString().equals("") ||lblCorreo.getText().toString().equals("")){
                    Toast.makeText(Registro.this, "Hay datos vacios", Toast.LENGTH_LONG).show();
                }
                else if (user.equals("1")){
                    Toast.makeText(Registro.this, "Guardado en la base de datos", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Registro.this, "Error al guardar en la base de datos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    //Funcion que establece la conexion con la base de datos y retorna un objeto de tipo usuario
    public String validarLogin(){
        //Variable que almacenará el resultado de la consulta
        String cliente = null;
        //Asignamos el driver de conexion
        String driver = "com.mysql.jdbc.Driver";
        try{
            //Cargamos el driver con el conector jdbc
            Class.forName(driver).newInstance();
            Cliente user = new Cliente(lblNombre.getText().toString(), lblApellido.getText().toString(), lblNickName.getText().toString(), lblPassword.getText().toString(), lblCorreo.getText().toString());
            cliente = new RegistroCliente().execute(user).get();
        } catch(Exception ex){
            Toast.makeText(Registro.this, "Error al conectarse a la BD" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return cliente;
    }
}
*/