package com.parcial.parcialito;

public class Asistencia {
    private int idAsistencia, idEvento;
    private String loginName;
    private int estado;

    public Asistencia() {
    }

    public Asistencia(int idAsistencia, int idEvento, String loginName, int estado) {
        this.idAsistencia = idAsistencia;
        this.idEvento = idEvento;
        this.loginName = loginName;
        this.estado = estado;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
