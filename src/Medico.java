import java.time.LocalTime;
import java.util.ArrayList;

public class Medico {
	
	private String nombre;
	private TurnoLaboral TURNO_LABORAL;
	private CalendarioTurnos calendarioTurnos;
	private ArrayList<Especialidad> especialidades= new ArrayList<Especialidad>();
	
	public Medico(String nombre, TurnoLaboral TURNO_LABORAL, Especialidad especialidad) {
		this.nombre = nombre;
		this.calendarioTurnos = new CalendarioTurnos(this);
		this.TURNO_LABORAL = TURNO_LABORAL;
		this.especialidades.add(especialidad);
		
	}
	
	public String obtenerNombre() {
		return this.nombre;
	}
	
	public TurnoLaboral obtenerTurnoLaboral(){
		return this.TURNO_LABORAL;
	}
	
	public LocalTime obtenerHoraComienzoTurnoLaboral() {
		if(TURNO_LABORAL == TurnoLaboral.MANIANA) {
			return LocalTime.parse("07:00:00");
		}else {
			return LocalTime.parse("16:00:00");
		}
	}
	
	public LocalTime obtenerHoraFinTurnoLaboral() {
		if(TURNO_LABORAL == TurnoLaboral.MANIANA) {
			return LocalTime.parse("15:00:00");
		}else {
			return LocalTime.parse("20:00:00");
		}
	}
	
	public void agregarEspecialidad(Especialidad especialidad) {
		especialidades.add(especialidad);
	}
	
}
