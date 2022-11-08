import java.util.ArrayList;

public class RecetaMedica {
	
	private String medicamento;
	
	/*
	 * Crea una receta medica con el medicamento **medicamento**.
	 */
	public RecetaMedica(String medicamento) {
		this.medicamento = medicamento;
	}
	
	/*
	 * POST: Devuelve el medicamento de la receta.
	 */
	public String obtenerMedicamento() {
		return this.medicamento;
	}

}
