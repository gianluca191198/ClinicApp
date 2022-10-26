import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarioTurnos {
	
	private ArrayList<Turno> turnos = new ArrayList<Turno>();
	public Medico medico;
	
	public CalendarioTurnos(Medico medico){
		this.medico = medico;
	}
	
	public void reservarTurno(LocalDate fecha, LocalTime hora ) {
		if(comprobarDisponibilidad(fecha, hora)) {
			turnos.add(new Turno(fecha, hora, medico));
		}else {
			System.out.println("Turno no disponible en la fecha y hora seleccionada");
		}
	}
	
	private boolean comprobarDisponibilidad(LocalDate fecha, LocalTime hora ) {
		boolean disponibilidad = true;
		for(int i = 0; i < turnos.size(); i++) {
			if(turnos.get(i).obtenerFecha() == fecha) {
				if(hora.compareTo(turnos.get(i).obtenerHora()) > 0 && hora.compareTo(turnos.get(i).obtenerHoraFin()) < 0 ) {
					disponibilidad = false;
				}
			}
		}
		return disponibilidad;
	}

}
