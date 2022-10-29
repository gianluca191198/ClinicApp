import java.util.ArrayList;

public class Tratamiento extends Prestacion{
	
	private Estado estado;
	private ArrayList<RecetaMedica> recetas;
	
	public Tratamiento(Especialidad especialidad) {
		this.ESPECIALIDAD = especialidad;
		this.estado = Estado.ASIGNADO;
	}

	public Estado obtenerEstado() {
		return estado;
	}

	public void cambiarEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void eliminarReceta(String medicamento) {
		for(int i = 0; i < recetas.size(); i++) {
			if(recetas.get(i).obtenerMedicamento()== medicamento) {
				recetas.remove(i);
			}
		}
	}
	
	public void agregarReceta(RecetaMedica receta) {
		recetas.add(receta);
	}
	
}
