/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demobd;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.demobd.VacacionesTO;
import com.mycompany.demobd.Servicio;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author Brenda
 */
public class ServicioVacaciones extends Servicio implements ICrud<VacacionesTO> {

    public int calcularDias(LocalDate fechaInicio) {

        LocalDate today = LocalDate.now();
        long daysWorked = ChronoUnit.DAYS.between(fechaInicio, today);
        int diasVacaciones = (int) (daysWorked / 30);
        //System.out.println(diasVacaciones);
        return diasVacaciones;
    }

    public void insertarVacaciones(VacacionesTO vacacionesTO) throws SQLException, Exception {

        PreparedStatement ps = null;
        Connection conn = super.getConection();
        try {

            ps = super.getConection().prepareStatement("INSERT INTO `proyecto2`.`TICKET` (`tipo`, `motivo`, `remitente`, `receptor`, `estado`,`fechaInicial`,`fechaFinal`) VALUES (?, ?, ?, ?, ?,?,?)");
            //ps.setInt(1, permisoTO.getNumTicket());
            ps.setString(1, "Vacacion");
            ps.setString(2, "Vacacion");
            ps.setString(3, vacacionesTO.getRemitente());
            ps.setString(4, vacacionesTO.getReceptor());
            ps.setString(5, "Pendiente");
            ps.setDate(6, convertLocalDateToSqlDate(vacacionesTO.getFechaInicio()));
            ps.setDate(7, convertLocalDateToSqlDate(vacacionesTO.getFechaFinal()));
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {

            super.cerrar(ps);
            super.cerrar(conn);
        }
    }
    
     public void concederDias(long diasAConceder, String correo) throws SQLException, Exception {

        PreparedStatement ps = null;
        Connection conn = super.getConection();
        diasAConceder = diasAConceder + demeDias(correo);
        try {

            ps = super.getConection().prepareStatement("UPDATE `proyecto2`.`usuario` SET `diasConcedidos` = ? WHERE (`correo` = ?)");
            //ps.setInt(1, permisoTO.getNumTicket());
            ps.setLong(1, diasAConceder);
            ps.setString(2, correo);
           
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {

            super.cerrar(ps);
            super.cerrar(conn);
        }
    }

     public long demeDias(String correo) throws SQLException, Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = super.getConection();
        ServicioVacaciones servicioVacaciones = new ServicioVacaciones();
        long retorno = 0;
        try {
            ps = getConection().prepareStatement("SELECT * FROM USUARIO WHERE correo = ?");
            ps.setString(1, correo);
            
            rs = ps.executeQuery();
            if (rs.next()) {

                long dias = rs.getInt("diasConcedidos");
                 retorno = dias;
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.cerrar(conn);
            super.cerrar(rs);
            super.cerrar(ps);
        }
        return retorno;
       

    }
    public static java.sql.Date convertLocalDateToSqlDate(LocalDate localDate) {
        java.util.Date utilDate = java.sql.Date.valueOf(localDate);
        return new java.sql.Date(utilDate.getTime());
    }
}