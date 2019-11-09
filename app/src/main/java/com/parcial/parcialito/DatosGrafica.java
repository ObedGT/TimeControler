package com.parcial.parcialito;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatosGrafica extends AsyncTask<Usuario, Void, Usuario> {
    Connection conexionMySql = null;
    private Statement st = null;
    private ResultSet rs = null;
    private Statement st1 = null;
    private ResultSet rs1 = null;
    private Usuario columnas = null;

    @Override
    protected Usuario doInBackground(Usuario... datos) {
        String sql = "select  logroH from usuario where pk_loginName = '"+datos[0].getLoginName()+"'";
        String sql2 = "select horas from tiempo where fk_loginName = '"+datos[0].getLoginName()+"'";
        try{
            Connection conn;
            Conexion conexion = new Conexion();
            conn = conexion.connect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            st1 = conn.createStatement();
            rs1 = st1.executeQuery(sql2);
            int horasAcum=0;

            if(rs1.first())
            {
                do
                {
                    horasAcum+=rs1.getInt("horas");
                }while(rs1.next());
            }

            if(rs.first())
            {
                do
                {
                    columnas = new Usuario("", "","", "", 0, 0,rs.getInt("logroH"),0,"",0,0,horasAcum);
                }while(rs.next());
            }
        } catch (SQLException ex) {
        }
        finally
        {
            try
            {
                if (conexionMySql!=null) {
                    st.close();
                    rs.close();
                    conexionMySql.close();
                }
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return columnas;
    }

}
