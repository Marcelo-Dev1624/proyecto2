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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Brenda
 */
public class servicioProyectosActividades extends Servicio implements ICrud<proyectosActividadesTO>{
        
  public void insertar(proyectosActividadesTO proyectosActividadesTO) throws SQLException, Exception {

        PreparedStatement ps = null;
        Connection conn = super.getConection();
        try {

            ps = super.getConection().prepareStatement("INSERT INTO `proyecto2`.`actividades` (`idActividad`, `correo_colaborador`, `nombreActividad`, `horasActividad`) VALUES (null, ?, ?, ?)");
            
            
            ps.setString(1, proyectosActividadesTO.getCorreoUsuario());
            ps.setString(2, proyectosActividadesTO.getNombreActividad());
            ps.setInt(3, proyectosActividadesTO.getHorasActividad());
            
            ps.executeUpdate();

        } catch (Exception e) {

        } finally {

            super.cerrar(ps);
            super.cerrar(conn);
        }
    }
     
      public List<proyectosActividadesTO> obtenerActividades(String correoColaboradorInput) throws SQLException, Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = super.getConection();

        List<proyectosActividadesTO> retorno = new ArrayList<proyectosActividadesTO>();
        try {
            ps = getConection().prepareStatement("SELECT * FROM actividades where correo_colaborador = ?");
            ps.setString(1, correoColaboradorInput);
            
            rs = ps.executeQuery();
            while (rs.next()) {

                int idActividad = rs.getInt("idActividad");
                String correoColaborador = rs.getString("correo_colaborador");
                String nombreActividad = rs.getString("nombreActividad");
                int horasActividad = rs.getInt("horasActividad");
                
                proyectosActividadesTO ActividadTO = new proyectosActividadesTO(idActividad, correoColaborador, nombreActividad, horasActividad);
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
