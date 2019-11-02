package com.parcial.parcialito;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RecyclerViewAdaptatorNotify extends RecyclerView.Adapter<RecyclerViewAdaptatorNotify.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        //context
        private Context context;
        //variables
        private TextView txtOrigen, txtTipo, txtDescrip;
        private Button btnIzquierda, btnDerecha;
        private String origen;
        private int id; //Contiene la id de la notificacion
        private String loginName;

        public ViewHolder( View itemView) {
            super(itemView);
            context= itemView.getContext();
            txtOrigen= (TextView)itemView.findViewById(R.id.txtOrigen);
            txtTipo= (TextView)itemView.findViewById(R.id.txtTipo);
            txtDescrip= (TextView)itemView.findViewById(R.id.txtDescrip);
            btnIzquierda= (Button)itemView.findViewById(R.id.btnIzquierda);
            btnDerecha= (Button)itemView.findViewById(R.id.btnDerecha);



        }


        void clickListenerTipo1(){

            btnDerecha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alerta= new AlertDialog.Builder(context);
                    alerta.setMessage(origen)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    enviarTelefono();
                                    ocultarNotificion();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog instanciarAlerta = alerta.create();
                    instanciarAlerta.setTitle("¿Estas seguro de enviar tu no. de teléfono a?");
                    instanciarAlerta.show();
                }
            });

            btnIzquierda.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {

                    ocultarNotificion();
                }
            });
        }

        void clickListenerTipo2y3(){

            btnIzquierda.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {

                 ocultarNotificion();

                }
            });
            //Definir los click listener
            //btnAceptar.setOnClickListener(this);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void ocultarNotificion(){
            Connection conn = null;
            PreparedStatement pst1 = null;
            String sql ="update notificacion set estado=2 where id_notificacion ="+id;

            try{
                Conexion conexion = new Conexion();
                conn = conexion.connect();

                pst1 = conn.prepareStatement(sql);
                pst1.executeUpdate();
            } catch (SQLException ex) {
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
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }


        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void enviarTelefono(){
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            PreparedStatement pst1 = null;
            String resultado;
            String telefono ="select celular from usuario where pk_loginName ='"+loginName+"'";

            try{
                Conexion conexion = new Conexion();
                conn = conexion.connect();

                st = conn.createStatement();
                rs = st.executeQuery(telefono);
                String celular="";

                //Ejecuta el query para obtener el no. de teléfono
                if(rs.first()) {
                    do {
                        celular = rs.getString("celular");
                    } while (rs.next());
                }

                String sql = "insert into notificacion (origen, destino, fk_tipo, descripcion, horaCreacion, estado) values ('"+loginName+"', '"+origen+"', 2, " +celular +", now(), 1)";
                //Ejecuta el query que inserta una nueva notificación
                pst1 = conn.prepareStatement(sql);
                pst1.executeUpdate();

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

    public List<NotifyModelo> notifyLista;

    public RecyclerViewAdaptatorNotify(List<NotifyModelo> notifyLista) {
        this.notifyLista = notifyLista;
    }

    @Override
    public RecyclerViewAdaptatorNotify.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notify,parent,false);
        RecyclerViewAdaptatorNotify.ViewHolder viewHolder = new RecyclerViewAdaptatorNotify.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdaptatorNotify.ViewHolder bolder, int position) {
        bolder.txtOrigen.setText("El usuario "+notifyLista.get(position).getOrigen());
        bolder.origen = notifyLista.get(position).getOrigen();
        bolder.txtDescrip.setText(notifyLista.get(position).getDescripcion());

        bolder.loginName=notifyLista.get(position).getDestino();
        bolder.id=notifyLista.get(position).getIdNotify();

        int int_tipo = notifyLista.get(position).getTipo();
        String tipo = "";
        if(int_tipo == 1){
            tipo = "Te ha pedido tu no. de teléfono";
            bolder.clickListenerTipo1();
        }
        else if (int_tipo == 2){
            tipo = "Te ha mandado su no. de teléfono:";
            bolder.btnDerecha.setText("");
            bolder.btnDerecha.setEnabled(false);
            bolder.clickListenerTipo2y3();
        }
        else if (int_tipo == 3){
            tipo = "No podrá asistir a:";
            bolder.btnDerecha.setText("");
            bolder.btnDerecha.setEnabled(false);
            bolder.clickListenerTipo2y3();
        }

        bolder.txtTipo.setText(tipo);
    }


    @Override
    public int getItemCount() {
        return notifyLista.size();
    }

}
