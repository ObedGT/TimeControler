package com.parcial.parcialito;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class DarHorasEvento extends AppCompatActivity {

    private RecyclerView recyclerViewEvento;
    private RecyclerViewAdaptator adaptatorEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_horas_evento);
        //instanciamos el RecyclerView
        recyclerViewEvento = (RecyclerView) findViewById(R.id.recyclerEvento);
        recyclerViewEvento.setLayoutManager(new LinearLayoutManager(this));

        //le agregamos los eventos al RecyclerView
        try {
            adaptatorEvento = new RecyclerViewAdaptator(new BDEvento().execute(cargarUsuario()).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(DarHorasEvento.this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(DarHorasEvento.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        recyclerViewEvento.setAdapter(adaptatorEvento);
    }

    private String cargarUsuario() {
        SharedPreferences preferences = getSharedPreferences("username", MODE_PRIVATE);
        String loginName = preferences.getString("user", "");
        loginName += " 3";

        return loginName;
    }
}