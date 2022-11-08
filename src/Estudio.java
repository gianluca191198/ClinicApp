import java.time.LocalDate;
import java.time.LocalTime;

public class Estudio extends Prestacion{
	
	private Especialidad especialidad;
	
	/*
	 * Crea un estudio con especialidad **especialidad**.
	 */
	public Estudio(Especialidad especialidad) {
		this.especialidad = especialidad;
		
	}
	
	/*
	 * POST: Devuelve la especialidad del estudio.
	 */
	public Especialidad obtenerNombre() {
		return especialidad;
	}
}
