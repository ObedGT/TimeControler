package com.parcial.parcialito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {
    private Button btnhoras;
    private Button btnagenda;
    private Button btnnoti;
    private Button btncontacto;
    private Button btnasistir;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        btnhoras = (Button) findViewById(R.id.btnhoras);
        btnagenda = (Button) findViewById(R.id.btnagenda);
        btnnoti = (Button) findViewById(R.id.btnnoti);
        btncontacto = (Button) findViewById(R.id.btncontacto);
        btnasistir = (Button) findViewById(R.id.btnasistir);

        btnhoras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion1 = new Intent(Menu.this, Horas.class);
                startActivity(navegacion1);
            }
        });

        btnagenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion2 = new Intent(Menu.this, Agenda.class);
                startActivity(navegacion2);
            }
        });
        btnnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion3 = new Intent(Menu.this, Notificaciones.class);
                startActivity(navegacion3);
            }
        });
        btncontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion4 = new Intent(Menu.this, Contacto.class);
                startActivity(navegacion4);
            }
        });
        btnasistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion5 = new Intent(Menu.this, Asistencia.class);
                startActivity(navegacion5);
            }
        });
    }
}
