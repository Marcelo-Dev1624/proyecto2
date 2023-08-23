/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demobd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brenda
 */
public class servicioActividades extends Servicio implements ICrud<ActividadesTO>{
        
  public void insertar(ActividadesTO proyectosActividadesTO) throws SQLException, Exception {

        PreparedStatement ps = null;
        Connection conn = super.getConection();
        try {

            ps = super.getConection().prepareStatement("INSERT INTO `proyecto2`.`proyectosactividades` (`id`, `correo_colaborador`, `id_proyecto`, `nombre_actividad`, `horas_actividad` ) VALUES (null, ?, ?, ?,?)");
            
            
            ps.setString(1, proyectosActividadesTO.getCorreoColaborador());
            ps.setInt(2, proyectosActividadesTO.getIdProyecto());
            ps.setString(3, proyectosActividadesTO.getNombreActividad());
            ps.setInt(4, proyectosActividadesTO.getHorasActividad());
            
            
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {

            super.cerrar(ps);
            super.cerrar(conn);
        }
    }
  
 

   
     
      public List<ActividadesTO> obtenerActividadesAdmin() throws SQLException, Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = super.getConection();

        List<ActividadesTO> retorno = new ArrayList<ActividadesTO>();
        try {
            ps = getConection().prepareStatement("SELECT * FROM `proyectosactividades`");
           
            
            rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String correoColaborador = rs.getString("correo_colaborador");
                int idProyecto = rs.getInt("id_proyecto");
                String nombreActividad = rs.getString("nombre_actividad");
                int horasActividad = rs.getInt("horas_actividad");
                
                
                
                ActividadesTO ActividadTO = new ActividadesTO(id,idProyecto, correoColaborador, nombreActividad, horasActividad);
                retorno.add(ActividadTO);
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
    
}
