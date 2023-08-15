import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@ManagedBean
@ViewScoped
public class VacacionesController implements Serializable {

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
    }
}
