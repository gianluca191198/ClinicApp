import java.util.logging.ConsoleHandler;

public class Laboratorio extends Consultorio {
	
	private String nombre;
	
	/*
	 * Crea un laboratorio de nombre **nombre** para la especialidad **especialidad**.
	 */
	public Laboratorio(String nombre, Especialidad especialidad) {
		this.nombre = nombre;
		this.especialidad = especialidad;
	}
}
