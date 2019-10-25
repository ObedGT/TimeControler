package com.parcial.parcialito;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class PonteEnContacto extends AppCompatActivity {
    private RecyclerView recyclerViewPerfil;
    private RecyclerViewAdaptatorPerfiles adaptatorPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponte_en_contacto);

        //instanciamos el RecyclerView
        recyclerViewPerfil=(RecyclerView)findViewById(R.id.recyclerPerfil);
        recyclerViewPerfil.setLayoutManager(new LinearLayoutManager(this));

        //le agregamos los eventos al RecyclerView
        try {
            adaptatorPerfil=new RecyclerViewAdaptatorPerfiles(new BDPerfil().execute(cargarUsuario()).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(PonteEnContacto.this,  e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(PonteEnContacto.this,  e.getMessage(), Toast.LENGTH_LONG).show();
        }
        recyclerViewPerfil.setAdapter(adaptatorPerfil);
    }

    private String cargarUsuario() {
        SharedPreferences preferences = getSharedPreferences("username", MODE_PRIVATE);
        String loginName=preferences.getString("user","");

        return loginName;
    }
}
