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
public class servicioProyectos extends Servicio implements ICrud<proyectosTO> {

    public void insertar(proyectosTO proyectosTO) throws SQLException, Exception {

        PreparedStatement ps = null;
        Connection conn = super.getConection();
        try {

            ps = super.getConection().prepareStatement("INSERT INTO `proyecto2`.`proyectos` (`idProyecto`, `nombreProyecto`, `encargadoProyecto`) VALUES (null, ?, ?)");
            
            ps.setString(1, proyectosTO.getNombreProyecto());
            ps.setString(2, proyectosTO.getEncargadoProyecto());
        
            ps.executeUpdate();
        } catch (Exception e) {

        } finally {

            super.cerrar(ps);
            super.cerrar(conn);
        }
    }

    public List<proyectosTO> obtenerProyectos() throws SQLException, Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = super.getConection();

        List<proyectosTO> retorno = new ArrayList<proyectosTO>();
        try {
            ps = getConection().prepareStatement("SELECT * FROM proyectos");

            rs = ps.executeQuery();
            while (rs.next()) {

                int idProyecto = rs.getInt("idProyecto");
                String nombreProyecto = rs.getString("nombreProyecto");
                String encargadoProyecto = rs.getString("encargadoProyecto");

                proyectosTO proyectos_TO = new proyectosTO(idProyecto, nombreProyecto, encargadoProyecto);
                retorno.add(proyectos_TO);
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
    
    
     public void modificar(proyectosTO proyectosTO) throws SQLException, Exception {

        PreparedStatement ps = null;
        Connection conn = super.getConection();
        try {

            ps = super.getConection().prepareStatement("UPDATE proyecto2.proyectos SET nombreProyecto = ?,encargadoProyecto = ? WHERE idProyecto = ?");
           
            
            ps.setString(1, proyectosTO.getNombreProyecto());
            ps.setString(2, proyectosTO.getEncargadoProyecto());
           ps.setInt(3, proyectosTO.getIdProyecto());
            
            

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            super.cerrar(ps);
            super.cerrar(conn);
        }
    }

    public void eliminar(proyectosTO proyectosTO) {
        PreparedStatement ps = null;

        try {
            Connection conn = super.getConection();
            ps = getConection().prepareStatement(
                    "DELETE FROM proyecto2.proyectos WHERE idProyecto = ?");
            ps.setInt(1, proyectosTO.getIdProyecto());
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
