package com.parcial.parcialito;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RecyclerViewAdaptator extends RecyclerView.Adapter<RecyclerViewAdaptator.ViewHolder> {



    public class ViewHolder extends RecyclerView.ViewHolder  {
        //context
        private Context context;
        //variables
        private TextView txtActividad, txtDescripcion, txtFecha, txtCoordinador;
        private Button btnAceptar;
        private int id_evento, id_asistencia; //Contiene la id del evento
        private String evento, coordinador;
        private String loginName, tipo, contenido="";
        private int posicion;

        public ViewHolder( View itemView) {
            super(itemView);
            context= itemView.getContext();
            txtActividad= (TextView)itemView.findViewById(R.id.txtActividad);
            txtDescripcion= (TextView)itemView.findViewById(R.id.txtDescripcion);
            txtFecha= (TextView)itemView.findViewById(R.id.txtFecha);
            txtCoordinador= (TextView)itemView.findViewById(R.id.txtCoordinador);
            btnAceptar= (Button)itemView.findViewById(R.id.btnAceptar);



        }
        void HacerOnClickListener1(){

            btnAceptar.setOnClickListener(new View.OnClickListener() {
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
                    instanciarAlerta.setTitle("¿Deseas aceptar el evento?");
                    instanciarAlerta.show();
                }
            });
            //Definir los click listener
            //btnAceptar.setOnClickListener(this);
        }

        void HacerOnClickListener2(){

            btnAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alerta= new AlertDialog.Builder(context);
                    final EditText motivo = new EditText(context);
                    motivo.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                    motivo.setHint("Ingrese el motivo");
                    motivo.setMaxLines(2);
                    alerta.setMessage(contenido)
                            .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    rechazarEvento(motivo.getText().toString());
                                    /*
                                    //forma para cerrar la activity usando context
                                    ((Notificaciones)context).finish();

                                    //forma para abrir una activity usando context
                                    Intent intent = new Intent(context, Notificaciones.class);
                                    context.startActivity(intent);

                                     */

                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    alerta.setView(motivo);
                    AlertDialog instanciarAlerta = alerta.create();
                    instanciarAlerta.setTitle("¿Por qué no podrás asistir?");
                    instanciarAlerta.show();
                }
            });
            //Definir los click listener
            //btnAceptar.setOnClickListener(this);
        }
        void HacerOnClickListener3(){

            btnAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DarHorasPerfil.class);
                    intent.putExtra("id", ""+id_evento);
                    context.startActivity(intent);
                }
            });


            //Definir los click listener
            //btnAceptar.setOnClickListener(this);
        }

            //Click listener
       /* @Override
        public void onClick(View v) {

            if(v.getId() == R.id.btnAceptar){
                //Crea el Dialogo de alerta

            }
        }

        */

        private void aceptarEvento() {
            //Crea asistencia en Maria DB
            Asistencia asist = new Asistencia(0,id_evento,loginName,0);

            String result = null;
            try{
                result = new BDAsistenciaAcept().execute(asist).get();

                } catch(Exception ex){

            }
            if(result.equals("1")){
                eventoLista.remove(posicion);
                //método que actualiza el recycler view
                notifyDataSetChanged();
                Toast.makeText(context, "Has aceptado un evento" , Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, "Error al aceptar el evento" , Toast.LENGTH_LONG).show();

            }


        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void rechazarEvento(String descripcion){
            Connection conn = null;
            PreparedStatement pst1 = null;
            PreparedStatement pst2 = null;
            PreparedStatement pst3 = null;
            String resultado;
            String update = "update asistencia set estado=2 where id_asistencia="+id_asistencia;
            String insert = "insert into inasistencia (fk_asistencia, descripcion, estado) values("+id_asistencia+",'"+descripcion+"',1)";
            String sql = "insert into notificacion (origen, destino, fk_tipo, descripcion, horaCreacion, estado) values ('"+loginName+"', '"+coordinador+"', 3, '"+evento+"', now(), 1)";

            try{
                //conexionMySql = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, userName, password);

                //st = conexionMySql.createStatement();
                Conexion conexion = new Conexion();
                conn = conexion.connect();

                pst1 = conn.prepareStatement(update);
                pst1.executeUpdate();

                pst2 = conn.prepareStatement(insert);
                pst2.executeUpdate();

                pst2 = conn.prepareStatement(sql);
                pst2.executeUpdate();

                eventoLista.remove(posicion);
                //método que actualiza el recycler view
                notifyDataSetChanged();

                resultado="1";
            } catch (SQLException ex) {
                resultado="0";
            }
            finally
            {
                try
                {
                    if (conn!=null) {
                        conn.close();
                    }
                    if (pst1!=null) {
                        pst1.close();
                    }
                    if (pst2!=null) {
                        pst2.close();
                    }
                    if (pst3!=null) {
                        pst3.close();
                    }
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

            if(resultado.equals("1")){
                Toast.makeText(context, "Acción realizada con éxito" , Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, "Error al realizar la acción" , Toast.LENGTH_LONG).show();

            }


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
        bolder.txtActividad.setText(eventoLista.get(position).getActividad());
        bolder.evento = bolder.txtActividad.getText().toString();
        bolder.txtDescripcion.setText(eventoLista.get(position).getDescripcion());
        bolder.txtFecha.setText(eventoLista.get(position).getFecha());
        bolder.txtCoordinador.setText(eventoLista.get(position).getCoordinador());
        bolder.coordinador = bolder.txtCoordinador.getText().toString();
        bolder.posicion = position;

        bolder.id_evento=eventoLista.get(position).getId();
        bolder.id_asistencia=eventoLista.get(position).getId_asistencia();
        bolder.loginName=eventoLista.get(position).getLoginName();
        bolder.tipo=eventoLista.get(position).getTipo();
        bolder.contenido=eventoLista.get(position).getActividad()+"\n"+ eventoLista.get(position).getDescripcion()+"\n"+ eventoLista.get(position).getFecha()+"\n"+ eventoLista.get(position).getCoordinador();

        if(bolder.tipo.equals("1")){
            bolder.HacerOnClickListener1();
        }
        else if(bolder.tipo.equals("2")){
            bolder.btnAceptar.setText("No podré asistir");
            bolder.HacerOnClickListener2();
        }
        else if(bolder.tipo.equals("3")){
            bolder.btnAceptar.setText("Administrar asistencias");
            bolder.HacerOnClickListener3();
        }



    }



    @Override
    public int getItemCount() {
        return eventoLista.size();
    }


}
