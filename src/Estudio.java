import java.time.LocalDate;
import java.time.LocalTime;

public class Estudio extends Prestacion{
	
	private Especialidad especialidad;
	
	public Estudio(Especialidad especialidad) {
		this.especialidad = especialidad;
		
	}
	public Especialidad obtenerNombre() {
		return especialidad;
	}
}
