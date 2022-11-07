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
	
	public Medico(String nombre, TurnoLaboral TURNO_LABORAL, Especialidad especialidad, Consultorio consultorio, int idMedico) {
		this.idMedico = idMedico;
		this.nombre = nombre;
		this.calendarioTurnos = new CalendarioTurnos(this);
		this.TURNO_LABORAL = TURNO_LABORAL;
		this.especialidad = especialidad;
		this.consultorio = consultorio;
		
	}
	public CalendarioTurnos obtenerCalendarioTurnos() {
		return calendarioTurnos;
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
	
	public Especialidad obtenerEspecialidad() {
		return this.especialidad;
	}
	
	public Consultorio obtenerConsultorio() {
		return consultorio;
	}
	
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
	
	public void cambiarEstadoTurno(int nroTurno, Estado estado) {
		obtenerTurnoPorNumero(nroTurno).cambiarEstado(estado);
	}
	public int obtenerIdMedico() {
		return this.idMedico;
	}
	
}
