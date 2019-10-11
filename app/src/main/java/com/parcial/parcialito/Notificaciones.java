package com.parcial.parcialito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Notificaciones extends AppCompatActivity {

    private RecyclerView recyclerViewEvento;
    private RecyclerViewAdaptator adaptatorEvento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        //instanciamos el RecyclerView
        recyclerViewEvento=(RecyclerView)findViewById(R.id.recyclerEvento);
        recyclerViewEvento.setLayoutManager(new LinearLayoutManager(this));

        //le agregamos los eventos al RecyclerView
        try {
            adaptatorEvento=new RecyclerViewAdaptator(new BDEvento().execute(0).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(Notificaciones.this,  e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(Notificaciones.this,  e.getMessage(), Toast.LENGTH_LONG).show();
        }
        recyclerViewEvento.setAdapter(adaptatorEvento);
    }

    /*
    public List<EventoModelo> obtenerEventos(){
        List<EventoModelo> evento = new ArrayList<>();
        evento.add(new EventoModelo("Festival de los sueños", "Colaborador de stand","10/11/2019 de 8:00 AM a 12:00 PM","Psicóloga")); //Una forma de agregar un nuevo evento
        return evento;

    }

     */
}
