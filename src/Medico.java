import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Medico {
	
	private int idMedico;
	private String nombre;
	private TurnoLaboral TURNO_LABORAL;
	private CalendarioTurnos calendarioTurnos;
	private Especialidad especialidad;
	private Consultorio consultorio;
	
	/*
	 * Crea un médico de nombre **nombre**, con turno laboral **TURNO_LABORAL**, en la especialidad **especialidad**,
	 * en el consultorio **consultorio** y con id **idMedico**.
	 */
	public Medico(String nombre, TurnoLaboral TURNO_LABORAL, Especialidad especialidad, Consultorio consultorio, int idMedico) {
		this.idMedico = idMedico;
		this.nombre = nombre;
		this.calendarioTurnos = new CalendarioTurnos(this);
		this.TURNO_LABORAL = TURNO_LABORAL;
		this.especialidad = especialidad;
		this.consultorio = consultorio;
		
	}
	
	/*
	 * POST: Devuelve el calendario de turnos del medico. 
	 */
	public CalendarioTurnos obtenerCalendarioTurnos() {
		return calendarioTurnos;
	}
	
	/*
	 * POST: Devuelve el nombre del médico.
	 */
	public String obtenerNombre() {
		return this.nombre;
	}
	
	/*
	 * POST: Devuelve el turno laboral del médico.
	 */
	public TurnoLaboral obtenerTurnoLaboral(){
		return this.TURNO_LABORAL;
	}
	
	/*
	 * POST: Devuelve cuando comienza el turno laboral del médico.
	 */
	public LocalTime obtenerHoraComienzoTurnoLaboral() {
		if(TURNO_LABORAL == TurnoLaboral.MANIANA) {
			return LocalTime.parse("07:00:00");
		}else {
			return LocalTime.parse("16:00:00");
		}
	}
	
	/*
	 * POST: Devuelve cuando finaliza el turno laboral del médico.
	 */
	public LocalTime obtenerHoraFinTurnoLaboral() {
		if(TURNO_LABORAL == TurnoLaboral.MANIANA) {
			return LocalTime.parse("15:00:00");
		}else {
			return LocalTime.parse("20:00:00");
		}
	}
	
	/*
	 * POST: Devuelve la especialidad del médico.
	 */
	public Especialidad obtenerEspecialidad() {
		return this.especialidad;
	}
	
	/*
	 * POST: Devuelve el consultorio (consultorio tradicional o laboratorio) donde atiende el médico.
	 */
	public Consultorio obtenerConsultorio() {
		return consultorio;
	}
	
	/*
	 * POST: Devuelve un archivo .txt con nombre de paciente, fecha y hora 
	 * 		 de las prestaciones brindadas por el médico.
	 */
	public void generarReportePrestacionesBrindadas() {
		ArrayList<Turno> turnosBrindados = calendarioTurnos.obtenerTurnosBrindados();
		try {
			File reporte = new File(".\\Recursos\\ReporteMedico-" + this.nombre.trim() + "-" + LocalDate.now().toString());
			FileWriter escribir;
			reporte.createNewFile();
			escribir = new FileWriter(reporte);
			escribir.write("Turnos Brindados: \r\n");
			escribir.write("----------------------------------------- \r\n");
				
			for (int i = 0; i < turnosBrindados.size(); i++) {
				Turno turno = turnosBrindados.get(i);
				escribir.write("Paciente:"+ turno.obtenerPaciente().obtenerNombre() + "\r\n");
				escribir.write("Dia:"+ turno.obtenerFecha() + "\r\n");
				escribir.write("Hora:"+ turno.obtenerHora() + "\r\n");
				escribir.write("----------------------------------------- \r\n");
			}
			
			escribir.close();
			System.out.println("Reporte generardo con exito");
		} catch (IOException e) {
			System.out.println("Error al generar reporte");
		}
	}
	
	/*
	 * PRE: **nroTurno** debe ser un número de turno válido.
	 * POST: Devuelve el turno perteneciente al número **nroTurno**.
	 */
	public Turno obtenerTurnoPorNumero(int nroTurno) {
		Turno turno = null;
		ArrayList<Turno> turnos = calendarioTurnos.obtenerTurnos();
		for(int i = 0; i < turnos.size(); i++) {
			if(turnos.get(i).obtenerNumeroTurno() == nroTurno ) {
				turno = calendarioTurnos.obtenerTurnos().get(i);
			}
		}
		
		return turno;
	}
	
	/*
	 * PRE: **nroTurno** debe ser un número de turno válido.
	 * 		**estado** debe ser un estado válido.
	 * POST: Cambia el turno con número **nroTurno** al estado de **estado**.
	 */
	public void cambiarEstadoTurno(int nroTurno, Estado estado) {
		obtenerTurnoPorNumero(nroTurno).cambiarEstado(estado);
	}
	
	/*
	 * POST: Devuelve el id del médico.
	 */
	public int obtenerIdMedico() {
		return this.idMedico;
	}
	
}
