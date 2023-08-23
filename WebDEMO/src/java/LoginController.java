
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import com.mycompany.demobd.PermisoTO;
import com.mycompany.demobd.ServicioPermiso;
import com.mycompany.demobd.ServicioUsuario;
import com.mycompany.demobd.UsuarioTO;
import com.mycompany.demobd.VacacionesTO;
import com.mycompany.demobd.ServicioVacaciones;
import com.sun.corba.se.impl.naming.pcosnaming.NameService;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
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
 * @author Lulu
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String correo;
    private String clave;
    private String nombre;
    private String apellido;
    private String estado;
    private String rol;
    private String manager;
    private LocalDate fechaInicio;
    private List<UsuarioTO> usuarios;
    private UsuarioTO usuario;
    private ServicioUsuario su;
    private boolean esNuevo = false;
    private UsuarioTO selectedUsuario = new UsuarioTO();
    
    private String managerFromUser;
    private List<String> managersFromUsers;

    private int numTicket;
    private String tipo;
    private String motivo;
    private String remitente;
    private String receptor;
    private String estadoTicket;
    private List<PermisoTO> permisos;
    private List<PermisoTO> permisosResueltos;
    private PermisoTO selectedPermiso = new PermisoTO();

    private LocalDate fechaFinal;
    private int diasVacaciones;
    private List<VacacionesTO> vacaciones;
    private VacacionesTO selectedVacacion = new VacacionesTO();
    private long diasConcedidos;

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
    

    @PostConstruct
    
   
    
    public void mostrarUsuarios() {
        try {
            ServicioUsuario su = new ServicioUsuario();
            ServicioPermiso sp = new ServicioPermiso();
            this.usuarios = null;
            this.permisos = null;
            this.permisosResueltos = null;
            if (this.usuario.getRol().equals("Admin")) {
                this.usuarios = new ServicioUsuario().demeUsuariosAdmin();
                this.permisos = new ServicioPermiso().demePermisos();
                this.permisosResueltos = new ServicioPermiso().demePermisosResueltos(this.usuario.getCorreo());
            } else if (this.usuario.getRol().equals("Manager")) {
                this.usuarios = new ServicioUsuario().demeUsuariosManager(this.usuario.getCorreo());
                this.permisos = new ServicioPermiso().demePermisosReceptor(this.usuario.getCorreo());
                this.permisosResueltos = new ServicioPermiso().demePermisosResueltos(this.usuario.getCorreo());
            } else {
                this.usuarios = new ServicioUsuario().demeUsuariosColaborador(this.usuario.getManager());
                this.permisos = new ServicioPermiso().demePermisosRemitente(this.usuario.getCorreo());
            }
        } catch (Exception e) {
        }

    }

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }

    public void logoutSalir() {
        this.redireccionar("/faces/index.xhtml");
        this.usuario = null;
    }

    public void permisosResueltosAbrir() {
        this.redireccionar("/faces/permisosResueltos.xhtml");
    }

    public void permisosResueltosSalir() {
        this.redireccionar("/faces/permisos.xhtml");
    }

    public UsuarioTO validacionUsuario() {

        UsuarioTO retorne = new UsuarioTO(this.correo, this.clave, this.nombre, this.apellido, this.rol, this.manager, this.fechaInicio, this.diasVacaciones);

        try {

            ServicioUsuario su = new ServicioUsuario();
            retorne = su.demeUsuario(correo, clave);
            usuario = retorne;

            if (retorne != null) {
                mostrarUsuarios();
                this.redireccionar("/faces/usuarios.xhtml");

            } else {

                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Existe en la base de datos", "No Existe en la base de datos"));

            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "ERROR"));

            e.printStackTrace();
        }
        return retorne;

    }

    public void saveUser() {

        try {
            ServicioUsuario su = new ServicioUsuario();
            selectedUsuario.setManager(this.usuario.getCorreo());
            su.insertar(selectedUsuario);
            this.usuarios.add(selectedUsuario);//para simular       

            this.esNuevo = false;
            this.selectedUsuario = new UsuarioTO();
            PrimeFaces.current().executeScript("PF('manageUserDialogAgregar').hide()");
            mostrarUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nuevo Usuario", "Nuevo Usuario Agregado Correctamente"));
        //---this.servicioUsuario.listarUsuarios();

    }

    
    
    public void updateUser() {
        try {
            ServicioUsuario su = new ServicioUsuario();
            su.modificar(selectedUsuario);
            this.esNuevo = false;
            this.selectedUsuario = new UsuarioTO();
            PrimeFaces.current().executeScript("PF('manageUserDialogEdit').hide()");
            mostrarUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Editado", "Usuario Editado Correctamente"));

    }

    public void deleteUser() {
        System.out.println("Estoy eliminando al usuario");
        try {
            ServicioUsuario su = new ServicioUsuario();
            su.eliminar(selectedUsuario);
            mostrarUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Eliminado", "Usuario Eliminado Correctamente"));

    }

    public void openNew() {
        this.esNuevo = true;
        this.selectedUsuario = new UsuarioTO();

        this.selectedPermiso = new PermisoTO();
    }

    public void savePermiso() {

        try {
            ServicioPermiso sp = new ServicioPermiso();
            selectedPermiso.setRemitente(this.usuario.getCorreo());
            selectedPermiso.setReceptor(this.usuario.getManager());
            sp.insertar(selectedPermiso);
            this.permisos.add(selectedPermiso);//para simular       

            this.esNuevo = false;
            this.selectedPermiso = new PermisoTO();
            PrimeFaces.current().executeScript("PF('manageUserDialogAgregarPermiso').hide()");
            mostrarUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nuevo Permiso", "Nuevo Permiso Agregado Correctamente"));
        //---this.servicioUsuario.listarUsuarios();

    }

    public void aprobarPermiso() {
        try {
            ServicioPermiso sp = new ServicioPermiso();
            sp.aprobar(selectedPermiso.getNumTicket());

            this.selectedPermiso = new PermisoTO();
            PrimeFaces.current().executeScript("PF('manageUserDialogRespuesta').hide()");
            mostrarUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permiso Apobado", "Permiso Aprobado Correctamente"));

    }

    public void rechazarPermiso() {
        try {
            ServicioPermiso sp = new ServicioPermiso();
            sp.rechazar(selectedPermiso.getNumTicket());

            this.selectedPermiso = new PermisoTO();
            PrimeFaces.current().executeScript("PF('manageUserDialogRespuesta').hide()");
            mostrarUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Permiso Rechazado", "Permiso Rechazado Correctamente"));

    }

    public LoginController() {
    }

    public LoginController(String usuario, String clave, String rol) {
        this.correo = usuario;
        this.clave = clave;
        this.rol = rol;
    }

    public LoginController(String correo, String nombre, String apellido, String estado, ArrayList<UsuarioTO> usuarios) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.usuarios = usuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellidos) {
        this.apellido = apellidos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<UsuarioTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<UsuarioTO> usuarios) {
        this.usuarios = usuarios;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public UsuarioTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioTO selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

    public ServicioUsuario getSu() {
        return su;
    }

    public void setSu(ServicioUsuario su) {
        this.su = su;
    }

    public int getNumTicket() {
        return numTicket;
    }

    public void setNumTicket(int numTicket) {
        this.numTicket = numTicket;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getEstadoTicket() {
        return estadoTicket;
    }

    public void setEstadoTicket(String estadoTicket) {
        this.estadoTicket = estadoTicket;
    }

    public List<PermisoTO> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoTO> permisos) {
        this.permisos = permisos;
    }

    public PermisoTO getSelectedPermiso() {
        return selectedPermiso;
    }

    public void setSelectedPermiso(PermisoTO selectedPermiso) {
        this.selectedPermiso = selectedPermiso;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<PermisoTO> getPermisosResueltos() {
        return permisosResueltos;
    }

    public void setPermisosResueltos(List<PermisoTO> permisosResueltos) {
        this.permisosResueltos = permisosResueltos;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getDiasVacaciones() {
        return diasVacaciones;
    }

    public void setDiasVacaciones(int diasVacaciones) {
        this.diasVacaciones = diasVacaciones;
    }

    public List<VacacionesTO> getVacaciones() {
        return vacaciones;
    }

    public void setVacaciones(List<VacacionesTO> vacaciones) {
        this.vacaciones = vacaciones;
    }

    public VacacionesTO getSelectedVacacion() {
        return selectedVacacion;
    }

    public void setSelectedVacacion(VacacionesTO selectedVacacion) {
        this.selectedVacacion = selectedVacacion;
    }

}
