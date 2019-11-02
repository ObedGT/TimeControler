package com.parcial.parcialito;

public class NotifyModelo {
    private int idNotify;
    private String origen, destino;
    private int tipo;
    private String descripcion;

    public NotifyModelo() {
    }

    public NotifyModelo(int idNotify, String origen, String destino, int tipo, String descripcion) {
        this.idNotify = idNotify;
        this.origen = origen;
        this.destino = destino;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getIdNotify() {
        return idNotify;
    }

    public void setIdNotify(int idNotify) {
        this.idNotify = idNotify;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
