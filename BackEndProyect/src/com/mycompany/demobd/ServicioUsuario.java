/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demobd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.demobd.ServicioVacaciones;
import static com.mycompany.demobd.ServicioVacaciones.convertLocalDateToSqlDate;

/**
 *
 * @author Lulu
 */
public class ServicioUsuario extends Servicio implements ICrud<UsuarioTO> {
public String cUser; 
    public void insertar(UsuarioTO usuarioTO) throws SQLException, Exception {

        PreparedStatement ps = null;
        Connection conn = super.getConection();
        try {

            ps = super.getConection().prepareStatement("INSERT INTO usuario VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, usuarioTO.getCorreo());
            ps.setString(2, usuarioTO.getClave());
            ps.setString(3, usuarioTO.getNombre());
            ps.setString(4, usuarioTO.getApellido());
            ps.setString(5, "activo");
            if (usuarioTO.getRol() == null) {
                ps.setString(6, "Colaborador");
            } else {
                ps.setString(6, usuarioTO.getRol());
            }
            ps.setString(7, usuarioTO.getManager());
            ps.setDate(8, convertLocalDateToSqlDate(LocalDate.now()));
            ps.setInt(9, 0);

            System.out.println(usuarioTO.getFechaInicio());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        } finally {

            super.cerrar(ps);
            super.cerrar(conn);
        }
    }

    public void modificar(UsuarioTO usuarioTO) throws SQLException, Exception {

        PreparedStatement ps = null;
        Connection conn = super.getConection();
        try {

            ps = super.getConection().prepareStatement("UPDATE proyecto2.usuario SET nombre = ?,clave = ?, apellido = ?, rol = ? WHERE correo = ?");
            ps.setString(1, usuarioTO.getNombre());
            ps.setString(2, usuarioTO.getClave());
            ps.setString(3, usuarioTO.getApellido());
            ps.setString(4, usuarioTO.getRol());
            ps.setString(5, usuarioTO.getCorreo());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            super.cerrar(ps);
            super.cerrar(conn);
        }
    }

    public void eliminar(UsuarioTO usuarioTO) {
        PreparedStatement ps = null;

        try {
            Connection conn = super.getConection();
            ps = getConection().prepareStatement(
                    "UPDATE proyecto2.usuario SET estado = 'inactivo' WHERE correo = ?");
            ps.setString(1, usuarioTO.getCorreo());
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

    /**
     *
     * @return @throws Exception
     */
    public List<UsuarioTO> demeUsuariosColaborador(String man) throws SQLException, Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = super.getConection();

        List<UsuarioTO> retorno = new ArrayList<UsuarioTO>();
        try {
            ps = getConection().prepareStatement("(SELECT correo,nombre,apellido,rol,manager,fechaInicio FROM USUARIO WHERE ESTADO='ACTIVO' and manager = ?)  UNION\n"
                    + " (SELECT correo,nombre,apellido,rol,manager,fechaInicio FROM USUARIO WHERE Correo = ?)");
            ps.setString(1, man);
            ps.setString(2, man);
            rs = ps.executeQuery();
            while (rs.next()) {

                String correo = rs.getString("correo");

                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String rol = rs.getString("rol");
                String manager = rs.getString("manager");

                UsuarioTO usuarioTO = new UsuarioTO(correo, nombre, apellido, rol, manager);
                retorno.add(usuarioTO);
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

    public List<UsuarioTO> demeUsuariosManager(String man) throws SQLException, Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = super.getConection();
        ServicioVacaciones servicioVacaciones = new ServicioVacaciones();

        List<UsuarioTO> retorno = new ArrayList<UsuarioTO>();
        try {
            ps = getConection().prepareStatement("SELECT correo,nombre,apellido,rol,manager,fechaInicio FROM USUARIO WHERE ESTADO='ACTIVO' and manager = ?");
            ps.setString(1, man);

            rs = ps.executeQuery();
            while (rs.next()) {

                String correo = rs.getString("correo");

                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String rol = rs.getString("rol");
                String manager = rs.getString("manager");
                LocalDate fechaInicio = rs.getDate("fechaInicio").toLocalDate();
                int diasVacaciones = servicioVacaciones.calcularDias(fechaInicio);
                UsuarioTO usuarioTO = new UsuarioTO(correo, nombre, apellido, rol, manager, fechaInicio, diasVacaciones);
                retorno.add(usuarioTO);
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

    public List<UsuarioTO> demeUsuariosAdmin() throws SQLException, Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = super.getConection();
        ServicioVacaciones servicioVacaciones = new ServicioVacaciones();

        List<UsuarioTO> retorno = new ArrayList<UsuarioTO>();
        try {
            ps = getConection().prepareStatement("SELECT correo,clave,nombre,apellido,rol,manager,fechaInicio FROM USUARIO WHERE ESTADO='ACTIVO'");

            rs = ps.executeQuery();
            while (rs.next()) {

                String correo = rs.getString("correo");
                String clave = rs.getString("clave");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String rol = rs.getString("rol");
                String manager = rs.getString("manager");
                LocalDate fechaInicio = rs.getDate("fechaInicio").toLocalDate();
                int diasVacaciones = servicioVacaciones.calcularDias(fechaInicio);

                UsuarioTO usuarioTO = new UsuarioTO(correo, clave, nombre, apellido, rol, manager, fechaInicio, diasVacaciones);
                retorno.add(usuarioTO);
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

    public List<UsuarioTO> ObtenerYMostrarUsuarios() throws SQLException, Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = super.getConection();

        List<UsuarioTO> retorno = new ArrayList<UsuarioTO>();
        try {
            ps = getConection().prepareStatement("SELECT correo,clave,nombre,apellido,rol,manager,fechaInicio FROM USUARIO WHERE ESTADO='ACTIVO'");

            rs = ps.executeQuery();
            while (rs.next()) {

                String correo = rs.getString("correo");
                String clave = rs.getString("clave");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String rol = rs.getString("rol");
                String manager = rs.getString("manager");
                LocalDate fechaInicio = rs.getDate("fechaInicio").toLocalDate();
                UsuarioTO usuarioTO = new UsuarioTO(correo, clave, nombre, apellido, rol, manager, fechaInicio);
                retorno.add(usuarioTO);
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

   
    public UsuarioTO demeUsuario(String correo, String clave) throws SQLException, Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = super.getConection();
        ServicioVacaciones servicioVacaciones = new ServicioVacaciones();
        UsuarioTO retorno = null;
        try {
            ps = getConection().prepareStatement("SELECT * FROM USUARIO WHERE correo = ? and clave = ?");
            ps.setString(1, correo);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            if (rs.next()) {

                correo = rs.getString("correo");
                clave = rs.getString("clave");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String rol = rs.getString("rol");
                String manager = rs.getString("manager");
                LocalDate fechaInicio = rs.getDate("fechaInicio").toLocalDate();
                long diasConcedidos = rs.getInt("diasConcedidos");
                long diasVacaciones = servicioVacaciones.calcularDias(fechaInicio) - diasConcedidos;
                
                retorno = new UsuarioTO(correo, clave, nombre, apellido, rol, manager, fechaInicio, diasVacaciones, diasConcedidos);
                System.out.println(retorno);
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

    public void modificarPerfil(UsuarioTO usuarioTO) throws SQLException, Exception {

        PreparedStatement ps = null;
        Connection conn = super.getConection();
        try {

            ps = super.getConection().prepareStatement("UPDATE proyecto2.usuario SET nombre = ?, apellido = ? WHERE correo = ?");
            ps.setString(1, usuarioTO.getNombre());
            ps.setString(2, usuarioTO.getApellido());
            ps.setString(3, usuarioTO.getCorreo());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            super.cerrar(ps);
            super.cerrar(conn);
        }
    }
    
    public UsuarioTO datosUsuario(String nombre, String apellido, String correo, String rol, String manager) throws SQLException, Exception {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = super.getConection();

        UsuarioTO dUsuario = null;


        try {
            ps = getConection().prepareStatement("SELECT correo,nombre,apellido,rol,manager FROM USUARIO WHERE correo = ?");
            ps.setString(1, cUser);
            rs = ps.executeQuery();

            if (rs.next()) {
            correo = rs.getString("correo");
            nombre = rs.getString("nombre");
            apellido = rs.getString("apellido");
            rol = rs.getString("rol");
            manager = rs.getString("manager");
            dUsuario = new UsuarioTO(correo, nombre, apellido, rol, manager);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.cerrar(conn);
            super.cerrar(rs);
            super.cerrar(ps);
        }
        return dUsuario;

    }
}
