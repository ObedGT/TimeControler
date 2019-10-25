package com.parcial.parcialito;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BDAsistenciaAcept extends AsyncTask<Asistencia, Void, String> {
    private Connection conn;
    private Statement st = null;

    @Override
    protected String doInBackground(Asistencia... asistencias) {
        String resultado;

        String sql = "insert into asistencia (fk_evento, fk_loginName, fecha, estado)  values(" + asistencias[0].getIdEvento() + ", '" + asistencias[0].getLoginName() + "', now(),1)";

        try{
            //conexionMySql = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, userName, password);

            //st = conexionMySql.createStatement();
            Conexion conexion = new Conexion();
            conn = conexion.connect();
            st = conn.createStatement();
            st.executeUpdate(sql);

            resultado="1";
        } catch (SQLException ex) {
            resultado="0";
        }
        finally
        {
            try
            {
                if (conn!=null) {
                    st.close();
                    conn.close();
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return resultado;
    }
}
