
public class Paciente {
	
	private int dni;
	private String nombre;
	
	/*
	 * Crea un paciente con nombre **nombre** y dni **dni**.
	 */
	public Paciente(String nombre, int dni) {
		this.dni = dni;
		this.nombre = nombre;
	}

	/*
	 * POST: Devuelve el dni del paciente.
	 */
	public int obtenertDni() {
		return dni;
	}

	/*
	 * POST: Devuelve el nombre del paciente.
	 */
	public String obtenerNombre() {
		return nombre;
	}
}
