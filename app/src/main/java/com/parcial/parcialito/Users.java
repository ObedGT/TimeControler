package com.parcial.parcialito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Users extends AppCompatActivity {
    private EditText lblNickName;
    private EditText lblPassword;
    private EditText lblNombre;
    private EditText lblApellido;
    private EditText lblRol;
    private EditText lblActivo;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        lblNickName = (EditText) findViewById(R.id.lblNickName);
        lblPassword = (EditText) findViewById(R.id.lblPassword);
        lblNombre = (EditText) findViewById(R.id.lblNombre);
        lblApellido = (EditText) findViewById(R.id.lblApellido);
        lblRol = (EditText) findViewById(R.id.lblRol);
        lblActivo = (EditText) findViewById(R.id.lblActivo);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String user = validarLogin();
            if (lblNombre.getText().toString().equals("") || lblApellido.getText().toString().equals("") || lblNickName.getText().toString().equals("") ||lblPassword.getText().toString().equals("") ||lblRol.getText().toString().equals("") ||lblActivo.getText().toString().equals("")){
                Toast.makeText(Users.this, "Hay datos vacios", Toast.LENGTH_LONG).show();
            }
            else if (user.equals("1")){
                Toast.makeText(Users.this, "Guardado en la base de datos", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(Users.this, "Error al guardar en la base de datos", Toast.LENGTH_LONG).show();
            }

        }
    });
}
    //Funcion que establece la conexion con la base de datos y retorna un objeto de tipo usuario
    public String validarLogin(){
        //Variable que almacenará el resultado de la consulta
        String usuario = null;
        //Asignamos el driver de conexion
        String driver = "com.mysql.jdbc.Driver";
        try{
            //Cargamos el driver con el conector jdbc
            Class.forName(driver).newInstance();
            //Usuario user = new Usuario(lblNickName.getText().toString(), lblPassword.getText().toString(), lblNombre.getText().toString(), lblApellido.getText().toString(), Integer.valueOf(lblRol.getText().toString()),Integer.valueOf(lblActivo.getText().toString()));
            //usuario = new Login2().execute(user).get();
        } catch(Exception ex){
            Toast.makeText(Users.this, "Error al conectarse a la BD" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return usuario;
    }
}
