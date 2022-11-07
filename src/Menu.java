import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.naming.AuthenticationException;

public class Menu {
	
	static App app = new App();
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sn = new Scanner(System.in);
	static boolean autenticacionEnCurso = true;
	static boolean menuMedico = true;
	static boolean continuarMenuAdministrativo = true;
	static Especialidad especialidadElegida;
	static LocalDate fechaElegida;
	static LocalTime horaElegida;
	static Paciente paciente;
	static boolean salir = false;
	static int intentos = 0;
	static Medico medico;
	
	public static void main(String[] args) {
		verMenu();
	}	
	
	public static void verMenu() {
        int opcion;
 
        while (!salir) {
        	continuarMenuAdministrativo = true;
        	System.out.println("Bienvenido a ClinicApp!");
			System.out.println("Ingresar como: ");
			System.out.println("1. Administrativo");
			System.out.println("2. Médico");
			System.out.println("3. Salir");
			
            try {
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1:
                    	verMenuAdministrativo();
                    	break;
                    case 2:
                    	verMenuMedico();
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
                sn.next();
            } 
            catch(AuthenticationException e){
    			System.out.println(e.getMessage());
    			System.out.println("Ingrese cualquier tecla para volver al menu");
    			intentos = 0;
    			sn.next();
    		}
        }
	}
	
	public static void verMenuAdministrativo() throws AuthenticationException, IOException {
		
		String nombre;
		int dni;
		if(autenticacionEnCurso) {
			System.out.println("Ingrese el ID: ");
		}
    	while(autenticacionEnCurso) {
    		autenticacionEnCurso = !app.verificarClaveAdmin(sn.nextInt());      
    		intentos++;
    		if(intentos >= 3 ) {
    			throw new AuthenticationException("Error al ingresar. Demasiados intentos fallidos");
    		}
    	}
    	while(continuarMenuAdministrativo) {
    		System.out.println("1. Reservar turno");
    		System.out.println("2. Guardar pacientes");
    		System.out.println("3. Volver al menu principal");
    		switch (sn.nextInt()) {
    		case 1:
    			System.out.println("1. Paciente existente");
    			System.out.println("2. Paciente nuevo");
    			switch (sn.nextInt()) {
    			case 1:
    				System.out.println("Ingresar DNI del paciente");
    				paciente = app.buscarPaciente(sn.nextInt());
    				System.out.println("Paciente: " + paciente.obtenerNombre());
    				if(paciente == null) {
    					System.out.println("Paciente no encontrado");
    					break;
    				}
    				verMenuTurnos();
    				break;
    			case 2:
    				System.out.println("Ingrese DNI del paciente");
    				dni = sn.nextInt();
    				System.out.println("Ingrese nombre del paciente");
    				nombre = input.readLine();
    				paciente = app.agregarPaciente(nombre, dni);
    				if(paciente == null) {
    					System.out.println("Ya existe un paciente con el dni ingresado");
    					break;
    				}
    				System.out.println("Paciente creado exitosamente");
    				verMenuTurnos();
    				break;
    			default:
    				break;
    			}
    			break;
			case 2:
				app.guardarPacientes();
				break;
    		case 3:
    			continuarMenuAdministrativo = false;
    			break;
    		}
    	}
	}
	
	public static void verMenuMedico() {
		Medico medico = null;
		Turno turno = null;
		while(medico == null) {
			System.out.println("Ingrese su ID de Medico:");
			medico = app.verificarMedico(sn.nextInt());
			if (medico == null) {
				System.out.println("Id incorrecto");
			}
		}
		System.out.println("Bienvenido " + medico.obtenerNombre());
    	while(menuMedico) {
    		System.out.println("1. Gestionar turno");
    		System.out.println("2. Generar reporte prestaciones brindadas");
    		System.out.println("3. Volver al menu principal");
    		switch (sn.nextInt()) {
    		case 1:
    			System.out.println("Ingresar numero de turno");
    			turno = medico.obtenerTurnoPorNumero(sn.nextInt());
    			if(turno == null) {
    				System.out.println("Turno no encontrado");
    				break;
    			}
    			System.out.println("Turno el dia "+ turno.obtenerFecha() + " a las " + turno.obtenerHora() + " con el paciente " + turno.obtenerPaciente().obtenerNombre());
    			System.out.println("1. Marcar asistencia");
    			System.out.println("2. Marcar inasistencia");
    			System.out.println("3. Cancelar turno");
    			System.out.println("4.Volver");
    			switch (sn.nextInt()) {
    			case 1:
    				medico.cambiarEstadoTurno(turno.obtenerNumeroTurno(), Estado.CUMPLIDO);
    				System.out.println("Turno cumplido");
    				break;
    			case 2:
    				medico.cambiarEstadoTurno(turno.obtenerNumeroTurno(), Estado.AUSENTE);
    				System.out.println("Paciente ausente");
    				break;
    			case 3:
    				medico.cambiarEstadoTurno(turno.obtenerNumeroTurno(), Estado.CANCELADO);
    				System.out.println("Turno cancelado");
    				break;
    			case 4:
    				break;
    			}
    			break;
    		case 2:
    			medico.generarReportePrestacionesBrindadas();
    			break;
    		case 3:
    			menuMedico = false;
    			break;
    		}
    	}
	}
	
	public static void verMenuTurnos() throws IOException {
		
		System.out.println("1. Turno para tratamiento");
		System.out.println("2. Turno para estudio");
		System.out.println("3. Volver al menu principal");
		switch (sn.nextInt()) {
		case 1:
			System.out.println("Seleccionar especialidad ");
			System.out.println("1. Cardiologia");
			System.out.println("2. Odontologia");
			System.out.println("3. Traumatologia");
			System.out.println("4. Clinica");
			switch (sn.nextInt()) {
			case 1:
				especialidadElegida = Especialidad.CARDIOLOGIA;
				break;
			case 2:
				especialidadElegida = Especialidad.ODONTOLOGIA;
				break;
			case 3:
				especialidadElegida = Especialidad.TRAUMATOLOGIA;
				break;
			case 4:
				especialidadElegida = Especialidad.CLINICA;
				break;
			default: 
				System.out.println("Opcion incorrecta");
			}
			verMenuFechaYHora();
			Turno turnoTratamiento = app.reservarTurnoTratamiento(fechaElegida, horaElegida, paciente.obtenertDni(), especialidadElegida);
			if(turnoTratamiento != null) {
				System.out.println("Turno reservado exitosamente!");
				System.out.println("Numero de turno: " + turnoTratamiento.obtenerNumeroTurno());
			}else {
				System.out.println("Turno no disponible en la fecha y hora seleccionada");
			}
			break;
		case 2:
			System.out.println("Seleccionar especialidad ");
			System.out.println("1. Extraccion de sangre");
			System.out.println("2. Resonancia magnetica");
			System.out.println("3. Radiografia");
			switch (sn.nextInt()) {
			case 1:
				especialidadElegida = Especialidad.EXTRACCION_SANGRE;
				break;
			case 2:
				especialidadElegida = Especialidad.RESONANCIA_MAGNETICA;
				break;
			case 3:
				especialidadElegida = Especialidad.RADIOGRAFIAS;
				break;
			default: 
				System.out.println("Opcion incorrecta");
			}
			verMenuFechaYHora();
			Turno turnoEspecialidad = app.reservarTurnoTratamiento(fechaElegida, horaElegida, paciente.obtenertDni(), especialidadElegida);
			if(turnoEspecialidad != null) {
				System.out.println("Turno reservado exitosamente!");
				System.out.println("Numero de turno: " + turnoEspecialidad.obtenerNumeroTurno());
			}else {
				System.out.println("Turno no disponible en la fecha y hora seleccionada");
			}break;
		case 3:
			continuarMenuAdministrativo = false;
			break;
		}
	}
	
	public static void verMenuFechaYHora() throws IOException {
		fechaElegida = null;
		horaElegida = null;
		try {
			System.out.println("Ingresar fecha (AAAA-MM-DD):"); 
			fechaElegida = LocalDate.parse(input.readLine());
			System.out.println("Ingresar hora (HH:MM:SS):"); 
			horaElegida = LocalTime.parse(input.readLine());		
		}catch(DateTimeParseException e) {
	    	System.out.println("Fecha u hora con formato incorrecto");
	    	continuarMenuAdministrativo = true;
	    }
	}
	
}
