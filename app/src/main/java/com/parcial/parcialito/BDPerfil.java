package com.parcial.parcialito;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BDPerfil extends AsyncTask<String, Void, List<PerfilModelo>> {

    private Connection conn;
    private Statement st = null;
    private ResultSet rs = null;
    private List<PerfilModelo> perfil = new ArrayList<>();

    protected List<PerfilModelo> doInBackground(String... datos) {
        //Crea el query para extraer los datos de la BD
        String sql1 = "select u.pk_loginName, u.nombre, u.apellido, c.nombre AS 'carrera', u.ciclo, u.celular, u.chroma, u.sexo from usuario u, carrera c where u.activo = 1 and u.fk_rol = 2 and u.fk_carrera = c.id_carrera";

        String[] cadena = datos[0].split(" ");
        String loginName=cadena[0];
        String tipo =cadena[1];

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
                    String eventos="";
                    String correo=rs.getString("pk_loginName");

                    //if que valida si el usuario es el que se ha logueado o no
                    if (correo.equals(loginName)){
                    }
                    else {

                        //Query que valida en la base de datos, TODOS los eventos que el usuario tiene aceptados y están activos
                        String sqlEventosAcept = "select DISTINCT a.id_asistencia, e.nombre from asistencia a, evento e, usuario u where e.id_evento = a.fk_evento and '" + correo + "' = a.fk_loginName and a.estado=1 and e.estado";
                        Statement st2 =null;
                        ResultSet comparacion = null;

                        st2 = conn.createStatement();
                        comparacion = st2.executeQuery(sqlEventosAcept);

                        //Mira en la base de datos si ya ha aceptado el evento o no
                        if (comparacion.first()) {
                            do {
                                eventos += " " + comparacion.getString("nombre");

                            }

                            while (comparacion.next());
                        }

                        String nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellido");
                        String carrera = rs.getString("ciclo") + " semestre de " + rs.getString("carrera");
                        int celular = rs.getInt("celular");
                        int chroma = rs.getInt("chroma");
                        int sexo = rs.getInt("sexo");
                        if (tipo.equals("1")) {
                            perfil.add(new PerfilModelo(correo, nombreCompleto, carrera, loginName, "Eventos en común:" + eventos, chroma, sexo, tipo/*Agregar los apartados de perfilModelo*/));
                        }
                        else if (tipo.equals("2")){
                            perfil.add(new PerfilModelo(correo, nombreCompleto, carrera, loginName, "Celular: " + celular, chroma, sexo, tipo/*Agregar los apartados de perfilModelo*/));
                        }
                        //parámetro para agregar una List versión 1
                        //evento.add(new EventoModelo(rs.getString("nombre"), rs.getString("descripcion"), fecha, rs.getString("fk_loginName")));

                        //parámetro para recibire el query y almacenarlo en un objeto Evento
                        //columnas = new Evento(rs.getInt("id_evento"), rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("activo"), rs.getString("fk_loginName"), rs.getString("fechaInicio"), rs.getString("fechaFin"), rs.getInt("cantidadH"), rs.getInt("cantidadPR"), rs.getInt("cantidadPA"));
                        st2.close();
                        comparacion.close();
                    }
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
