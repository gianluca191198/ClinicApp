import java.util.ArrayList;

public class Medico {
	
	private String nombre;
	private ArrayList<TurnoLaboral> turnoLaboral = new ArrayList<TurnoLaboral>();
	
	public void agregarTurnosLaborales(TurnoLaboral turno) {
		turnoLaboral.add(turno);
	}
	
}
