/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demobd;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author laboratorio
 */
public class UsuarioTO implements Serializable {

    private String correo;
    private String clave;
    private String nombre;
    private String apellido;
    private String estado;
    private String rol;
    private String manager;
    private LocalDate fechaInicio;
    private int diasVacaciones;

    

    public UsuarioTO() {
    }

    public UsuarioTO(String correo, String clave, String rol, String manager) {
        this.correo = correo;
        this.clave = clave;
        this.rol = rol;
        this.manager = manager;
       
    }

    public UsuarioTO(String correo, String clave, String nombre, String apellido, String rol, String manager, LocalDate fechaInicio) {
        this.correo = correo;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.manager = manager;
        this.fechaInicio = fechaInicio;
    }

    

    public UsuarioTO(String correo, String nombre, String apellido, String rol, String manager, LocalDate fechaInicio, int diasVacaciones) {
        this.correo = correo;

        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.manager = manager;
        this.fechaInicio = fechaInicio;
        this.diasVacaciones = diasVacaciones;
    }


    /*public UsuarioTO(String correo, String nombre, String apellido, String estado) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
    }*/
    public UsuarioTO(String correo, String clave, String nombre, String apellido, String rol, String manager, LocalDate fechaInicio,int diasVacaciones) {
        this.correo = correo;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;

        this.rol = rol;
        this.manager = manager;
        this.fechaInicio = fechaInicio;
        this.diasVacaciones = diasVacaciones;
    }

    public UsuarioTO(String correo, String clave, String rol, String manager, int diasVacaciones) {
        this.correo = correo;
        this.clave = clave;
        this.rol = rol;
        this.manager = manager;
        this.diasVacaciones = diasVacaciones;
    }

   

    public UsuarioTO(String correo, String nombre, String apellido, String rol, String manager) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.manager = manager;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getDiasVacaiones() {
        return diasVacaciones;
    }

    public void setDiasVacaiones(int diasVacaiones) {
        this.diasVacaciones = diasVacaiones;
    }

}
