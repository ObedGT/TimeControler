package com.parcial.parcialito;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.parcial.parcialito.R;

public class Administrador extends AppCompatActivity {
    private Button btnAgregar;
    private  Button btnEvento;
    private  Button btnAsistencia;
    private  Button btnInasistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnEvento = (Button) findViewById(R.id.btnEvento);
        btnAsistencia = (Button) findViewById(R.id.btnAsistencia);
        btnInasistencia = (Button) findViewById(R.id.btnInasistencia);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion = new Intent(Administrador.this, AgregarUsuario.class);
                startActivity(navegacion);
            }
        });


        btnEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion = new Intent(Administrador.this, CrearEvento.class);
                startActivity(navegacion);
            }
        });

        btnAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion = new Intent(Administrador.this, DarHorasEvento.class);
                startActivity(navegacion);
            }
        });








    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            AlertDialog.Builder alerta = new AlertDialog.Builder(Administrador.this);
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
}
