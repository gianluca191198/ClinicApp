import java.util.ArrayList;

public abstract class Prestacion {
	
	protected Especialidad ESPECIALIDAD = null;
	protected boolean activa = false;
	
	public void camibiarEstado() {
		this.activa = !this.activa; 
	}
	
	public Especialidad obtenerEspecialidad() {
		return this.ESPECIALIDAD;
	}
	public boolean estaActiva() {
		return activa;
	}

}