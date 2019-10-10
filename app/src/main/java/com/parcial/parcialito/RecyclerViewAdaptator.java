package com.parcial.parcialito;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class RecyclerViewAdaptator extends RecyclerView.Adapter<RecyclerViewAdaptator.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView actividad, descripcion, fecha, coordinador;

        public ViewHolder( View itemView) {
            super(itemView);
            actividad= (TextView)itemView.findViewById(R.id.txtActividad);
            descripcion= (TextView)itemView.findViewById(R.id.txtDescripcion);
            fecha= (TextView)itemView.findViewById(R.id.txtFecha);
            coordinador= (TextView)itemView.findViewById(R.id.txtCoordinador);
        }
    }

    public List<EventoModelo> eventoLista;

    public RecyclerViewAdaptator(List<EventoModelo> eventoLista) {
        this.eventoLista = eventoLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder bolder, int position) {
        bolder.actividad.setText(eventoLista.get(position).getActividad());
        bolder.descripcion.setText(eventoLista.get(position).getDescripcion());
        bolder.fecha.setText(eventoLista.get(position).getFecha());
        bolder.coordinador.setText(eventoLista.get(position).getCoordinador());

    }

    @Override
    public int getItemCount() {
        return eventoLista.size();
    }
}
