import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarioTurnos {
	
	private ArrayList<Turno> turnos = new ArrayList<Turno>();
	public Medico medico;
	
	/*
	 * Crea el calendario de turnos del medico **medico**.
	 */
	public CalendarioTurnos(Medico medico){
		this.medico = medico;
	}
	
	/*
	 * PRE: **fecha** debe tener el formato "aaaa-mm-dd" 
	 * 		**hora** debe tener el formato "hh:mm:ss"
	 * POST: De haber disponibilidad, reserva un turno en fecha **fecha**, hora **hora**, 
	 * 		 para el paciente **paciente**, con la prestación **prestaciona** y en el consultorio **consultorio**
	 * 		 y lo devuelve.
	 */
	public Turno reservarTurno(LocalDate fecha, LocalTime hora, Paciente paciente, Prestacion prestacion, Consultorio consultorio) {
		Turno turno = null;
		if(comprobarDisponibilidad(fecha, hora)) {
			turno = new Turno(fecha, hora, paciente, medico, prestacion, consultorio);
			turnos.add(turno);
		}
		return turno;
	}
	
	/*
	 * PRE: **fecha** debe tener el formato "aaaa-mm-dd" 
	 * 		**hora** debe tener el formato "hh:mm:ss"
	 * POST: Devuelve si hay disponibilidad de turno o no, para la fecha **fecha** y la hora **hora**. 
	 */
	public boolean comprobarDisponibilidad(LocalDate fecha, LocalTime hora ) {
		boolean disponibilidad = true;
		
		if(fecha.getDayOfWeek().getValue() == 6 || fecha.getDayOfWeek().getValue() == 7) {
			return false;
		}
		if(hora.compareTo(medico.obtenerHoraComienzoTurnoLaboral()) < 0 || hora.compareTo(medico.obtenerHoraFinTurnoLaboral()) >= 0 ) {
			return false;
		}
		
		for(int i = 0; i < turnos.size(); i++) {
			if(turnos.get(i).obtenerFecha().compareTo(fecha) == 0) {
				if(hora.compareTo(turnos.get(i).obtenerHora()) >= 0 && hora.compareTo(turnos.get(i).obtenerHoraFin()) < 0 ) {
					disponibilidad = false;
				}
			}
		}
		return disponibilidad;
	}
	
	/*
	 * POST: Devuelve el ArrayList de turnos.
	 */
	public ArrayList<Turno> obtenerTurnos() {
		return turnos;
	}
	
	/*
	 * POST: Devuelve los turnos que finalizaron exitosamente.
	 */
	public ArrayList<Turno> obtenerTurnosBrindados() {
		ArrayList<Turno> turnosbrindados = new ArrayList<Turno>();
		for(int i= 0; i < this.turnos.size(); i++) {
			if(this.turnos.get(i).obtenerEstado() == Estado.CUMPLIDO ) {
				turnosbrindados.add(turnos.get(i));
			}
		}
		return turnosbrindados;
	}

}
