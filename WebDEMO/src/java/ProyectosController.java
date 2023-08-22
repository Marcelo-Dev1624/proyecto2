
import com.mycompany.demobd.ServicioUsuario;
import com.mycompany.demobd.UsuarioTO;
import com.mycompany.demobd.proyectosTO;
import com.mycompany.demobd.servicioProyectos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
@ManagedBean(name = "proyectosController")
@SessionScoped
public class ProyectosController {
    private int idProyecto;
    private String nombreProyecto;
    private String encargadoProyecto;
    
    private List<proyectosTO> proyectos;
    private proyectosTO proyecto;
    private boolean esNuevo;
    
    private UsuarioTO usuario;
    
    private proyectosTO selectedProyecto = new proyectosTO();

    public ProyectosController() {
    }

    public ProyectosController(int idProyecto, String nombreProyecto, String encargadoProyecto, List<proyectosTO> proyectos, proyectosTO proyecto, boolean esNuevo, UsuarioTO usuario) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.encargadoProyecto = encargadoProyecto;
        this.proyectos = proyectos;
        this.proyecto = proyecto;
        this.esNuevo = esNuevo;
        this.usuario = usuario;
    }

    
    
    

    
    @PostConstruct
    public void mostrarProyectos(){
        try {
            servicioProyectos sp = new servicioProyectos();
            this.proyectos = null;
            
            
                this.proyectos = new servicioProyectos().obtenerProyectos();
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(ProyectosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void saveProyecto() {

        try {
            
            servicioProyectos sp = new servicioProyectos();
            
            sp.insertar(selectedProyecto);
            this.proyectos.add(selectedProyecto);//para simular       

            this.esNuevo = false;
            this.selectedProyecto = new proyectosTO();
            PrimeFaces.current().executeScript("PF('manageUserDialogAgregar').hide()");
            mostrarProyectos();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nuevo Proyecto", "Nuevo Proyecto Agregado Correctamente"));
        

    }

    
    
    public void updateProyecto() {
        try {
            servicioProyectos sp = new servicioProyectos();
            sp.modificar(selectedProyecto);
            this.esNuevo = false;
            this.selectedProyecto = new proyectosTO();
            PrimeFaces.current().executeScript("PF('manageUserDialogEdit').hide()");
            mostrarProyectos();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto Editado", "Proyecto Editado Correctamente"));

    }

    public void deleteProyecto() {
        System.out.println("Estoy eliminando al proyecto");
        try {
            servicioProyectos sp = new servicioProyectos();
            sp.eliminar(selectedProyecto);
            mostrarProyectos();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto Eliminado", "Proyecto Eliminado Correctamente"));

    }

    public void openNew() {
        this.esNuevo = true;
        this.selectedProyecto = new proyectosTO();

        
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public String getEncargadoProyecto() {
        return encargadoProyecto;
    }

    public List<proyectosTO> getProyectos() {
        return proyectos;
    }

    public proyectosTO getProyecto() {
        return proyecto;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public proyectosTO getSelectedProyecto() {
        return selectedProyecto;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public void setEncargadoProyecto(String encargadoProyecto) {
        this.encargadoProyecto = encargadoProyecto;
    }

    public void setProyectos(List<proyectosTO> proyectos) {
        this.proyectos = proyectos;
    }

    public void setProyecto(proyectosTO proyecto) {
        this.proyecto = proyecto;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

   
    public void setSelectedProyecto(proyectosTO selectedProyecto) {
        this.selectedProyecto = selectedProyecto;
    }
    
    
}
