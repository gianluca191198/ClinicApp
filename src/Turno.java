import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
	
	private boolean esSobreTurno = false;
	private Consultorio consultorio;
	private LocalDate fecha;
	private LocalTime hora;
	private LocalTime horaFin; 
	private Medico medico;
	private boolean asistio = false;
	private Prestacion prestacion;
	
	public Turno(LocalDate fecha, LocalTime hora, Medico medico, Prestacion prestacion, Consultorio consultorio) {
		this.fecha = fecha;
		this.hora = hora;
		this.horaFin = hora.plusMinutes(30);
		this.medico = medico;
		this.prestacion = prestacion;
		this.consultorio = consultorio;
	}
	
	public LocalDate obtenerFecha() {
		return fecha;
	}

	public LocalTime obtenerHora() {
		return hora;
	}
	
	public LocalTime obtenerHoraFin() {
		return horaFin;
	}

	public void marcarAsistencia() {
		this.asistio = true;
	}
	
	public boolean asistio() {
		return asistio;
	}
	
	public void marcarSobreTurno() {
		this.esSobreTurno = true;
	}
	
	public boolean esSobreTurno() {
		return esSobreTurno;
	}
	
	public Consultorio obtenerConsultorio() {
		return consultorio;
	}
	
	public Prestacion obtenerPrestacion() {
		return prestacion;
	}
}
