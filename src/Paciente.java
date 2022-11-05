
public class Paciente {
	
	private int dni;
	private String nombre;
	
	public Paciente(String nombre, int dni) {
		this.dni = dni;
		this.nombre = nombre;
	}

	public int obtenertDni() {
		return dni;
	}

	public String obtenerNombre() {
		return nombre;
	}
}
