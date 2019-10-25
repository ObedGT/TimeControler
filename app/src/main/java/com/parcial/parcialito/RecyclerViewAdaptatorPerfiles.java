package com.parcial.parcialito;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdaptatorPerfiles extends RecyclerView.Adapter<RecyclerViewAdaptatorPerfiles.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        //context
        private Context context;
        //variables
        private TextView txtCorreo, txtNombre, txtCarrera, txtEventosComun;
        private Button btnTelefono;
        private int id; //Contiene la id del evento
        private String loginName, contenido="";

        public ViewHolder( View itemView) {
            super(itemView);
            context= itemView.getContext();
            txtCorreo= (TextView)itemView.findViewById(R.id.txtCorreo);
            txtNombre= (TextView)itemView.findViewById(R.id.txtNombre);
            txtCarrera= (TextView)itemView.findViewById(R.id.txtCarrera);
            txtEventosComun= (TextView)itemView.findViewById(R.id.txtEventosComun);
            btnTelefono= (Button)itemView.findViewById(R.id.btnTelefono);



        }
        void HacerOnClickListener(){

            btnTelefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alerta= new AlertDialog.Builder(context);
                    alerta.setMessage(contenido)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    aceptarEvento();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog instanciarAlerta = alerta.create();
                    instanciarAlerta.setTitle("¿Deseas pedir el número de teléfono a?");
                    instanciarAlerta.show();
                }
            });
            //Definir los click listener
            //btnAceptar.setOnClickListener(this);
        }

        private void aceptarEvento() {
            //Crea asistencia en Maria DB
            Asistencia asist = new Asistencia(0,id,loginName,0);

            String result = null;
            try{
                result = new BDAsistenciaAcept().execute(asist).get();
            } catch(Exception ex){

            }
            if(result.equals("1")){
                Toast.makeText(context, "Se ha pedido un número de telefono" , Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, "Error al pedir el número de telefono" , Toast.LENGTH_LONG).show();

            }


        }
    }



    public List<PerfilModelo> perfilLista;

    public RecyclerViewAdaptatorPerfiles(List<PerfilModelo> perfilLista) {
        this.perfilLista = perfilLista;
    }

    @Override
    public RecyclerViewAdaptatorPerfiles.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_perfil,parent,false);
        RecyclerViewAdaptatorPerfiles.ViewHolder viewHolder = new RecyclerViewAdaptatorPerfiles.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdaptatorPerfiles.ViewHolder bolder, int position) {
        bolder.txtCorreo.setText(perfilLista.get(position).getCorreo());
        bolder.txtNombre.setText(perfilLista.get(position).getNombre());
        bolder.txtCarrera.setText(perfilLista.get(position).getCarrera());
        bolder.txtEventosComun.setText(perfilLista.get(position).getEventosComun());

        bolder.loginName=perfilLista.get(position).getLoginName();
        bolder.contenido=perfilLista.get(position).getNombre()+"\nDe la carrera "+ perfilLista.get(position).getCarrera();
        bolder.HacerOnClickListener();
    }



    @Override
    public int getItemCount() {
        return perfilLista.size();
    }


}

