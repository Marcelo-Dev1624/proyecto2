/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demobd;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Brenda
 */
public class VacacionesTO implements Serializable {
    private int numVacacion;
    private Date fechaInicio;
    private Date fechaActual;
    private String remitente;
    private int diasVacaciones;
    
    public VacacionesTO(){

}

    public VacacionesTO(int numVacacion, Date fechaInicio, Date fechaActual, String remitente, int diasVacaciones) {
        this.numVacacion = numVacacion;
        this.fechaInicio = fechaInicio;
        this.fechaActual = fechaActual;
        this.remitente = remitente;
        this.diasVacaciones = diasVacaciones;
    }

    public int getNumVacacion() {
        return numVacacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public String getRemitente() {
        return remitente;
    }

    public int getDiasVacaciones() {
        return diasVacaciones;
    }

    public void setNumVacacion(int numVacacion) {
        this.numVacacion = numVacacion;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public void setDiasVacaciones(int diasVacaciones) {
        this.diasVacaciones = diasVacaciones;
    }
    
    
    
}



