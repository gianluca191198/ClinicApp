import java.util.Scanner;

public class Menu {
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		int opcion = 0;
		int opcion2 = 0;
		int opcion12 = 0;
		int opcion13 = 0;
		int opcion14 = 0;
		int opcion15 = 0;
		int opcion16 = 0;
		String opcion3;
		String opcion4;
		String opcion5;
		String opcion6;
		String opcion7;
		String opcion8;
		String opcion9;
		String opcion10;
		String opcion11;
		
		do {
			System.out.println("Ingresar como: ");
			System.out.println("1. Administrativo");
			System.out.println("2. Médico");
			System.out.println("3. Salir");
			
			System.out.println("Ingrese una opción: ");
			
			opcion = scanner.nextInt();
			
			switch(opcion) {
				case 1:
					System.out.println("Ingrese su ID: ");
					opcion2 = scanner.nextInt();
					System.out.println("1. Turno para consulta");
                    System.out.println("2. Turno para tratamiento");
                    System.out.println("3. Turno para estudio");
                    switch (scanner.nextInt()) {
                    	case 1:
                    		System.out.println("Ingresar especialidad: "); //sale pegado con el print de fecha.
                    		opcion3 = scanner.nextLine();
                    		System.out.println("Ingresar fecha: ");
                    		opcion4 = scanner.nextLine();
                    		System.out.println("Ingresar horario: ");
                    		opcion5 = scanner.nextLine();
                    		System.out.println("Ingresar nombre de paciente: ");
                    		opcion6 = scanner.nextLine();
                    		System.out.println("Ingresar apellido de paciente: ");
                    		opcion7 = scanner.nextLine();
                    		System.out.println("Ingresar DNI de paciente: ");
                    		opcion8 = scanner.nextLine();
                    		System.out.println("Obra social, prepaga o particular: ");
                    		opcion9 = scanner.nextLine();
                    		System.out.println("Nro de tarjeta de credito: ");
                    		opcion10 = scanner.nextLine();
                    		System.out.println("");
                    		System.out.println("Turno creado exitosamente");
                    		System.out.println("");
                    		break;
                    	case 2:
                    		System.out.println("Ingresar especialidad: ");//sale pegado con el print de fecha.
                    		opcion3 = scanner.nextLine();
                    		System.out.println("Ingresar fecha: ");
                    		opcion4 = scanner.nextLine();
                    		System.out.println("Ingresar horario: ");
                    		opcion5 = scanner.nextLine();
                    		System.out.println("Ingresar nombre de paciente: ");
                    		opcion6 = scanner.nextLine();
                    		System.out.println("Ingresar apellido de paciente: ");
                    		opcion7 = scanner.nextLine();
                    		System.out.println("Ingresar DNI de paciente: ");
                    		opcion8 = scanner.nextLine();
                    		System.out.println("Obra social, prepaga o particular: ");
                    		opcion9 = scanner.nextLine();
                    		System.out.println("Nro de tarjeta de credito: ");
                    		opcion10 = scanner.nextLine();
                    		System.out.println("");
                    		System.out.println("Turno creado exitosamente");
                    		System.out.println("");
                    		break;
                    	case 3:
                    		System.out.println("Ingresar estudio: ");//sale pegado con el print de fecha.
                    		opcion3 = scanner.nextLine();
                    		System.out.println("Ingresar fecha: ");
                    		opcion4 = scanner.nextLine();
                    		System.out.println("Ingresar horario: ");
                    		opcion5 = scanner.nextLine();
                    		System.out.println("Ingresar nombre de paciente: ");
                    		opcion6 = scanner.nextLine();
                    		System.out.println("Ingresar apellido de paciente: ");
                    		opcion7 = scanner.nextLine();
                    		System.out.println("Ingresar DNI de paciente: ");
                    		opcion8 = scanner.nextLine();
                    		System.out.println("Obra social, prepaga o particular: ");
                    		opcion9 = scanner.nextLine();
                    		System.out.println("Nro de tarjeta de credito: ");
                    		opcion10 = scanner.nextLine();
                    		System.out.println("");
                    		System.out.println("Turno creado exitosamente");
                    		System.out.println("");
                    		break;
                    	default:
                    		System.out.println("");
                    		System.out.println("Opción invalida");
                    		System.out.println("");
                    }
                    break;	
			
				case 2:
					System.out.println("Ingrese su ID: ");
					opcion2 = scanner.nextInt();
					System.out.println("1. Marcar asistencia del paciente");
                    System.out.println("2. Cargar recetas del paciente");
                    System.out.println("3. Cargar estudios del paciente");
                    switch (scanner.nextInt()) {
                    	case 1:
                    		System.out.println("Ingresar fecha del turno: ");
                    		opcion12 = scanner.nextInt();
                    		System.out.println("Ingresar horario del turno: ");
                    		opcion13 = scanner.nextInt();
                    		System.out.println("Ingresar nombre del paciente: ");//sale pegado con el print del ingreso del apellido.
                    		opcion3 = scanner.nextLine();
                    		System.out.println("Ingresar apellido de paciente: ");
                    		opcion4 = scanner.nextLine();
                    		System.out.println("Ingresar DNI de paciente: ");
                    		opcion14 = scanner.nextInt();
                    		System.out.println("");
                    		System.out.println("Asistencia marcada");
                    		System.out.println("");
                    		break;
                    	case 2:
                    		System.out.println("Ingresar nombre del paciente: "); //sale pegado con el print del ingreso del apellido.
                    		opcion3 = scanner.nextLine();
                    		System.out.println("Ingresar apellido de paciente: ");
                    		opcion4 = scanner.nextLine();
                    		System.out.println("Ingresar DNI de paciente: ");
                    		opcion15 = scanner.nextInt();
                    		System.out.println("");
                    		System.out.println("Recetas cargadas exitosamente");
                    		System.out.println("");
                    		break;
                    	case 3:
                    		System.out.println("Ingresar nombre del paciente: "); //sale pegado con el print del ingreso del apellido.
                    		opcion3 = scanner.nextLine();
                    		System.out.println("Ingresar apellido de paciente: ");
                    		opcion4 = scanner.nextLine();
                    		System.out.println("Ingresar DNI de paciente: ");
                    		opcion16 = scanner.nextInt();
                    		System.out.println("");
                    		System.out.println("Estudios cargados exitosamente");
                    		System.out.println("");
                    		break;
                    	default:
                    		System.out.println("");
                    		System.out.println("Opción invalida");
                    		System.out.println("");
                    }
                    break;	
				
				default:
					System.out.println("");
					if(opcion > 3) {
						System.out.println("Opción invalida");
					}
					System.out.println("");
			}
		} while(opcion != 3);
		
		System.out.println("La aplicación ha finalizado.");
	}
	
}
