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
		
		assertEquals(pacientes.get(0).obtenerNombre(), "Gian Luca Bellone");
		assertEquals(pacientes.get(1).obtenerNombre(), "Marcos Macagna");
	}
	
	@Test
	void comprobarDisponibilidadDiaSabadoODomingoNoDisponible() {		
		Consultorio consultorioCardiologia = new ConsultorioTradicional("Consultorio de Cardiologia", Especialidad.CARDIOLOGIA);
		Medico medico = new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA, consultorioCardiologia, 1);
		CalendarioTurnos calendario = new CalendarioTurnos(medico);
		LocalDate sabado = LocalDate.parse("2022-10-29"); 
		LocalDate domingo = LocalDate.parse("2022-10-30");
		LocalTime hora = LocalTime.parse("11:00:00");
		
		
		assertEquals(calendario.comprobarDisponibilidad(sabado, hora), false);
		assertEquals(calendario.comprobarDisponibilidad(domingo, hora), false);
		
	}
	
	@Test
	void comprobarDisponibilidadDiasHabilesDisponible() {
		Consultorio consultorioCardiologia = new ConsultorioTradicional("Consultorio de Cardiologia", Especialidad.CARDIOLOGIA);
		Medico medico = new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA, consultorioCardiologia, 1);
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
		Consultorio consultorioCardiologia = new ConsultorioTradicional("Consultorio de Cardiologia", Especialidad.CARDIOLOGIA);
		
		Medico medico = new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA, consultorioCardiologia, 1);
		Medico medico2 = new Medico("Favaloro2", TurnoLaboral.TARDE, Especialidad.CARDIOLOGIA, consultorioCardiologia, 2);
		
		CalendarioTurnos calendario = medico.obtenerCalendarioTurnos();
		CalendarioTurnos calendario2 = medico2.obtenerCalendarioTurnos();
		
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
		
		Paciente paciente = new Paciente("Messi", 30000000);
		
		Consultorio consultorio = new Laboratorio("Lab1", Especialidad.CARDIOLOGIA);
		Consultorio consultorio2 = new ConsultorioTradicional("Cons1", Especialidad.ODONTOLOGIA);	
		
		Medico medico = new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA, consultorio, 1);
		Medico medico2 = new Medico("Favaloro", TurnoLaboral.MANIANA, Especialidad.CARDIOLOGIA, consultorio2, 2);
		CalendarioTurnos calendario = medico.obtenerCalendarioTurnos();
		
		Prestacion prestacion = new Estudio(Especialidad.CARDIOLOGIA);
		Prestacion prestacion2 = new Tratamiento(Especialidad.ODONTOLOGIA);
		
		
		medico.obtenerCalendarioTurnos().reservarTurno(dia, hora, paciente, prestacion, consultorio);
		medico2.obtenerCalendarioTurnos().reservarTurno(dia, hora, paciente, prestacion2, consultorio2);
		
		assertEquals(medico.obtenerCalendarioTurnos().obtenerTurnos().get(0).obtenerConsultorio(), consultorio);
		assertEquals(medico.obtenerCalendarioTurnos().obtenerTurnos().get(0).obtenerPrestacion(), prestacion);
		assertEquals(medico2.obtenerCalendarioTurnos().obtenerTurnos().get(0).obtenerConsultorio(), consultorio2);
		assertEquals(medico2.obtenerCalendarioTurnos().obtenerTurnos().get(0).obtenerPrestacion(), prestacion2);
	}
	
	@Test
	void generarReporteExitoso() {
		App app = new App();
		app.agregarPaciente("Lionel Messi", 30000000);
		app.agregarPaciente("Lautaro Martinez", 20000000);
		app.agregarPaciente("Rodrigo De Paul", 10000000);
		
		Turno turno1 = app.reservarTurnoEstudio(LocalDate.parse("2022-11-18"), LocalTime.parse("12:00:00") , 30000000, Especialidad.RADIOGRAFIAS);
		Turno turno2 = app.reservarTurnoEstudio(LocalDate.parse("2022-11-18"), LocalTime.parse("11:00:00") , 20000000, Especialidad.RADIOGRAFIAS);
		Turno turno3 = app.reservarTurnoEstudio(LocalDate.parse("2022-11-18"), LocalTime.parse("13:00:00") , 10000000, Especialidad.RADIOGRAFIAS);
		
		Medico medico = app.obtenerMedicoPorEspecialidadYTurno(Especialidad.RADIOGRAFIAS, TurnoLaboral.MANIANA);
		
		medico.cambiarEstadoTurno(turno1.obtenerNumeroTurno(), Estado.CUMPLIDO);
		medico.cambiarEstadoTurno(turno2.obtenerNumeroTurno(), Estado.CUMPLIDO);
		medico.cambiarEstadoTurno(turno3.obtenerNumeroTurno(), Estado.CUMPLIDO);
				
		medico.generarReportePrestacionesBrindadas();
		
	}

}
