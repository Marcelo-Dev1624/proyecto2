
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.mycompany.demobd.UsuarioTO;
import com.mycompany.demobd.ServicioUsuario;
import com.mycompany.demobd.ActividadesTO;
import com.mycompany.demobd.servicioActividades;
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
@ManagedBean(name = "actividadesController")
@SessionScoped
public class actividadesController implements Serializable {

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

    private ActividadesTO actividad;

    private String nombreActividad;
    private String nombreProyecto;
    private String encargadoProyecto;

    public actividadesController(String correo, String clave, String nombre, String apellidos, String estado, String rol, String manager, Date fechaInicio, ServicioUsuario su, List<UsuarioTO> usuarios, UsuarioTO usuario, boolean esNuevo, ActividadesTO actividad, String nombreActividad, String nombreProyecto, String encargadoProyecto, List<ActividadesTO> actividades, Map<String, String> nombreActividades) {
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
        this.nombreProyecto = nombreProyecto;
        this.encargadoProyecto = encargadoProyecto;
        this.actividades = actividades;
        this.nombreActividades = nombreActividades;
    }

    private ActividadesTO selectedActividad = new ActividadesTO();
    private List<ActividadesTO> actividades;

    private Map<String, String> nombreActividades;

    public actividadesController() {
    }

    @PostConstruct
    public void postConstruct() {

        nombreActividades = new HashMap<>();

        nombreActividades.put("Comer", "Comer");
        nombreActividades.put("Dormir", "Dormir");
        nombreActividades.put("Trabajar", "Trabajar");
        nombreActividades.put("Correr", "Correr");
        nombreActividades.put("Terminar", "Terminar");
        nombreActividades.put("Descansar", "Descansar");

        mostrarUsuarios();
        actividad = new ActividadesTO();
    }

    public void initActividades() {

        try {
            this.actividades = new servicioActividades().obtenerActividadesAdmin();
        } catch (Exception ex) {
            Logger.getLogger(actividadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        this.selectedActividad = new ActividadesTO();

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

        try {
            for (UsuarioTO usuario : new ServicioUsuario().demeUsuariosAdmin()) {
                if (usuario.getCorreo().equals(actividad.getCorreoColaborador())) {
                    new servicioActividades().insertar(actividad);
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

    public String getEncargadoProyecto() {
        return encargadoProyecto;
    }

    public ActividadesTO getActividad() {
        return actividad;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public ActividadesTO getSelectedActividad() {
        return selectedActividad;
    }

    public List<ActividadesTO> getActividades() {
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

    public void setEncargadoProyecto(String encargadoProyecto) {
        this.encargadoProyecto = encargadoProyecto;
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

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public void setActividad(ActividadesTO actividad) {
        this.actividad = actividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public void setSelectedActividad(ActividadesTO selectedActividad) {
        this.selectedActividad = selectedActividad;
    }

    public void setActividades(List<ActividadesTO> actividades) {
        this.actividades = actividades;
    }

    public void setNombreActividades(Map<String, String> nombreActividades) {
        this.nombreActividades = nombreActividades;
    }

}
