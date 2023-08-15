
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.mycompany.demobd.UsuarioTO;
import com.mycompany.demobd.ServicioUsuario;
import com.mycompany.demobd.proyectosActividadesTO;
import com.mycompany.demobd.servicioProyectosActividades;
import java.sql.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
@ManagedBean(name = "proyectosController")
@SessionScoped
public class proyectosController implements Serializable {

    private String correo;
    private String clave;
    private String nombre;
    private String apellidos;
    private String estado;
    private String rol;
    private String manager;
    private Date fechaInicio;

    private ServicioUsuario su;
    private List<UsuarioTO> usuarios;
    private UsuarioTO usuario;

    private UsuarioTO selectedUsuario = new UsuarioTO();
    private boolean esNuevo;

    private proyectosActividadesTO actividad;

    private String nombreActividad;
    private proyectosActividadesTO selectedActividad = new proyectosActividadesTO();
    private List<proyectosActividadesTO> actividades;

    private Map<String, String> nombreActividades;

    public proyectosController() {
    }

    public proyectosController(String correo, String clave, String nombre, String apellidos, String estado, String rol, String manager, Date fechaInicio, ServicioUsuario su, List<UsuarioTO> usuarios, UsuarioTO usuario, boolean esNuevo, proyectosActividadesTO actividad, String nombreActividad, List<proyectosActividadesTO> actividades, Map<String, String> nombreActividades) {
        this.correo = correo;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.estado = estado;
        this.rol = rol;
        this.manager = manager;
        this.fechaInicio = fechaInicio;
        this.su = su;
        this.usuarios = usuarios;
        this.usuario = usuario;
        this.esNuevo = esNuevo;
        this.actividad = actividad;
        this.nombreActividad = nombreActividad;
        this.actividades = actividades;
        this.nombreActividades = nombreActividades;
    }

    public void initActividades() {
        try {
            for (UsuarioTO usuario : new ServicioUsuario().ObtenerYMostrarUsuarios()) {
                if (usuario.getCorreo().equals(actividad.getCorreoUsuario())) {
                    this.actividades = new servicioProyectosActividades().obtenerActividades(actividad.getCorreoUsuario());
                }
            }

        } catch (Exception e) {
        }

    }
    /*
    public void insertar() {

        System.out.println("ESTOY EN EL INGRESAR");
        System.out.println("EL VALOR DE USUARIO ES:" + this.getCorreo());
        System.out.println("EL VALOR DE CLAVE ES:" + this.getClave());
        boolean continuar = true;
        if (this.getCorreo() == null || this.getCorreo().equals("")) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos inválidos", "El usuario o la clave no son correctas"));
            continuar = false;
        }
        if (this.getClave() == null || this.getClave().equals("")) {

            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos inválidos", "El usuario o la clave no son correctas"));
            continuar = false;
        }
        if (continuar) {
            validacionUsuario();

        }

    }
    
    
    public UsuarioTO validacionUsuario() {

        UsuarioTO retorne = new UsuarioTO(this.correo, this.clave, this.rol, this.manager, this.fechaInicio);

        try {

            ServicioUsuario su = new ServicioUsuario();
            retorne = su.demeUsuario(correo, clave, rol, manager, fechaInicio);
            usuario = retorne;

            if (retorne != null) {
                mostrarUsuarios();
                this.redireccionar("/faces/bienvenida.xhtml");

            } else {

                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Existe en la base de datos", "No Existe en la base de datos"));

            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "ERROR"));

            e.printStackTrace();
        }
        return retorne;

    }
*/
    @PostConstruct
    public void postConstruct() {

        nombreActividades = new HashMap<>();

        nombreActividades.put("Comer", "Comer");
        nombreActividades.put("Dormir", "Dormir");
        nombreActividades.put("Trabajar", "Trabajar");
        nombreActividades.put("Correr", "Correro");
        nombreActividades.put("Cagar", "Cagar");

        mostrarUsuarios();
        actividad = new proyectosActividadesTO();
    }

    public void mostrarUsuarios() {
        try {
            ServicioUsuario su = new ServicioUsuario();

            this.usuarios = new ServicioUsuario().demeUsuariosAdmin();

        } catch (Exception e) {
        }

    }

    public void logoutSalir() {
        this.redireccionar("/faces/index.xhtml");
        this.usuario = null;
    }

    public void openNew() {
        this.esNuevo = true;
        this.selectedUsuario = new UsuarioTO();

        this.selectedActividad = new proyectosActividadesTO();

    }

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }

    public void guardarActividad() {
        if (actividad.getNombreActividad() != null && "".equals(actividad.getNombreActividad())) {
            return;
        }

        try {
            for (UsuarioTO usuario : new ServicioUsuario().demeUsuariosAdmin()) {
                if (usuario.getCorreo().equals(actividad.getCorreoUsuario())) {
                    new servicioProyectosActividades().insertar(actividad);
                }
            }

        } catch (Exception e) {
        }

        initActividades();

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

    public ServicioUsuario getSu() {
        return su;
    }

    public List<UsuarioTO> getUsuarios() {
        return usuarios;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    public UsuarioTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public proyectosActividadesTO getActividad() {
        return actividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public proyectosActividadesTO getSelectedActividad() {
        return selectedActividad;
    }

    public List<proyectosActividadesTO> getActividades() {
        return actividades;
    }

    public Map<String, String> getNombreActividades() {
        return nombreActividades;
    }

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

    public void setSu(ServicioUsuario su) {
        this.su = su;
    }

    public void setUsuarios(List<UsuarioTO> usuarios) {
        this.usuarios = usuarios;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

    public void setSelectedUsuario(UsuarioTO selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public void setActividad(proyectosActividadesTO actividad) {
        this.actividad = actividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public void setSelectedActividad(proyectosActividadesTO selectedActividad) {
        this.selectedActividad = selectedActividad;
    }

    public void setActividades(List<proyectosActividadesTO> actividades) {
        this.actividades = actividades;
    }

    public void setNombreActividades(Map<String, String> nombreActividades) {
        this.nombreActividades = nombreActividades;
    }

}
