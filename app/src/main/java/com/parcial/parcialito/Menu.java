package com.parcial.parcialito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
