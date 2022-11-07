import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class App {

	private BasePacientes pacientes;
	private ArrayList<Medico> medicos = new ArrayList<Medico>();
	final private int claveAdmin = 123412;
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
	
	public Paciente agregarPaciente(String nombre, int dni) {
		return pacientes.agregarPaciente(nombre , dni);
	}
	
	
	public Paciente eliminarPaciente(int dni) {
		return pacientes.eliminarPaciente(dni);
	}
	
	public Paciente buscarPaciente(int dni) {
		return pacientes.buscarPaciente(dni);
	}
	
	public void guardarPacientes() {
		pacientes.guardarPacientes();
	}
	
	public boolean verificarClaveAdmin(int clave) {
		if(this.claveAdmin == clave) {
			System.out.println("Autenticacion exitosa");
		}else {
			System.out.println("Clave invalida");
		}
		return this.claveAdmin == clave; 
	}
	
	/*
	 * Pre: **fecha** debe tener el formato "aaaa-mm-dd" 
	 * 		**hora** debe tener el formato "hh:mm:ss"
	 */
	public Turno reservarTurnoEstudio(LocalDate fecha, LocalTime horario, int dniPaciente, Especialidad especialidad) {
		Paciente paciente = pacientes.buscarPaciente(dniPaciente);
		LocalDate dia = fecha;
		LocalTime hora = horario;
		TurnoLaboral turno = obtenerTurnoLaboralPorHorario(hora); 
		
		Estudio estudio = new Estudio(especialidad);
		Medico medico = obtenerMedicoPorEspecialidadYTurno(especialidad, turno);
		return medico.obtenerCalendarioTurnos().reservarTurno(dia, hora, paciente, estudio, medico.obtenerConsultorio());
		
	}
	
	/*
	 * Pre: **fecha** debe tener el formato "aaaa-mm-dd" 
	 * 		**hora** debe tener el formato "hh:mm:ss"
	 */
	public Turno reservarTurnoTratamiento(LocalDate fecha, LocalTime horario, int dniPaciente, Especialidad especialidad) {
		
		if(fecha == null || horario == null) {
			return null;
		}
		
		Paciente paciente = pacientes.buscarPaciente(dniPaciente);
		LocalDate dia = fecha;
		LocalTime hora = horario;
		TurnoLaboral turno = obtenerTurnoLaboralPorHorario(hora); 
		
		Tratamiento tratamiento = new Tratamiento(especialidad);
		Medico medico = obtenerMedicoPorEspecialidadYTurno(especialidad, turno);
		return medico.obtenerCalendarioTurnos().reservarTurno(dia, hora, paciente, tratamiento, medico.obtenerConsultorio());
		
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
		Consultorio laboratorioImagen = new Laboratorio("Laboratorio de imagen", Especialidad.RESONANCIA_MAGNETICA);
		Consultorio consultorioCardiologia = new ConsultorioTradicional("Consultorio de Cardiologia", Especialidad.CARDIOLOGIA);
		Consultorio consultorioOdontologia = new ConsultorioTradicional("Consultorio de Odontologia", Especialidad.ODONTOLOGIA);
		Consultorio consultorioTraumatologia = new ConsultorioTradicional("Consultorio de Traumatologia", Especialidad.TRAUMATOLOGIA);	
		Consultorio consultorioClinico = new ConsultorioTradicional("Consultorio de Traumatologia", Especialidad.CLINICA);	
		
		medicos.add(new Medico("Gaston", TurnoLaboral.MANIANA, Especialidad.RESONANCIA_MAGNETICA, laboratorioImagen, 1));
		medicos.add(new Medico("Sofia", TurnoLaboral.TARDE, Especialidad.RESONANCIA_MAGNETICA, laboratorioImagen, 2));
		
		medicos.add(new Medico("Federico", TurnoLaboral.MANIANA, Especialidad.RADIOGRAFIAS, laboratorioRayosX, 3));
		medicos.add(new Medico("Martina", TurnoLaboral.TARDE, Especialidad.RADIOGRAFIAS, laboratorioRayosX, 4));
		
		medicos.add(new Medico("Sol", TurnoLaboral.MANIANA, Especialidad.EXTRACCION_SANGRE, laboratorioExtraccionSangre,5));
		medicos.add(new Medico("Raul", TurnoLaboral.TARDE, Especialidad.EXTRACCION_SANGRE, laboratorioExtraccionSangre, 6));

		medicos.add(new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA, consultorioCardiologia, 7));
		medicos.add(new Medico("Agustin", TurnoLaboral.TARDE, Especialidad.CARDIOLOGIA, consultorioCardiologia, 8));
		
		medicos.add(new Medico("Gian", TurnoLaboral.MANIANA, Especialidad.ODONTOLOGIA, consultorioOdontologia, 9));
		medicos.add(new Medico("Ferderico", TurnoLaboral.TARDE, Especialidad.ODONTOLOGIA, consultorioOdontologia, 10));
		
		medicos.add(new Medico("Marcos", TurnoLaboral.MANIANA, Especialidad.CLINICA, consultorioClinico, 11));
		medicos.add(new Medico("Maria", TurnoLaboral.TARDE, Especialidad.CLINICA, consultorioClinico, 12));
		
		medicos.add(new Medico("Cristian", TurnoLaboral.MANIANA, Especialidad.TRAUMATOLOGIA, consultorioTraumatologia, 13));
		medicos.add(new Medico("Milagros", TurnoLaboral.TARDE, Especialidad.TRAUMATOLOGIA, consultorioTraumatologia, 14));
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
	
	private TurnoLaboral obtenerTurnoLaboralPorHorario(LocalTime hora) {
		if(hora.compareTo(LocalTime.parse("16:00:00")) > 0) {
			return TurnoLaboral.TARDE;
		}else {
			return TurnoLaboral.MANIANA;
		}
	}
	
	public Medico verificarMedico(int idMedico) {
		Medico medico = null;
		for(int i = 0; i < medicos.size(); i++) {
			if(medicos.get(i).obtenerIdMedico() == idMedico) {
				medico = medicos.get(i);
			}
		}
		return medico;
	}
}
