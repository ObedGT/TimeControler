package com.parcial.parcialito;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BDNotify extends AsyncTask<String, Void, List<NotifyModelo>> {

    private Connection conn;
    private Statement st = null;
    private ResultSet rs = null;
    private List<NotifyModelo> perfil = new ArrayList<>();

    protected List<NotifyModelo> doInBackground(String... datos) {
        //Crea el query para extraer los datos de la BD
        String loginName=datos[0];
        String sql1 = "select id_notificacion, origen, destino, fk_tipo, descripcion, horaCreacion from notificacion where destino='"+loginName+"' and estado=1";


        try{
            //Establece la conexion
            Conexion conexion = new Conexion();
            conn = conexion.connect();
            st = conn.createStatement();

            //envía el query
            rs = st.executeQuery(sql1);

            if(rs.first())
            {
                //Empieza a llenar el List<EventoModelo>, que lleva todos los objetos a mostrar
                do
                {
                    int id_notify = rs.getInt("id_notificacion");
                    String origen = rs.getString("origen");
                    String destino = rs.getString("destino");
                    int tipo = rs.getInt("fk_tipo");
                    String descripcion = rs.getString("descripcion");


                    perfil.add(new NotifyModelo(id_notify, origen, destino, tipo, descripcion));


                }while(rs.next());
            }
        } catch (SQLException ex) {
        }
        finally
        {
            try
            {
                if (conn!=null) {
                    st.close();
                    rs.close();
                    conn.close();
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return perfil;
    }
}
