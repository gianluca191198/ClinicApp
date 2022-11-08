import java.util.ArrayList;

public class Tratamiento extends Prestacion{
	
	private Estado estado;
	private ArrayList<RecetaMedica> recetas;
	
	/*
	 * Crea un tratamiento con la especialidad **especialidad**.
	 */
	public Tratamiento(Especialidad especialidad) {
		this.ESPECIALIDAD = especialidad;
		this.estado = Estado.ASIGNADO;
	}

	/*
	 * POST: Devuelve el estado del tratamiento.
	 */
	public Estado obtenerEstado() {
		return estado;
	}

	/*
	 * PRE: **estado** debe ser uno de los que se encuentran en el enum ESTADO.
	 * POST: Cambia el tratamiento al estado de **estado**.
	 */
	public void cambiarEstado(Estado estado) {
		this.estado = estado;
	}
	
	/*
	 * POST: Elimina la receta con el medicamento **medicamento** del ArrayList de recetas.
	 */
	public void eliminarReceta(String medicamento) {
		for(int i = 0; i < recetas.size(); i++) {
			if(recetas.get(i).obtenerMedicamento()== medicamento) {
				recetas.remove(i);
			}
		}
	}
	
	/*
	 * POST: Agrega la receta **receta** al ArrayList de recetas.
	 */
	public void agregarReceta(RecetaMedica receta) {
		recetas.add(receta);
	}
	
}
