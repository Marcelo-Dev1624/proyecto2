
import com.mycompany.demobd.PermisoTO;
import com.mycompany.demobd.ServicioPermiso;
import com.mycompany.demobd.ServicioUsuario;
import com.mycompany.demobd.UsuarioTO;
import com.mycompany.demobd.VacacionesTO;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brenda
 */

@ManagedBean(name = "vacacionesController")
@SessionScoped
public class VacacionesController implements Serializable{
    
    
    private String correo;
    private String clave;
    private String nombre;
    private String apellidos;
    private String estado;
    private String rol;
    private String manager;
    private Date fechaInicio;
    private List<UsuarioTO> usuarios;
    private UsuarioTO usuario;
    private ServicioUsuario su;
    private boolean esNuevo = false;
    private UsuarioTO selectedUsuario = new UsuarioTO();
    
    private int numSolicitud;
    private Date fk_fechaInicio;
    private Date fechaSolicitud;
    private String fk_correo;
    private int diasVacacionesDisponibles;
    private List<VacacionesTO> vacaciones;
    private Object selectedVacacion;


   
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setUsuarios(List<UsuarioTO> usuarios) {
        this.usuarios = usuarios;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

    public void setSu(ServicioUsuario su) {
        this.su = su;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public void setSelectedUsuario(UsuarioTO selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public void setNumSolicitud(int numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public void setFk_fechaInicio(Date fk_fechaInicio) {
        this.fk_fechaInicio = fk_fechaInicio;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setFk_correo(String fk_correo) {
        this.fk_correo = fk_correo;
    }

    public void setDiasVacacionesDisponibles(int diasVacacionesDisponibles) {
        this.diasVacacionesDisponibles = diasVacacionesDisponibles;
    }

    public void setVacaciones(List<VacacionesTO> vacaciones) {
        this.vacaciones = vacaciones;
    }

    public void setSelectedVacacion(Object selectedVacacion) {
        this.selectedVacacion = selectedVacacion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEstado() {
        return estado;
    }

    public String getRol() {
        return rol;
    }

    public String getManager() {
        return manager;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public List<UsuarioTO> getUsuarios() {
        return usuarios;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    public ServicioUsuario getSu() {
        return su;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public UsuarioTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public int getNumSolicitud() {
        return numSolicitud;
    }

    public Date getFk_fechaInicio() {
        return fk_fechaInicio;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public String getFk_correo() {
        return fk_correo;
    }

    public int getDiasVacacionesDisponibles() {
        return diasVacacionesDisponibles;
    }

    public List<VacacionesTO> getVacaciones() {
        return vacaciones;
    }

    public Object getSelectedVacacion() {
        return selectedVacacion;
    }
    
}
