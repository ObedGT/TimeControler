
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

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AgregarUsuario extends AppCompatActivity {

    private Spinner spinner;
    private EditText txtCorreo;
    private EditText txtContraseña;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCelular;
    private Button btnGuardar;
    private int gen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario2);

        txtCorreo = (EditText) findViewById(R.id.txtUsuario);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtCelular = (EditText) findViewById(R.id.txtCelular);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        spinner = findViewById(R.id.spGenero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Genero,R.layout.spinner_item_modificar);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gen = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (txtCorreo.getText().toString().isEmpty() && txtContraseña.getText().toString().isEmpty() && txtNombre.getText().toString().isEmpty() && txtApellido.getText().toString().isEmpty() && txtCelular.getText().toString().isEmpty() && gen==0) {
                    Toast.makeText(getApplicationContext(), "Todos los campos vacíos, por favor ingrese los datos requeridos", Toast.LENGTH_LONG).show();
                }
                else if (txtCorreo.getText().toString().isEmpty() || txtContraseña.getText().toString().isEmpty() || txtNombre.getText().toString().isEmpty() || txtApellido.getText().toString().isEmpty() || txtCelular.getText().toString().isEmpty() || gen==0){
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

                    } else{ Toast.makeText(getApplicationContext(), "Correo electronico invalido, El correo son las iniciales de tu primer apellido y tu número de carné.", Toast.LENGTH_SHORT).show();
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
        String sql="insert into usuario(pk_loginName, password, nombre, apellido, fk_rol, activo, celular, sexo) values('" + usuario + "', '" + txtContraseña.getText().toString()+ "', '" +txtNombre.getText().toString() + "', '" + txtApellido.getText().toString() + "', 1, 1, '"+txtCelular.getText().toString()+"', "+gen+")";
        try {
            Conexion conexion = new Conexion();
            conn = conexion.connect();

            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            Toast.makeText(getApplicationContext(),"Registro Exitoso",Toast.LENGTH_SHORT).show();

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






/*
    public static SecretKey generateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeySpec secret;
        String password = null;
        return secret = new SecretKeySpec(password.getBytes(), "AES");
    }

    public static byte[] encryptMsg(String message, SecretKey secret)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
        /* Encrypt the message. */ /*
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] cipherText = cipher.doFinal(message.getBytes("UTF-8"));
        return cipherText;
    }

    public static String decryptMsg(byte[] cipherText, SecretKey secret)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException
    {
        /* Decrypt the message, given derived encContentValues and initialization vector. */
       /* Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        String decryptString = new String(cipher.doFinal(cipherText), "UTF-8");
        return decryptString;
    }


*/







    //_________________________METODOS_PARA_VALIDACIONES_________________________
    //_________________________VALIDACION EMAIL________________________________
    private boolean isValidEmail(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches(); }






}
