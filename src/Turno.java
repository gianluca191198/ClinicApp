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
	
	/*
	 * PRE: **fecha** debe tener el formato "aaaa-mm-dd" 
	 * 		**hora** debe tener el formato "hh:mm:ss"
	 * POST: Crea un turno con fecha **fecha**, hora **hora**, para el paciente **paciente**,
	 * 		 con el médico **medico**, con el tipo de prestación **prestacion** y en el consultorio **consultorio**.
	 */
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
	
	/*
	 * POST: Devuelve la fecha del turno.
	 */
	public LocalDate obtenerFecha() {
		return fecha;
	}

	/*
	 * POST: Devuelve la hora del turno.
	 */
	public LocalTime obtenerHora() {
		return hora;
	}
	
	/*
	 * POST: Devuelve la hora en que finaliza el turno.
	 */
	public LocalTime obtenerHoraFin() {
		return horaFin;
	}
	
	/*
	 * POST: Establece si el turno se trata de un sobre turno.
	 */
	public void marcarSobreTurno() {
		this.esSobreTurno = true;
	}
	
	/*
	 * POST: Devuelve si el turno es sobre turno o no.
	 */
	public boolean esSobreTurno() {
		return esSobreTurno;
	}
	
	/*
	 * POST: Devuelve el consultorio del turno.
	 */
	public Consultorio obtenerConsultorio() {
		return consultorio;
	}
	
	/*
	 * POST: Devuelve la prestación del turno.
	 */
	public Prestacion obtenerPrestacion() {
		return prestacion;
	}
	
	/*
	 * PRE: **estado** debe ser alguno del enum ESTADO.
	 * POST: Cambia el turno al estado de **estado**.
	 */
	public void cambiarEstado(Estado estado) {
		this.estado = estado;
	}
	
	/*
	 * POST: Devuelve el estado del turno.
	 */
	public Estado obtenerEstado() {
		return estado;
	}

	/*
	 * POST: Devuelve el paciente del turno.
	 */
	public Paciente obtenerPaciente() {
		return paciente;
	}

	/*
	 * POST: Devuelve el médico del turno.
	 */
	public Medico obtenerMedico() {
		return medico;
	}

	/*
	 * POST: Devuelve el número del turno.
	 */
	public int obtenerNumeroTurno() {
		return numeroTurno;
	}
}
