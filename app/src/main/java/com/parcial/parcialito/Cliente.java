package com.parcial.parcialito;

public class Cliente {
    //Atributos de la clase
    private String nombre;
    private String apellido;
    private String nickName;
    private String password;
    private String correo;

    //Constructor que recibe todos los parametros de usuario
    public Cliente(String nombre, String apellido, String nickName, String Password, String correo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickName= nickName;
        this.password = password;
        this.correo = correo;
    }

    //Constructor sin parametros
    public Cliente(String pk_login_name, String password, String nombre, String apellido, int fk_id_rol, int activo) {

    }

    //Metodos gets y sets

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getLoginName() {
        return nickName;
    }

    public void setLoginName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void getCorreo(String correo) {
        this.correo = correo;
    }

}
