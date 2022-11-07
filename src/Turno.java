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
	private Prestacion prestacion;
	private Estado estado;
	private Paciente paciente;
	private int numeroTurno;
	
	public Turno(LocalDate fecha, LocalTime hora, Paciente paciente, Medico medico, Prestacion prestacion, Consultorio consultorio) {
		this.numeroTurno = (int) (Math.random() * 10000);
		this.fecha = fecha;
		this.hora = hora;
		this.horaFin = hora.plusMinutes(30);
		this.medico = medico;
		this.prestacion = prestacion;
		this.consultorio = consultorio;
		this.estado = Estado.ASIGNADO;
		this.paciente = paciente;
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
	
	public void cambiarEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Estado obtenerEstado() {
		return estado;
	}

	public Paciente obtenerPaciente() {
		return paciente;
	}

	public Medico obtenerMedico() {
		return medico;
	}

	public int obtenerNumeroTurno() {
		return numeroTurno;
	}
}
