import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Brenda
 */

@ManagedBean(name = "vacacionesController")
@SessionScoped
public class VacacionesController implements Serializable{
    
    
  /*  private String correo;
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

    private LocalDate startDate;
    private int vacationDays;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;

    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void calculateVacationDays() {
        LocalDate today = LocalDate.now();
        long daysWorked = ChronoUnit.DAYS.between(startDate, today);
        vacationDays = (int) (daysWorked / 30);
        System.out.println(vacationDays);
    }*/
}
