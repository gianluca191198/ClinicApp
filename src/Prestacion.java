import java.util.ArrayList;

public abstract class Prestacion {
	
	protected Especialidad ESPECIALIDAD = null;
	protected boolean activa = false;
	
	/*
	 * POST: Cambia el estado actual de la prestación.
	 */
	public void camibiarEstado() {
		this.activa = !this.activa; 
	}
	
	/*
	 * POST: Devuelve la especialidad de la prestacion.
	 */
	public Especialidad obtenerEspecialidad() {
		return this.ESPECIALIDAD;
	}
	
	/*
	 * POST: Devuelve si la prestación esta activa o no.
	 */
	public boolean estaActiva() {
		return activa;
	}

}