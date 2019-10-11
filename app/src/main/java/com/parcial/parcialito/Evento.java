package com.parcial.parcialito;

public class Evento {

    private int idEvento;
    private String nombre, descripcion;
    private int activo;
    private String coordinador, fechaInicio, fechaFin;
    private int cantH, cantPR, cantPA;

    public Evento() {
    }

    public Evento(int idEvento, String nombre, String descripcion, int activo, String coordinador, String fechaInicio, String fechaFin, int cantH, int cantPR, int cantPA) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
        this.coordinador = coordinador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantH = cantH;
        this.cantPR = cantPR;
        this.cantPA = cantPA;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantH() {
        return cantH;
    }

    public void setCantH(int cantH) {
        this.cantH = cantH;
    }

    public int getCantPR() {
        return cantPR;
    }

    public void setCantPR(int cantPR) {
        this.cantPR = cantPR;
    }

    public int getCantPA() {
        return cantPA;
    }

    public void setCantPA(int cantPA) {
        this.cantPA = cantPA;
    }
}
