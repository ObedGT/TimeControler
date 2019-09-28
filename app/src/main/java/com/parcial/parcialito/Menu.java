package com.parcial.parcialito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {
    private Button btnhoras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        btnhoras = (Button) findViewById(R.id.btnhoras);

        btnhoras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion1 = new Intent(Menu.this, cuantasHorasLlevo.class);
                startActivity(navegacion1);
            }
        });

/*
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion2 = new Intent(Menu.this, Video.class);
                startActivity(navegacion2);
            }
        });
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion3 = new Intent(Menu.this, Calculadora.class);
                startActivity(navegacion3);
            }
        });
        btnFormulario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent navegacion4 = new Intent(Menu.this, Registro.class);
                startActivity(navegacion4);
            }
        });


        btnFormulario2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion5 = new Intent(Menu.this, Users.class);
                startActivity(navegacion5);
            }
        });
        
 */
    }
}
