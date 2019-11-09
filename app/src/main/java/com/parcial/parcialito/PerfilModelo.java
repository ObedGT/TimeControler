package com.parcial.parcialito;

public class PerfilModelo {
    private String correo, nombre, carrera, loginName, eventosComun;
    private int chroma, sexo;
    private String tipo;

    public PerfilModelo() {
    }

    public PerfilModelo(String correo, String nombre,  String carrera, String loginName, String eventosComun, int chroma, int sexo, String tipo) {
        this.correo= correo;
        this.nombre = nombre;
        this.carrera = carrera;
        this.loginName = loginName;
        this.eventosComun = eventosComun;
        this.chroma = chroma;
        this.sexo = sexo;
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEventosComun() {
        return eventosComun;
    }

    public void setEventosComun(String eventosComun) {
        this.eventosComun = eventosComun;
    }

    public int getChroma() {
        return chroma;
    }

    public void setChroma(int chroma) {
        this.chroma = chroma;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
