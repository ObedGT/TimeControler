package com.parcial.parcialito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                Intent navegacion1 = new Intent(Administrador.this, AgregarUsuario.class);
                startActivity(navegacion1);
            }
        });


        btnEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navegacion2 = new Intent(Administrador.this, CrearEvento.class);
                startActivity(navegacion2);
            }
        });








    }
}
