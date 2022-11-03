import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Consultorio {

	protected Especialidad especialidad;
	protected ArrayList<Medico> medicos = new ArrayList<Medico>();
	
	public void generarReporte() {}
	
	public Especialidad obtenerEspecialidad() {
		return this.especialidad;
	}
	
	public Medico obtenerMedicoDisponible(LocalDate fecha, LocalTime hora) {
		Medico medico = null;
		for(int i = 0; i < medicos.size(); i++) {
			if(medicos.get(i).obtenerCalendarioTurnos().comprobarDisponibilidad(fecha, hora)) {
				medico = medicos.get(i);
			}
		}
		return medico;
	}
}
