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
public class ActividadesTO implements Serializable {
    private int id;
    private String correoColaborador;
    private int idProyecto;
    private String nombreActividad;
    private int horasActividad;

    public ActividadesTO() {
    }

    public ActividadesTO(int id, String correoColaborador, int idProyecto, String nombreActividad, int horasActividad) {
        this.id = id;
        this.correoColaborador = correoColaborador;
        this.idProyecto = idProyecto;
        this.nombreActividad = nombreActividad;
        this.horasActividad = horasActividad;
    }

    ActividadesTO(int id, int idProyecto, String correoColaborador, String nombreActividad, int horasActividad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

  

   

    

    public int getId() {
        return id;
    }

    public String getCorreoColaborador() {
        return correoColaborador;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

   
   

    public String getNombreActividad() {
        return nombreActividad;
    }

    public int getHorasActividad() {
        return horasActividad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCorreoColaborador(String correoColaborador) {
        this.correoColaborador = correoColaborador;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

   

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    
    public void setHorasActividad(int horasActividad) {
        this.horasActividad = horasActividad;
    }
    
}