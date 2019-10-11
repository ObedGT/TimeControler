package com.parcial.parcialito;

import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends AsyncTask<Usuario, Void, Usuario> {
    //Connection conexionMySql = null;
    private Connection conn;
    private Statement st = null;
    private ResultSet rs = null;
    private Usuario columnas = null;

    @Override
    protected Usuario doInBackground(Usuario... datos) {
        String sql = "select pk_loginName, password, fk_rol, activo from usuario where pk_loginName = '"+datos[0].getLoginName()+"' and password = '"+datos[0].getPassword() + "'";
        /*String host = "192.168.1.3";
        String port = "3306";
        String dbName = "timecontroler";
        String userName = "root";
        String password = "admon";

         */
        try{
            //conexionMySql = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, userName, password);

            //st = conexionMySql.createStatement();
            Conexion conexion = new Conexion();
            conn = conexion.connect();
            st = conn.createStatement();


            rs = st.executeQuery(sql);
            if(rs.first())
            {
                do
                {
                    columnas = new Usuario(rs.getString("pk_loginName"), rs.getString("password"),"", "", rs.getInt("fk_rol"), rs.getInt("activo"),0,0,"",0,0,0);
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
        return columnas;
    }

}