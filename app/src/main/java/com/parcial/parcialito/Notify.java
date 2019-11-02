package com.parcial.parcialito;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Notify extends AppCompatActivity {
    private RecyclerView recyclerViewNotify;
    private RecyclerViewAdaptatorNotify adaptatorNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        //instanciamos el RecyclerView
        recyclerViewNotify=(RecyclerView)findViewById(R.id.recyclerNotify);
        recyclerViewNotify.setLayoutManager(new LinearLayoutManager(this));

        //le agregamos los eventos al RecyclerView
        try {
            adaptatorNotify=new RecyclerViewAdaptatorNotify(new BDNotify().execute(cargarUsuario()).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(Notify.this,  e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(Notify.this,  e.getMessage(), Toast.LENGTH_LONG).show();
        }
        recyclerViewNotify.setAdapter(adaptatorNotify);
    }

    private String cargarUsuario() {
        SharedPreferences preferences = getSharedPreferences("username", MODE_PRIVATE);
        String loginName=preferences.getString("user","");

        return loginName;
    }
}
