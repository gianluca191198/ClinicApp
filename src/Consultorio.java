import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Consultorio {

	protected Especialidad especialidad;
	
	public void generarReporte() {}
	
	public Especialidad obtenerEspecialidad() {
		return this.especialidad;
	}
}
