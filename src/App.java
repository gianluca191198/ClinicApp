
public class App {

	public BasePacientes pacientes;

	public App() {
		pacientes = BasePacientes.obtenerInstancia();
	}
	
	public void agregarPaciente(String nombre, int dni) {
		pacientes.agregarPaciente(nombre , dni);
	}
	
	public void eliminarPaciente(int dni) {
		pacientes.eliminarPaciente(dni);
	}
	
}
