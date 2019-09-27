package com.parcial.parcialito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


    public class Galeria extends AppCompatActivity implements View.OnClickListener {

        private Button btnAnterior;
        private Button btnSiguiente;
        private ImageView imgFotos;
        private TextView lblDescripcion;
        int contador = 0, total = 0;
        private int[] fotoId = {R.drawable.fa, R.drawable.fb, R.drawable.fc, R.drawable.fd};
        private String[] descripcion = {"Foto Grupal", "Andres", "Obed","Kenneth"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_galeria);
            btnAnterior = findViewById(R.id.btnAnterior);
            btnSiguiente = findViewById(R.id.btnSiguiente);
            imgFotos =  findViewById(R.id.imgView);
            total = fotoId.length;

            lblDescripcion = findViewById(R.id.lblDescripcion);
            btnSiguiente.setOnClickListener(this);
            btnAnterior.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int id = v.getId();

            if(id == R.id.btnSiguiente){
                contador++;
                if(contador == total){
                    contador = 0;
                }
            }
            if(id == R.id.btnAnterior){
                contador--;
                if(contador == -1){
                    contador = total -1;
                }
            }
            imgFotos.setImageResource(fotoId[contador]);
            lblDescripcion.setText(descripcion[contador]);
        }
}
