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
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {
    private Button btnHora;
    private Button btnEventosAceptados;
    private Button btnNoti;
    private Button btnPonte;
    private Button btnAsistir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        btnHora = (Button) findViewById(R.id.btnhoras);
        btnEventosAceptados = (Button) findViewById(R.id.btnagenda);
        btnNoti = (Button) findViewById(R.id.btnnoti);
        btnPonte = (Button) findViewById(R.id.btncontacto);
        btnAsistir = (Button) findViewById(R.id.btnasistir);

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion = new Intent(Menu.this, CuantasHorasLlevo.class);
                startActivity(navegacion);
            }
        });

        btnEventosAceptados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion = new Intent(Menu.this, EventosAceptados.class);
                startActivity(navegacion);
            }
        });

        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion = new Intent(Menu.this, Notificaciones.class);
                startActivity(navegacion);
            }
        });

        btnPonte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion = new Intent(Menu.this, PonteEnContacto.class);
                startActivity(navegacion);
            }
        });

        btnAsistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion = new Intent(Menu.this, Notify.class);
                startActivity(navegacion);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            AlertDialog.Builder alerta = new AlertDialog.Builder(Menu.this);
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
