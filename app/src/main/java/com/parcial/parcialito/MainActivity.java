package com.parcial.parcialito;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnIngresar;
    private Button btnIniciarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);

        //Llama a las preferencias (cargar usuario y contraseña pre-registrado)
        cargarPreferencias();

        //action listener
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion1 = new Intent(MainActivity.this, Menu.class);
                Intent navegacion2 = new Intent(MainActivity.this, Administrador.class);
                Usuario user = validarLogin();
                if(user != null){
                    if(user.getActivo() == 0){
                        Toast.makeText(MainActivity.this, "Usuario inactivo", Toast.LENGTH_LONG).show();
                        txtUsuario.setText("");
                        txtPassword.setText("");
                    } else if(user.getIdRol() == 1 && user.getActivo() == 1){
                        guardarPreferencias();
                        startActivity(navegacion2);
                    } else if(user.getIdRol() == 2 && user.getActivo() == 1){
                        guardarPreferencias();
                        startActivity(navegacion1);
                    }
                } else{
                    txtUsuario.setText("");
                    txtPassword.setText("");
                    Toast.makeText(MainActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navegacion3 = new Intent(MainActivity.this, registrar1.class);
                startActivity(navegacion3);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            alerta.setMessage("¿Desea Salir de la Aplicación?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            finishAffinity();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            AlertDialog titulo = alerta.create();
            titulo.setTitle("Salir");
            titulo.show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //Método que sirve para guardar el Usuario en el dispositivo
    public void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("username", MODE_PRIVATE);

        String usuario=txtUsuario.getText().toString();
        String password=txtPassword.getText().toString();

        SharedPreferences.Editor editor =preferences.edit();
        editor.putString("user",usuario);
        editor.putString("pass",password);

        editor.commit();
    }

    public void cargarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("username", MODE_PRIVATE);

        String loginName=preferences.getString("user","");
        String password=preferences.getString("pass","");

        txtUsuario.setText(loginName);
        txtPassword.setText(password);

    }

    //Funcion que establece la conexion con la base de datos y retorna un objeto de tipo usuario
    public Usuario validarLogin(){
        //Variable que almacenará el resultado de la consulta
        Usuario usuario = null;
        try{
            Usuario user = new Usuario(txtUsuario.getText().toString(), txtPassword.getText().toString(),"","",0,0,0,0,"",0,0,0);
            usuario = new Login().execute(user).get();
        } catch(Exception ex){
            Toast.makeText(MainActivity.this, "Error al conectarse a la BD" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }




        return usuario;
    }


}
