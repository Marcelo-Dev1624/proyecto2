/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demobd;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Lulu
 */
public class PermisoTO implements Serializable {
    
     private int numTicket ;
    private String tipo;
    private String motivo;
    private String remitente;
    private String receptor;
    private String estado;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;

    public PermisoTO() {
    }

    public PermisoTO(int numTicket, String tipo, String motivo, String remitente, String receptor, String estado, LocalDate fechaInicial, LocalDate fechaFinal) {
        this.numTicket = numTicket;
        this.tipo = tipo;
        this.motivo = motivo;
        this.remitente = remitente;
        this.receptor = receptor;
        this.estado = estado;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    public PermisoTO(int numTicket, String tipo, String motivo, String remitente, String receptor, String estado) {
        this.numTicket = numTicket;
        this.tipo = tipo;
        this.motivo = motivo;
        this.remitente = remitente;
        this.receptor = receptor;
        this.estado = estado;
    }

    public PermisoTO(int numTicket, String tipo, String motivo, String remitente, String estado) {
        this.numTicket = numTicket;
        this.tipo = tipo;
        this.motivo = motivo;
        this.remitente = remitente;
        this.estado = estado;
    }

    public PermisoTO(int numTicket, String tipo, String motivo, String remitente, String receptor, String estado, LocalDate fechaInicial) {
        this.numTicket = numTicket;
        this.tipo = tipo;
        this.motivo = motivo;
        this.remitente = remitente;
        this.receptor = receptor;
        this.estado = estado;
        this.fechaInicial = fechaInicial;
    }
    

    public int getNumTicket() {
        return numTicket;
    }

    public void setNumTicket(int numTicket) {
        this.numTicket = numTicket;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
}
