package com.parcial.parcialito;

import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//pide un Integer, procesa, retorna un List<EventoModelo>
public class BDEvento extends AsyncTask<Integer, Void, List<EventoModelo>>{
    private Connection conn;
    private Statement st = null;
    private ResultSet rs = null;
    private List<EventoModelo> evento = new ArrayList<>();

    protected List<EventoModelo> doInBackground(Integer... datos) {
        //Crea el query para extraer los datos de la BD
        String sql = "select id_evento, nombre, descripcion, activo, fk_loginName, fechaInicio, fechaFin, cantidadH, cantidadPR, cantidadPA from evento where activo = '1'";


        try{
            //Establece la conexion
            Conexion conexion = new Conexion();
            conn = conexion.connect();
            st = conn.createStatement();

            //envía el query
            rs = st.executeQuery(sql);
            if(rs.first())
            {
                //Empieza a llenar el List<EventoModelo>, que lleva todos los objetos a mostrar
                do
                {
                    String actividad=rs.getString("nombre");
                    String descripcion=rs.getString("descripcion");
                    String fecha=rs.getString("fechaInicio");
                    String coordinador=rs.getString("fk_loginName");

                    evento.add(new EventoModelo(""+actividad,""+descripcion,""+fecha,""+coordinador));
                    //parámetro para agregar una List versión 1
                    //evento.add(new EventoModelo(rs.getString("nombre"), rs.getString("descripcion"), fecha, rs.getString("fk_loginName")));

                    //parámetro para recibire el query y almacenarlo en un objeto Evento
                    //columnas = new Evento(rs.getInt("id_evento"), rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("activo"), rs.getString("fk_loginName"), rs.getString("fechaInicio"), rs.getString("fechaFin"), rs.getInt("cantidadH"), rs.getInt("cantidadPR"), rs.getInt("cantidadPA"));
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
        return evento;
    }
}
