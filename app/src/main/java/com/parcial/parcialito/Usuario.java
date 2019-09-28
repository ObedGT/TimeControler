package com.parcial.parcialito;

public class Usuario {
    //Atributos de la clase
    private String loginName;
    private String password;
    private String nombre;
    private String apellido;
    private int idRol;
    private int activo;
    private int logroH;
    private int fk_carrera;
    private String ciclo;
    private int celular;
    private int chroma;
    private int horaAcum;


    //Constructor que recibe todos los parametros de usuario


    public Usuario(String loginName, String password, String nombre, String apellido, int idRol, int activo, int logroH, int fk_carrera, String ciclo, int celular, int chroma, int horaAcum) {
        this.loginName = loginName;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idRol = idRol;
        this.activo = activo;
        this.logroH = logroH;
        this.fk_carrera = fk_carrera;
        this.ciclo = ciclo;
        this.celular = celular;
        this.chroma = chroma;
        this.horaAcum = horaAcum;
    }

    //Constructor sin parametros
    public Usuario() {

    }

    //Metodos gets y sets
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getLogroH() {
        return logroH;
    }

    public void setLogroH(int logroH) {
        this.logroH = logroH;
    }

    public int getFk_carrera() {
        return fk_carrera;
    }

    public void setFk_carrera(int fk_carrera) {
        this.fk_carrera = fk_carrera;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getChroma() {
        return chroma;
    }

    public void setChroma(int chroma) {
        this.chroma = chroma;
    }

    public int getHoraAcum() {
        return horaAcum;
    }

    public void setHoraAcum(int horaAcum) {
        this.horaAcum = horaAcum;
    }
}
