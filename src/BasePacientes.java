import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BasePacientes {
	
	private ArrayList<Paciente> pacientes;
	private static String RUTA = ".\\Recursos\\Pacientes.TXT";
	
	private static BasePacientes instancia = null;
	
	/*
	 * Escanea el archivo .txt donde se encuentran los pacientes.
	 */
	private BasePacientes() {
		int posicion = 0;
		try {
			File doc = new File(RUTA);			
			Scanner scanner = new Scanner(doc);
			pacientes = new ArrayList<Paciente>();
			
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				StringTokenizer tokens = new StringTokenizer(linea, ";");
				agregarPaciente(tokens.nextToken(), Integer.parseInt(tokens.nextToken()));
				posicion++;
			}
		}catch(FileNotFoundException e){
			System.out.println("Archivo no encontrado en la ruta: " + RUTA);
		}
	}
	
	/*
	 * Crea una base de pacientes.
	 */
	public static BasePacientes obtenerInstancia() {
		if(instancia == null) {
			instancia = new BasePacientes();
		}
		return instancia;
	}
	
	/*
	 * POST: Agrega un paciente de nombre **nombre** y dni **dni** al ArrayList de pacientes y lo devuelve.
	 */
	public Paciente agregarPaciente(String nombre, int dni) {
		Paciente paciente = null;
		if(buscarPaciente(dni) == null) {
			paciente = new Paciente(nombre, dni);
			pacientes.add(paciente);
		}
		return paciente;
		
	}
	
	/*
	 * POST: Guarda la base de datos actual de pacientes.
	 */
	public void guardarPacientes() {
        FileWriter escribir;
		try {
			escribir = new FileWriter(RUTA, false);
			
			for (int i = 0; i < pacientes.size(); i++) {
				Paciente paciente = pacientes.get(i);
				escribir.write( paciente.obtenerNombre() + ";" + Integer.toString(paciente.obtenertDni()));
				escribir.write("\r\n");
			}
			
			escribir.close();
		} catch (IOException e) {
			System.out.println("Archivo no encontrado en la ruta: " + RUTA);
		}
	}
	
	/*
	 * POST: Devuelve los pacientes del ArrayList de pacientes.
	 */
	public ArrayList<Paciente> obtenerPacientes() {
		return pacientes;
	}
	
	/*
	 * POST: Elimina un paciente con dni **dni** del ArrayList de pacientes y lo devuelve.
	 */
	public Paciente eliminarPaciente(int dni) {
		Paciente paciente = null;
        for(int i = 0 ; i < pacientes.size();i++) {
            if(pacientes.get(i).obtenertDni() == dni) {
            	paciente = pacientes.get(i);
            	pacientes.remove(i);
            }
        }
        return paciente;
	}
	
	/*
	 * POST: Busca si el paciente con dni **dni** se encuentra en el ArrayList de pacientes y lo devuelve.
	 */
    public Paciente buscarPaciente(int dni) {
    	Paciente paciente = null;
        for(int i = 0 ; i < pacientes.size();i++) {
            if(pacientes.get(i).obtenertDni() == dni) {
            	paciente = pacientes.get(i);
            }
        }
        
		return paciente;
    }
}
