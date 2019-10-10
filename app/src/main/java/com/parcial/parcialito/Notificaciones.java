package com.parcial.parcialito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Notificaciones extends AppCompatActivity {

    private RecyclerView recyclerViewEvento;
    private RecyclerViewAdaptator adaptatorEvento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        recyclerViewEvento=(RecyclerView)findViewById(R.id.recyclerEvento);
        recyclerViewEvento.setLayoutManager(new LinearLayoutManager(this));

        adaptatorEvento=new RecyclerViewAdaptator(obtenerEventos());
        recyclerViewEvento.setAdapter(adaptatorEvento);
    }

    public List<EventoModelo> obtenerEventos(){
        List<EventoModelo> evento = new ArrayList<>();
        evento.add(new EventoModelo("Festival de los sueños", "Colaborador de stand","10/11/2019 de 8:00 AM a 12:00 PM","Psicóloga"));
        return evento;

    }
}
