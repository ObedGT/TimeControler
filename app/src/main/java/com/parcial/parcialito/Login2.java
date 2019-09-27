package com.parcial.parcialito;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login2 extends AsyncTask<Usuario, Void, String> {
    Connection conexionMySql = null;
    private Statement st = null;
    private ResultSet rs = null;

    @Override
    protected String doInBackground(Usuario... datos) {
        String resultado = "";
        String sql = ("Insert into usuario values('"+datos[0].getLoginName()+"','"+datos[0].getPassword()+"','"+datos[0].getNombre()+"','"+datos[0].getApellido()+"','"+datos[0].getIdRol()+"','"+datos[0].getActivo()+"')");
        String host = "192.168.43.120";
        String port = "3306";
        String dbName = "uvgmoviles";
        String userName = "root";
        String password = "admon";
        try{
            conexionMySql = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, userName, password);
            st = conexionMySql.createStatement();
            st.executeUpdate(sql);
            resultado="1";
        } catch (SQLException ex) {
            resultado="0";
        }
        finally
        {
            try
            {
                st.close();
                rs.close();
                conexionMySql.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return resultado;
    }
}

