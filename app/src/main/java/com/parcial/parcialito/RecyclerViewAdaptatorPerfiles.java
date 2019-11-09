package com.parcial.parcialito;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import java.util.List;

public class RecyclerViewAdaptatorPerfiles extends RecyclerView.Adapter<RecyclerViewAdaptatorPerfiles.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        //context
        private Context context;
        //variables
        private TextView txtCorreo, txtNombre, txtCarrera, txtEventosComun;
        private Button btnTelefono;
        private String perfil;
        private String loginName, contenido="";
        private String tipo;

        public ViewHolder( View itemView) {
            super(itemView);
            context= itemView.getContext();
            txtCorreo= (TextView)itemView.findViewById(R.id.txtCorreo);
            txtNombre= (TextView)itemView.findViewById(R.id.txtNombre);
            txtCarrera= (TextView)itemView.findViewById(R.id.txtCarrera);
            txtEventosComun= (TextView)itemView.findViewById(R.id.txtEventosComun);
            btnTelefono= (Button)itemView.findViewById(R.id.btnTelefono);



        }

        void HacerOnClickListener1(){

            btnTelefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alerta= new AlertDialog.Builder(context);
                    alerta.setMessage(contenido)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    solicitarTelefono();
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

        void HacerOnClickListener2() {

            btnTelefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                    final EditText hrs = new EditText(context);
                    hrs.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                    hrs.setHint("Horas para adjuntarle");
                    hrs.setMaxLines(1);
                    hrs.setInputType(InputType.TYPE_CLASS_NUMBER);
                    alerta.setMessage(contenido)
                            .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    adjuntarHoras(hrs.getText().toString());
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
                    alerta.setView(hrs);
                    AlertDialog instanciarAlerta = alerta.create();
                    instanciarAlerta.setTitle("¿Cuánta horas deseas adjuntarle?");
                    instanciarAlerta.show();
                }
            });
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void solicitarTelefono(){
            Connection conn = null;
            PreparedStatement pst1 = null;
            String resultado;
            String sql = "insert into notificacion (origen, destino, fk_tipo, horaCreacion, estado) values ('"+loginName+"', '"+perfil+"', 1, now(), 1)";

            try{
                //conexionMySql = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, userName, password);

                //st = conexionMySql.createStatement();
                Conexion conexion = new Conexion();
                conn = conexion.connect();

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

        private void adjuntarHoras(String horas){
            String id = ((DarHorasPerfil)context).getIntent().getExtras().getString("id");
            Connection conn = null;
            PreparedStatement pst1 = null;
            String resultado;
            String sql = "insert into tiempo (fk_loginName, fk_evento, horas, estado) values('" +loginName +"'," +id +"," +horas +",1)";

            try{
                Conexion conexion = new Conexion();
                conn = conexion.connect();

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
        bolder.txtCorreo.setText(perfilLista.get(position).getCorreo() + "@uvg.edu.gt");
        bolder.perfil = perfilLista.get(position).getCorreo();
        bolder.txtNombre.setText(perfilLista.get(position).getNombre());
        bolder.txtCarrera.setText(perfilLista.get(position).getCarrera());
        bolder.txtEventosComun.setText(perfilLista.get(position).getEventosComun());
        bolder.tipo = perfilLista.get(position).getTipo();

        bolder.contenido=perfilLista.get(position).getNombre()+"\nDe la carrera "+ perfilLista.get(position).getCarrera();

        if(bolder.tipo.equals("1")){
            bolder.loginName=perfilLista.get(position).getLoginName();
            bolder.HacerOnClickListener1();
        }
        else if(bolder.tipo.equals("2")){
            bolder.btnTelefono.setText("Dar Horas");
            bolder.loginName=perfilLista.get(position).getCorreo();
            bolder.HacerOnClickListener2();
        }
    }



    @Override
    public int getItemCount() {
        return perfilLista.size();
    }


}

