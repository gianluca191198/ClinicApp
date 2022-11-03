import java.time.LocalDate;
import java.time.LocalTime;

public class Estudio extends Prestacion{
	
	private Especialidad especialidad;
	private Estado estado;
	
	public Estudio(Especialidad especialidad) {
		this.especialidad = especialidad;
		this.estado = Estado.ASIGNADO;
	}
	public Especialidad obtenerNombre() {
		return especialidad;
	}

	public Estado obtenerEstado() {
		return estado;
	}

	public void cambiarEstado(Estado estado) {
		this.estado = estado;
	}
}
