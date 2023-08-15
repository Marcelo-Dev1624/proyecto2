/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demobd;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Brenda
 */
public class VacacionesTO extends PermisoTO implements Serializable {

    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private int diasVacaciones;

    public VacacionesTO() {

    }

    public VacacionesTO(LocalDate fechaInicio, LocalDate fechaFinal, int diasVacaciones, int numTicket, String tipo, String motivo, String remitente, String receptor, String estado) {
        super(numTicket, tipo, motivo, remitente, receptor, estado);
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.diasVacaciones = diasVacaciones;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getDiasVacaciones() {
        return diasVacaciones;
    }

    public void setDiasVacaciones(int diasVacaciones) {
        this.diasVacaciones = diasVacaciones;
    }

    
}
