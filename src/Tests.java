import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void importar2PacientesDesdeArchivoAlInstanciarBasePacientes() {
		BasePacientes instancia = BasePacientes.obtenerInstancia();
		ArrayList<Paciente> pacientes = instancia.obtenerPacientes();
		
		assertEquals(pacientes.get(0).nombre, "Gian Luca Bellone");
		assertEquals(pacientes.get(1).nombre, "Marcos Macagna");
	}
	
	@Test
	void comprobarDisponibilidadDiaSabadoODomingoNoDisponible() {		
		Medico medico = new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA);
		CalendarioTurnos calendario = new CalendarioTurnos(medico);
		LocalDate sabado = LocalDate.parse("2022-10-29"); 
		LocalDate domingo = LocalDate.parse("2022-10-30");
		LocalTime hora = LocalTime.parse("11:00:00");
		
		
		assertEquals(calendario.comprobarDisponibilidad(sabado, hora), false);
		assertEquals(calendario.comprobarDisponibilidad(domingo, hora), false);
		
	}
	
	@Test
	void comprobarDisponibilidadDiasHabilesDisponible() {
		Medico medico = new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA);
		CalendarioTurnos calendario = new CalendarioTurnos(medico);
		LocalDate lunes = LocalDate.parse("2022-10-24");
		LocalDate martes = LocalDate.parse("2022-10-25"); 
		LocalDate miercoles = LocalDate.parse("2022-10-26"); 
		LocalDate jueves = LocalDate.parse("2022-10-27"); 
		LocalDate viernes = LocalDate.parse("2022-10-28");
		LocalTime hora = LocalTime.parse("11:00:00");
		
		assertEquals(calendario.comprobarDisponibilidad(lunes, hora), true);
		assertEquals(calendario.comprobarDisponibilidad(martes, hora), true);
		assertEquals(calendario.comprobarDisponibilidad(miercoles, hora), true);
		assertEquals(calendario.comprobarDisponibilidad(jueves, hora), true);
		assertEquals(calendario.comprobarDisponibilidad(viernes, hora), true);
	}
	
	@Test
	void comprobarDisponibilidadDiaHabilHorarioNoLaboralNoDisponible() {
		Medico medico = new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA);
		Medico medico2 = new Medico("Favaloro2", TurnoLaboral.TARDE, Especialidad.CARDIOLOGIA);
		
		CalendarioTurnos calendario = new CalendarioTurnos(medico);
		CalendarioTurnos calendario2 = new CalendarioTurnos(medico2);
		
		LocalDate sabado = LocalDate.parse("2022-10-29"); 
		LocalDate domingo = LocalDate.parse("2022-10-30");
		
		LocalTime horaInicioMedico1 = LocalTime.parse("06:00:00");
		LocalTime horaFinMedico1 = LocalTime.parse("16:00:00");
		
		LocalTime horaInicioMedico2 = LocalTime.parse("15:00:00");
		LocalTime horaFinMedico2 = LocalTime.parse("21:00:00");
		
		
		
		assertEquals(calendario.comprobarDisponibilidad(sabado, horaInicioMedico1), false);
		assertEquals(calendario.comprobarDisponibilidad(sabado, horaFinMedico1), false);
		assertEquals(calendario2.comprobarDisponibilidad(domingo, horaInicioMedico2), false);
		assertEquals(calendario2.comprobarDisponibilidad(domingo, horaFinMedico2), false);
		
	}
	
	@Test
	void reservarTurnoParaEstudioCardiologicoYTratamientoOdontologico() {
		LocalDate dia = LocalDate.parse("2022-10-24");
		
		LocalTime hora = LocalTime.parse("12:00:00");
		
		Medico medico = new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA);
		CalendarioTurnos calendario = new CalendarioTurnos(medico);
		
		Prestacion prestacion = new Estudio(Especialidad.CARDIOLOGIA);
		Prestacion prestacion2 = new Tratamiento(Especialidad.ODONTOLOGIA);
		
		Consultorio consultorio = new Laboratorio("Lab1", Especialidad.CARDIOLOGIA);
		Consultorio consultorio2 = new ConsultorioTradicional("Cons1", Especialidad.ODONTOLOGIA);	
		
		calendario.reservarTurno(dia, hora, prestacion, consultorio);
		calendario.reservarTurno(dia, hora, prestacion2, consultorio2);
		
		assertEquals(calendario.obtenerTurnos().get(0).obtenerConsultorio(), consultorio);
		assertEquals(calendario.obtenerTurnos().get(0).obtenerPrestacion(), prestacion);
		assertEquals(calendario.obtenerTurnos().get(1).obtenerConsultorio(), consultorio2);
		assertEquals(calendario.obtenerTurnos().get(1).obtenerPrestacion(), prestacion2);

	}

}
