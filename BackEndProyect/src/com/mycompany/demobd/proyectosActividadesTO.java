/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demobd;

import java.io.Serializable;

/**
 *
 * @author Brenda
 */
public class proyectosActividadesTO implements Serializable {
    private int idActividad;
    private String correoUsuario;
    private String nombreActividad;
    private int horasActividad;

    public proyectosActividadesTO() {
    }

    public proyectosActividadesTO(int idActividad, String correoUsuario, String nombreActividad, int horasActividad) {
        this.idActividad = idActividad;
        this.correoUsuario = correoUsuario;
        this.nombreActividad = nombreActividad;
        this.horasActividad = horasActividad;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public int getHorasActividad() {
        return horasActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public void setHorasActividad(int horasActividad) {
        this.horasActividad = horasActividad;
    }

}