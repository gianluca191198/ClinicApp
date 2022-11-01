import java.util.ArrayList;

public abstract class Consultorio {

	public ArrayList<Prestacion> prestaciones = new ArrayList<Prestacion>();
	public ArrayList<Medico> medicos = new ArrayList<Medico>();
	
	public void generarReporte() {}
	
	public void agregarPrestaciones(Prestacion prestacion) {
		prestaciones.add(prestacion);
	}
	
	public void agregarMedicos(Medico medico) {
		medicos.add(medico);
	}
}
