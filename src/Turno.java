import java.sql.Date;

public class Turno {
	
	private boolean esSobreTurno = false;
	//private Consultorio consultorio;
	private Date fechaInicio;
	private Date fechaFin;
	private Medico medico;
	private boolean asistio = false;
	//private Prestacion prestacion;
	
	public Turno(Date inicio, Date fin, Medico medico) {
		this.fechaInicio = inicio;
		this.fechaFin = fin;
		this.medico = medico;
		//prestacion
	}
	
	public void marcarAsistencia() {
		this.asistio = true;
	}
	
	public boolean asistio() {
		return asistio;
	}
	
	public void marcarSobreTurno() {
		this.esSobreTurno = true;
	}
	
	public boolean esSobreTurno() {
		return esSobreTurno;
	}
}
