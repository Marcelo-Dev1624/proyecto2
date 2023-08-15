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
import java.time.LocalDate;
/**
 *
 * @author Brenda
 */
public class ServicioVacaciones extends Servicio implements ICrud<VacacionesTO>{
    
    
  public int calcularDias(LocalDate fechaInicio) {
     
        LocalDate today = LocalDate.now();
        long daysWorked = ChronoUnit.DAYS.between(fechaInicio, today);
         int diasVacaciones = (int) (daysWorked / 30);
        //System.out.println(diasVacaciones);
        return diasVacaciones;
    }
    }
    

