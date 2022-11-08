import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;

public class App {

	private BasePacientes pacientes;
	private ArrayList<Medico> medicos = new ArrayList<Medico>();
	final private int claveAdmin = 123412;
	Hashtable<Especialidad, Integer> precioPrestacion = new Hashtable<Especialidad, Integer>();

	/*
	 * Se crea la app junto con la base de pacientes, la carga de medicos y consultorios 
	 * y el precio de las prestaciones.
	 */
	public App() {
		pacientes = BasePacientes.obtenerInstancia();	
		cargarMedicosYConsultorios();
		cargarPrecioPrestacion();
	}
	
	/*
	 * PRE: **especialidad** tiene que ser una especialidad de las contempladas.
	 * POST: Paga un turno de la especialidad **especialidad**
	 */
	public void pagarPrestacion(Especialidad especialidad) {
		System.out.println("El monto a pagar es: " + precioPrestacion.get(especialidad));
		System.out.println("Procesando pago...");
		System.out.println("Pago realizado con exito");
	}
	
	/*
	 * POST: Agrega un paciente de nombre **nombre** y dni **dni** a la base de datos de pacientes.
	 */
	public Paciente agregarPaciente(String nombre, int dni) {
		return pacientes.agregarPaciente(nombre , dni);
	}
	
	/*
	 * PRE: el dni **dni** tiene que estar presente en la base de datos de pacientes.
	 * POST: Elimina un paciente con dni **dni** de la base de datos de pacientes.
	 */
	public Paciente eliminarPaciente(int dni) {
		return pacientes.eliminarPaciente(dni);
	}
	
	/*
	 * POST: Busca un paciente con dni **dni** en la base de datos de pacientes.
	 */
	public Paciente buscarPaciente(int dni) {
		return pacientes.buscarPaciente(dni);
	}
	
	/*
	 * POST: Guarda la base de datos actual de pacientes.
	 */
	public void guardarPacientes() {
		pacientes.guardarPacientes();
	}
	
	/*
	 * POST: Verifica si la clave ingresada como administrador en el menu, es correcta y lo muestra en pantalla.
	 * 		 De no ser asi, devolvera un mensaje indicando lo contrario.
	 */
	public boolean verificarClaveAdmin(int clave) {
		if(this.claveAdmin == clave) {
			System.out.println("Autenticacion exitosa");
		}else {
			System.out.println("Clave invalida");
		}
		return this.claveAdmin == clave; 
	}
	
	/*
	 * PRE: **fecha** debe tener el formato "aaaa-mm-dd" 
	 * 		**hora** debe tener el formato "hh:mm:ss"
	 * 		**especialidad** debe ser una de las especialidades contempladas.
	 * POST: Reserva un turno para un estudio en la fecha **fecha**, hora **horario**, para el paciente con dni
	 * 		 **dniPaciente**, en la especialidad **especialidad**.
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
	 * PRE: **fecha** debe tener el formato "aaaa-mm-dd" 
	 * 		**hora** debe tener el formato "hh:mm:ss"
	 * 		**especialidad** debe ser una de las especialidades contempladas.
	 * POST: Reserva un turno para tratamiento en la fecha **fecha**, hora **horario**, para el paciente con dni
	 * 		 **dniPaciente**, en la especialidad **especialidad**.
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
	
	/*
	 * PRE: **especialidad** debe ser una de las especialidades contempladas.
	 * POST: Devuelve un médico de la especialidad **especialidad**, en el turno **turno**.
	 */
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
	
	/*
	 * POST: Carga todos los consultorios (ya sean consultorios tradicionales, como laboratorios) de cada especialidad, 
	 * 		 con sus respectivos médicos (los cuales también se cargan con sus id's) en los horarios de la mañana y de la tarde.
	 */
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
	
	/*
	 * POST: Carga el precio que hay que abonar por cada especialidad.
	 */
	private void cargarPrecioPrestacion() {
		this.precioPrestacion.put(Especialidad.CARDIOLOGIA, 2500);
		this.precioPrestacion.put(Especialidad.ODONTOLOGIA, 2500);
		this.precioPrestacion.put(Especialidad.CLINICA, 2000);
		this.precioPrestacion.put(Especialidad.TRAUMATOLOGIA, 2000);
		this.precioPrestacion.put(Especialidad.EXTRACCION_SANGRE, 3000);
		this.precioPrestacion.put(Especialidad.RADIOGRAFIAS, 2500);
		this.precioPrestacion.put(Especialidad.RESONANCIA_MAGNETICA, 10000);
	}
	
	/*
	 * PRE: **hora** debe tener el formato "hh:mm:ss"
	 * POST: Dependiendo la hora que se ingrese por parámetro,
	 * 		 devuelve si la misma pertenece al turno mañana o al turno tarde.
	 */
	private TurnoLaboral obtenerTurnoLaboralPorHorario(LocalTime hora) {
		if(hora.compareTo(LocalTime.parse("16:00:00")) > 0) {
			return TurnoLaboral.TARDE;
		}else {
			return TurnoLaboral.MANIANA;
		}
	}
	
	/*
	 * POST: Verifica si el entero **idMedico** pertenece a un id válido de médico.
	 */
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
