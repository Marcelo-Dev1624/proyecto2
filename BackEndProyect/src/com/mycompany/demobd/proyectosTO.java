/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demobd;

/**
 *
 * @author Brenda
 */
public class proyectosTO {
    
    private int idProyecto;
    private String nombreProyecto;
    private String encargadoProyecto;

    public proyectosTO() {
    }

    public proyectosTO(int idProyecto, String nombreProyecto, String encargadoProyecto) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.encargadoProyecto = encargadoProyecto;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public String getEncargadoProyecto() {
        return encargadoProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public void setEncargadoProyecto(String encargadoProyecto) {
        this.encargadoProyecto = encargadoProyecto;
    }
    
    
}
