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
    private Usuario columnas = null;

    @Override
    protected Usuario doInBackground(Usuario... datos) {
        String sql = "select  logroH, horaAcum from usuario where pk_loginName = '"+datos[0].getLoginName()+"'";
        try{
            Connection conn;
            Conexion conexion = new Conexion();
            conn = conexion.connect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if(rs.first())
            {
                do
                {
                    columnas = new Usuario("", "","", "", 0, 0,rs.getInt("logroH"),0,"",0,0,rs.getInt("horaAcum"));
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
