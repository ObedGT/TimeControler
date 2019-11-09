package com.parcial.parcialito;

import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//pide un String, procesa, retorna un List<EventoModelo>
public class BDEvento extends AsyncTask<String, Void, List<EventoModelo>>{
    private Connection conn;
    private Statement st = null;
    private Statement st2 = null;
    private ResultSet rs = null;
    private ResultSet comparacion = null;
    private List<EventoModelo> evento = new ArrayList<>();

    protected List<EventoModelo> doInBackground(String... datos) {
        //Crea el query para extraer los datos de la BD
        String sql1 = "select id_evento, nombre, descripcion, estado, fk_loginName, fechaInicio, fechaFin, cantidadH, cantidadPR, cantidadPA from evento where estado = 1";

        String[] cadena = datos[0].split(" ");
        String loginName=cadena[0];
        String tipo =cadena[1];

        String sql2 = "select id_asistencia, fk_evento from asistencia where fk_loginName = '"+loginName+"' and estado = 1";
        String sql3 = "select id_evento, nombre, descripcion, fechaInicio, fechaFin from evento where fk_loginName = '"+loginName+"' and estado = 1";

        try{
            //Establece la conexion
            Conexion conexion = new Conexion();
            conn = conexion.connect();
            st = conn.createStatement();
            st2 = conn.createStatement();

            rs = st.executeQuery(sql3);
            if(tipo.equals("3")){
                if(rs.first())
                {
                    do
                    {
                        int id = rs.getInt("id_evento");
                        String actividad = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        String fecha = obtenerFecha(rs.getString("fechaInicio"), rs.getString("fechaFin"));

                        evento.add(new EventoModelo("" + actividad, "" + descripcion, "" + fecha, "" + loginName, id, 0, loginName, tipo));

                    }while(rs.next());
                }
            }
            else {
                //envía el query
                rs = st.executeQuery(sql1);

                comparacion = st2.executeQuery(sql2);

                if (rs.first()) {
                    //Empieza a llenar el List<EventoModelo>, que lleva todos los objetos a mostrar
                    do {
                        int id = rs.getInt("id_evento");
                        //Mira el estado de la asistencia
                        boolean asistenciaEstado = true;
                        int id_asistencia = 0;

                        //Mira en la base de datos si ya ha aceptado el evento o no
                        if (comparacion.first()) {
                            do {
                                int id_comparacion = comparacion.getInt("fk_evento");

                                //si el tipo es = 1 y el usuario ha aceptado el evento, no se agrega el evento
                                if (tipo.equals("1")) {
                                    if (id_comparacion == id) {
                                        asistenciaEstado = false;
                                    }
                                }
                                //si el tipo es = 2 y el usuario no ha aceptado el evento, no se agrega el evento
                                else if (tipo.equals("2")) {
                                    if (id_comparacion == id) {
                                        asistenciaEstado = true;
                                        id_asistencia = comparacion.getInt("id_asistencia");
                                        break;
                                    } else {
                                        asistenciaEstado = false;
                                    }
                                }
                            }

                            while (comparacion.next());
                        }
                        if (asistenciaEstado) {
                            String actividad = rs.getString("nombre");
                            String descripcion = rs.getString("descripcion");
                            String fecha = obtenerFecha(rs.getString("fechaInicio"), rs.getString("fechaFin"));

                            String coordinador = rs.getString("fk_loginName");

                            evento.add(new EventoModelo("" + actividad, "" + descripcion, "" + fecha, "" + coordinador, id, id_asistencia, loginName, tipo));
                        }
                        //parámetro para agregar una List versión 1
                        //evento.add(new EventoModelo(rs.getString("nombre"), rs.getString("descripcion"), fecha, rs.getString("fk_loginName")));

                        //parámetro para recibire el query y almacenarlo en un objeto Evento
                        //columnas = new Evento(rs.getInt("id_evento"), rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("activo"), rs.getString("fk_loginName"), rs.getString("fechaInicio"), rs.getString("fechaFin"), rs.getInt("cantidadH"), rs.getInt("cantidadPR"), rs.getInt("cantidadPA"));
                    } while (rs.next());

                }
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

    private String obtenerFecha(String inicio, String fin) {
        //Declaración de variables
        String fecha="", fecha1, fecha2, hora1, hora2;

        //Separando fecha con horas¿

        //Formato de fecha
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1 = null;
        Date date2 = null;

        try {
            //Formato de fecha
            date1 = fmt.parse(inicio);
            date2 = fmt.parse(fin);
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy hh:mm");

            fecha1= fmtOut.format(date1);
            fecha2= fmtOut.format(date2);

            String[] fechaInicio = fecha1.split(" ");
            String[] fechaFin = fecha2.split(" ");

            fecha1= fechaInicio[0];
            hora1= fechaInicio[1];
            fecha2= fechaFin[0];
            hora2= fechaFin[1];

            if(fecha1.equals(fecha2)){
                fecha=fecha1 +" De " +hora1 +" A " +hora2;
            }
            else{
                fecha="Del " +fecha1 +" hasta " +fecha2 +" De " +hora1 +" A " +hora2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }



        return fecha;
    }
}
