package com.parcial.parcialito;

public class EventoModelo {
    private String actividad, descripcion, fecha, coordinador;
    private int id, id_asistencia;
    private String loginName, tipo;

    public EventoModelo() {
    }

    public EventoModelo(String actividad, String descripcion, String fecha, String coordinador, int id, int id_asistencia, String loginName, String tipo) {
        this.actividad = actividad;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.coordinador = coordinador;
        this.id = id;
        this.id_asistencia = id_asistencia;
        this.loginName = loginName;
        this.tipo = tipo;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_asistencia() {
        return id_asistencia;
    }

    public void setId_asistencia(int id_asistencia) {
        this.id_asistencia = id_asistencia;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
