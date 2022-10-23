import static org.junit.jupiter.api.Assertions.*;

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

}
