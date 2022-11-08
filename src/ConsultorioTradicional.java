
public class ConsultorioTradicional extends Consultorio {
	
	private String nombre;
	
	/*
	 * Crea un consultorio tradicional de nombre **nombre** y especialidad **especialidad**.
	 */
	public ConsultorioTradicional(String nombre, Especialidad especialidad) {
		this.nombre = nombre;
		this.especialidad = especialidad;
	}
}
