import java.time.LocalDate;
import java.time.LocalTime;

public class Estudio extends Prestacion{
	
	private Especialidad especialidad;
	private Estado estado;
	private LocalDate fecha;
	private LocalTime hora;
	
	public Estudio(Especialidad especialidad, LocalDate fecha, LocalTime hora) {
		this.especialidad = especialidad;
		this.estado = Estado.ASIGNADO;
		this.fecha = fecha;
		this.hora = hora;
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

	public LocalDate obtenerFecha() {
		return fecha;
	}

	public LocalTime obtenerHora() {
		return hora;
	}
}
