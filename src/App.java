import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class App {

	private BasePacientes pacientes;
	private ArrayList<Medico> medicos = new ArrayList<Medico>();
	final private int claveAdmin = 41463608;
	Hashtable<Especialidad, Integer> precioPrestacion = new Hashtable<Especialidad, Integer>();

	public App() {
		pacientes = BasePacientes.obtenerInstancia();	
		cargarMedicosYConsultorios();
		cargarPrecioPrestacion();
	}
	
	public void pagarPrestacion(Especialidad especialidad) {
		System.out.println("El monto a pagar es: " + precioPrestacion.get(especialidad));
		System.out.println("Procesando pago...");
		System.out.println("Pago realizado con exito");
	}
	
	public void agregarPaciente(String nombre, int dni) {
		pacientes.agregarPaciente(nombre , dni);
	}
	
	
	public void eliminarPaciente(int dni) {
		pacientes.eliminarPaciente(dni);
	}
	
	public boolean verificarClaveAdmin(int clave) {
		return this.claveAdmin == clave; 
	}
	
	/*
	 * Pre: **fecha** debe tener el formato "aaaa-mm-dd" 
	 * 		**hora** debe tener el formato "hh:mm:ss"
	 */
	public void reservarTurnoEstudio(String fecha, String horario, int dniPaciente, Especialidad especialidad) {
		Paciente paciente = pacientes.buscarPaciente(dniPaciente);
		LocalDate dia = LocalDate.parse(fecha);
		LocalTime hora = LocalTime.parse(horario);
		
		Estudio estudio = new Estudio(especialidad);
		Medico medico = buscarMedicoPorEspecialidad(especialidad);
		medico.obtenerCalendarioTurnos().reservarTurno(dia, hora, paciente, estudio, medico.obtenerConsultorio());
		
	}
	
	/*
	 * Pre: **fecha** debe tener el formato "aaaa-mm-dd" 
	 * 		**hora** debe tener el formato "hh:mm:ss"
	 */
	public void reservarTurnoTratamiento(String fecha, String horario, int dniPaciente, Especialidad especialidad) {
		Paciente paciente = pacientes.buscarPaciente(dniPaciente);
		LocalDate dia = LocalDate.parse(fecha);
		LocalTime hora = LocalTime.parse(horario);
		
		Tratamiento tratamiento = new Tratamiento(especialidad);
		Medico medico = buscarMedicoPorEspecialidad(especialidad);
		medico.obtenerCalendarioTurnos().reservarTurno(dia, hora, paciente, tratamiento, medico.obtenerConsultorio());
		
	}
	
	public Medico buscarMedicoPorEspecialidad(Especialidad especialidad) {
		Medico medico = null;
		for(int i = 0; i < medicos.size(); i++) {
			if(medicos.get(i).obtenerEspecialidad() == especialidad) {
				medico = medicos.get(i);
			}
		}
		return medico;
	}
	
	public Medico obtenerMedicoPorEspecialidadYTurno(Especialidad especialidad, TurnoLaboral turno) {
		Medico medico = null;
		for(int i = 0; i < medicos.size(); i++) {
			if(medicos.get(i).obtenerEspecialidad() == especialidad) {
				if(medicos.get(i).obtenerTurnoLaboral() == turno) {
					medico = medicos.get(i);					
				}
			}
		}
		return medico;
	}
	
	private void cargarMedicosYConsultorios() {
		Consultorio laboratorioRayosX = new Laboratorio("Laboratorio de rayos x", Especialidad.RADIOGRAFIAS);
		Consultorio laboratorioExtraccionSangre = new Laboratorio("Laboratorio de sangre", Especialidad.EXTRACCION_SANGRE);
		Consultorio laboratorioImagen = new Laboratorio("Laboratorio de imagen", Especialidad.ECOGRAFIAS);
		Consultorio consultorioCardiologia = new ConsultorioTradicional("Consultorio de Cardiologia", Especialidad.CARDIOLOGIA);
		Consultorio consultorioOdontologia = new ConsultorioTradicional("Consultorio de Odontologia", Especialidad.ODONTOLOGIA);
		Consultorio consultorioTraumatologia = new ConsultorioTradicional("Consultorio de Traumatologia", Especialidad.TRAUMATOLOGIA);	
		Consultorio consultorioClinico = new ConsultorioTradicional("Consultorio de Traumatologia", Especialidad.CLINICA);	
		
		medicos.add(new Medico("Gaston", TurnoLaboral.MANIANA, Especialidad.RESONANCIA_MAGNETICA, laboratorioImagen));
		medicos.add(new Medico("Sofia", TurnoLaboral.TARDE, Especialidad.RESONANCIA_MAGNETICA, laboratorioImagen));
		
		medicos.add(new Medico("Gaston", TurnoLaboral.MANIANA, Especialidad.RADIOGRAFIAS, laboratorioRayosX));
		medicos.add(new Medico("Sofia", TurnoLaboral.TARDE, Especialidad.RADIOGRAFIAS, laboratorioRayosX));
		
		medicos.add(new Medico("Gaston", TurnoLaboral.MANIANA, Especialidad.EXTRACCION_SANGRE, laboratorioExtraccionSangre));
		medicos.add(new Medico("Sofia", TurnoLaboral.TARDE, Especialidad.EXTRACCION_SANGRE, laboratorioExtraccionSangre));

		medicos.add(new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA, consultorioCardiologia));
		medicos.add(new Medico("Agustin", TurnoLaboral.TARDE, Especialidad.CARDIOLOGIA, consultorioCardiologia));
		
		medicos.add(new Medico("Gian", TurnoLaboral.MANIANA, Especialidad.ODONTOLOGIA, consultorioOdontologia));
		medicos.add(new Medico("Ferderico", TurnoLaboral.TARDE, Especialidad.ODONTOLOGIA, consultorioOdontologia));
		
		medicos.add(new Medico("Marcos", TurnoLaboral.MANIANA, Especialidad.CLINICA, consultorioClinico));
		medicos.add(new Medico("Maria", TurnoLaboral.TARDE, Especialidad.CLINICA, consultorioClinico));
		
		medicos.add(new Medico("Cristian", TurnoLaboral.MANIANA, Especialidad.TRAUMATOLOGIA, consultorioTraumatologia));
		medicos.add(new Medico("Milagros", TurnoLaboral.TARDE, Especialidad.TRAUMATOLOGIA, consultorioTraumatologia));
	}
	
	private void cargarPrecioPrestacion() {
		this.precioPrestacion.put(Especialidad.CARDIOLOGIA, 2500);
		this.precioPrestacion.put(Especialidad.ODONTOLOGIA, 2500);
		this.precioPrestacion.put(Especialidad.CLINICA, 2000);
		this.precioPrestacion.put(Especialidad.TRAUMATOLOGIA, 2000);
		this.precioPrestacion.put(Especialidad.EXTRACCION_SANGRE, 3000);
		this.precioPrestacion.put(Especialidad.RADIOGRAFIAS, 2500);
		this.precioPrestacion.put(Especialidad.RESONANCIA_MAGNETICA, 10000);
	}
}
