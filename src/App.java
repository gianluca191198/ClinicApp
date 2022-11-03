import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class App {

	private BasePacientes pacientes;
	private ArrayList<Consultorio> consultorios = new ArrayList<Consultorio>();
	private ArrayList<Medico> medicos = new ArrayList<Medico>();

	public App() {
		pacientes = BasePacientes.obtenerInstancia();
		medicos.add(new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA));
		medicos.add(new Medico("Gian", TurnoLaboral.MANIANA, Especialidad.ODONTOLOGIA));
		medicos.add(new Medico("Marcos", TurnoLaboral.MANIANA, Especialidad.CLINICA));
		medicos.add(new Medico("Cristian", TurnoLaboral.MANIANA, Especialidad.TRAUMATOLOGIA));
		consultorios.add(new Laboratorio("Laboratorio de rayos x", Especialidad.RADIOGRAFIAS));
		consultorios.add(new Laboratorio("Laboratorio de sangre", Especialidad.EXTRACCION_SANGRE));
		consultorios.add(new Laboratorio("Laboratorio de imagen", Especialidad.ECOGRAFIAS));
		consultorios.add(new ConsultorioTradicional("Consultorio de Cardiologia", Especialidad.CARDIOLOGIA ));
		consultorios.add(new ConsultorioTradicional("Consultorio de Odontologia", Especialidad.ODONTOLOGIA));
		consultorios.add(new ConsultorioTradicional("Consultorio de Traumatologia", Especialidad.TRAUMATOLOGIA));		
	}
	
	public void agregarPaciente(String nombre, int dni) {
		pacientes.agregarPaciente(nombre , dni);
	}
	
	public void eliminarPaciente(int dni) {
		pacientes.eliminarPaciente(dni);
	}
	
	/*
	 * Recorre el arraylist de consultorios, a cada consultorio le pregunta si en sus
	 * prestaciones, contiene la prestacion que buscamos por parametro.
	 * Si la misma se encuentra en dicho consultorio, se lo guarda en el arraylist consultoriosPosibles
	 * y luego se devuelve dicho arraylist.
	 */
	private Consultorio buscarConsultorioPorEspecialidad(Especialidad especialidad) {
		Consultorio consultorio = null;
		
		for(int i = 0; i < consultorios.size(); i++) {
			if (consultorios.get(i).obtenerEspecialidad() == especialidad) {
				consultorio = consultorios.get(i);
			}
		}
		
		return consultorio;
	}
	
	
	private Medico buscarMedicoPorEspecialidad(Especialidad especialidad) {
		Medico medico = null;
		for(int i = 0; i < medicos.size(); i++) {
			if(medicos.get(i).obtenerEspecialidad() == especialidad) {
				medico = medicos.get(i);
			}
		}
		return medico;
	}
	/*
	 * Pre: **fecha** debe tener el formato "aaaa-mm-dd" 
	 * 		**hora** debe tener el formato "hh:mm:ss"
	 */
	public void reservarTurnoEstudio(String fecha, String horario, Especialidad especialidad) {
		LocalDate dia = LocalDate.parse(fecha);
		LocalTime hora = LocalTime.parse(horario);
		
		Estudio estudio = new Estudio(especialidad);
		Consultorio consultorio = buscarConsultorioPorEspecialidad(especialidad);
		Medico medico = consultorio.obtenerMedicoDisponible(dia, hora);
		medico.obtenerCalendarioTurnos().reservarTurno(dia, hora, estudio, consultorio);
		
	}
	
	
}
