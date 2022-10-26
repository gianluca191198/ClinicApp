import java.util.ArrayList;

public class Medico {
	
	private String nombre;
	private TurnoLaboral TURNO_LABORAL;
	private CalendarioTurnos calendarioTurnos;
	
	public Medico(String nombre, TurnoLaboral TURNO_LABORAL) {
		this.nombre = nombre;
		calendarioTurnos = new CalendarioTurnos(this);
		this.TURNO_LABORAL = TURNO_LABORAL;
	}
	
	public String obtenerNombre() {
		return this.nombre;
	}
	
	public TurnoLaboral obtenerTurnoLaboral(){
		return this.TURNO_LABORAL;
	}
	
}
