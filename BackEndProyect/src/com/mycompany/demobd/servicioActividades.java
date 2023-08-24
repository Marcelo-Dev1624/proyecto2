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

            ps = super.getConection().prepareStatement("INSERT INTO `proyecto2`.`proyectosactividades` (`id`, `correo_colaborador`, `nombre_actividad`, `horas_actividad`,`id_proyecto` ) VALUES (null, ?, ?, ?,?)");
            
            
            ps.setString(1, proyectosActividadesTO.getCorreoColaborador());
            ps.setString(2, proyectosActividadesTO.getNombreActividad());
            ps.setInt(3, proyectosActividadesTO.getHorasActividad());
            ps.setInt(4, proyectosActividadesTO.getIdProyecto());
            
            
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
                
                String nombreActividad = rs.getString("nombre_actividad");
                int horasActividad = rs.getInt("horas_actividad");
                int idProyecto = rs.getInt("id_proyecto");
                
                
                ActividadesTO ActividadTO = new ActividadesTO(id, correoColaborador, nombreActividad, horasActividad, idProyecto);
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
      
       public void modificar(ActividadesTO proyectosActividadesTO) throws SQLException, Exception {

        PreparedStatement ps = null;
        Connection conn = super.getConection();
        try {

            ps = super.getConection().prepareStatement("UPDATE proyecto2.proyectosactividades SET correo_colaborador = ?,nombre_actividad = ?, horas_actividad = ?, id_proyecto = ? WHERE id = ?");
           
             ps.setString(1, proyectosActividadesTO.getCorreoColaborador());
            ps.setString(2, proyectosActividadesTO.getNombreActividad());
            ps.setInt(3, proyectosActividadesTO.getHorasActividad());
            ps.setInt(4, proyectosActividadesTO.getIdProyecto());
            ps.setInt(5, proyectosActividadesTO.getId());
            

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            super.cerrar(ps);
            super.cerrar(conn);
        }
    }
    
       
        public void eliminar(ActividadesTO proyectosActividadesTO) {
        PreparedStatement ps = null;

        try {
            Connection conn = super.getConection();
            ps = getConection().prepareStatement(
                    "DELETE FROM proyecto2.proyectosactividades WHERE id = ?");
            ps.setInt(1,proyectosActividadesTO.getId() );
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                    ps = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
