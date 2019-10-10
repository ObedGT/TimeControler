package com.parcial.parcialito;

public class EventoModelo {
    private String actividad, descripcion, fecha, coordinador;

    public EventoModelo() {
    }

    public EventoModelo(String actividad, String descripcion, String fecha, String coordinador) {
        this.actividad = actividad;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.coordinador = coordinador;
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
}
