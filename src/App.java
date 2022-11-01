import java.util.ArrayList;

public class App {

	public BasePacientes pacientes;
	public ArrayList<Consultorio> consultorios = new ArrayList<Consultorio>();

	public App() {
		pacientes = BasePacientes.obtenerInstancia();
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
	public ArrayList<Consultorio> devolverConsultorioParaPrestacion(Prestacion prestacion) {
		ArrayList<Consultorio> consultoriosPosibles = new ArrayList<Consultorio>();
		
		for(int i = 0; i < consultorios.size(); i++) {
			if (consultorios.get(i).prestaciones.contains(prestacion)) {
				consultoriosPosibles.add(consultorios.get(i));
			}
		}
		
		return consultoriosPosibles;
	}
	
}
