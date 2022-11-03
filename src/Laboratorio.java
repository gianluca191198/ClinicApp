import java.util.logging.ConsoleHandler;

public class Laboratorio extends Consultorio {
	
	private String nombre;
	
	public Laboratorio(String nombre, Especialidad especialidad) {
		this.nombre = nombre;
		this.especialidad = especialidad;
	}
}
