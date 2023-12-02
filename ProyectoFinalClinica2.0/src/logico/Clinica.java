package logico;

import java.util.ArrayList;

public class Clinica {

	private ArrayList<Vivienda> misViviendas;
	private ArrayList<Cita> misCitas;
	private ArrayList<Persona> misPersonas;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Enfermedad> misEnfermedades;
	public static int generadorCodePaciente = 1;
	public static int generadorCodeMedico = 1;
	public static int generadorCodeConsMed = 1;
	public static int generadorCodeHistMed = 1;
	public static int generadorCodeVacuna = 1;
	public static int generadorNumCita = 1;
	public static Clinica clinica = null; 
	
	public Clinica() {
		super();
		this.misViviendas = new ArrayList<Vivienda>();
		this.misCitas = new ArrayList<Cita>();
		this.misPersonas = new ArrayList<Persona>();
		this.misVacunas = new ArrayList<Vacuna>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
	}

	public static Clinica getInstance() {
		
		if (clinica == null) {
			clinica = new Clinica();
		}
		
		return clinica;
	}
	
	public ArrayList<Vivienda> getMisViviendas() {
		return misViviendas;
	}
	
	public void setMisViviendas(ArrayList<Vivienda> misViviendas) {
		this.misViviendas = misViviendas;
	}
	
	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}

	public void setMisCitas(ArrayList<Cita> misCitas) {
		this.misCitas = misCitas;
	}

	public ArrayList<Persona> getMisPersonas() {
		return misPersonas;
	}

	public void setMisPersonas(ArrayList<Persona> misPersonas) {
		this.misPersonas = misPersonas;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}
	
	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	
	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}
	
	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}
	
	public static int getGeneradorCodePaciente() {
		return generadorCodePaciente;
	}

	public static void setGeneradorCodePaciente(int generadorCodePaciente) {
		Clinica.generadorCodePaciente = generadorCodePaciente;
	}

	public static int getGeneradorCodeMedico() {
		return generadorCodeMedico;
	}

	public static void setGeneradorCodeMedico(int generadorCodeMedico) {
		Clinica.generadorCodeMedico = generadorCodeMedico;
	}

	public static int getGeneradorCodeConsMed() {
		return generadorCodeConsMed;
	}

	public static void setGeneradorCodeConsMed(int generadorCodeConsMed) {
		Clinica.generadorCodeConsMed = generadorCodeConsMed;
	}

	public static int getGeneradorCodeHistMed() {
		return generadorCodeHistMed;
	}

	public static void setGeneradorCodeHistMed(int generadorCodeHistMed) {
		Clinica.generadorCodeHistMed = generadorCodeHistMed;
	}

	public static int getGeneradorCodeVacuna() {
		return generadorCodeVacuna;
	}

	public static void setGeneradorCodeVacuna(int generadorCodeVacuna) {
		Clinica.generadorCodeVacuna = generadorCodeVacuna;
	}

	public static int getGeneradorNumCita() {
		return generadorNumCita;
	}

	public static void setGeneradorNumCita(int generadorNumCita) {
		Clinica.generadorNumCita = generadorNumCita;
	}

	public void insertarVivienda(Vivienda vivienda) {
		
		misViviendas.add(vivienda);
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misViviendas.size()+" viviendas");
	}
	
	public void insertarVacuna(Vacuna vacuna) {
		
		misVacunas.add(vacuna);
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misVacunas.size()+" vacunas");
		generadorCodeVacuna++;
	}
	
	public void insertarPaciente(Paciente paciente) {
		
		misPersonas.add(paciente);
		generadorCodePaciente++;
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misPersonas.size()+" pacientes");
	}
	
	public void insertarMedico(Medico medico) {
		
		misPersonas.add(medico);
		generadorCodeMedico++;
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misPersonas.size()+" medicos");
	}
	
	public void insertarEnfermedad(Enfermedad nuevaEnfermedad) {
		
		misEnfermedades.add(nuevaEnfermedad);
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misEnfermedades.size()+" enfermedades");
		for (Enfermedad enfermedad : misEnfermedades) {
			System.out.println("Nombre: " +enfermedad.getNombre()+ ", Síntomas: " + enfermedad.getSintomas()+ ", Tipo: " +enfermedad.getTipo()+ ", Indice de peligrosidad: " +enfermedad.getIndPeligro());
		}
		
	}
	
	public void actualizarEnfermedad(Enfermedad enfermedad) {
		int index = buscarIndexEnfermedadByNombre(enfermedad.getNombre());
		misEnfermedades.set(index, enfermedad);
	}
	
	public void actualizarPaciente(Paciente paciente) {
		
		int index = buscarIndexPacienteByCode(paciente.getCodePaciente());
		
		misPersonas.set(index, paciente);
	}
	
	public void actualizarMedico(Medico medico) {
		
		int index = buscarIndexMedicoByCode(medico.getCodeMedico());
		
		misPersonas.set(index, medico);
	}
	
	public void eliminarPaciente(Paciente pacienteAEliminar) {
		
		misPersonas.remove(pacienteAEliminar);
	}
	
	public void eliminarMedico(Medico medicoAEliminar) {
		
		misPersonas.remove(medicoAEliminar);
	}
	
	public int buscarIndexEnfermedadByNombre(String nombreEnfermedad) {
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misEnfermedades.size()) {
			if(misEnfermedades.get(i).getNombre().equalsIgnoreCase(nombreEnfermedad)){
				encontrado = true;
				index = i;
			}
			i++;
			
		}
		return index;
	}
	
	public int buscarIndexPacienteByCode(String codigo) {
		
		boolean encontrado = false;
		int cont = 0, indPaciente = -1;
		
		while (!encontrado && cont < misPersonas.size()) {
			
			if (misPersonas.get(cont) instanceof Paciente) {
				
				if (((Paciente) misPersonas.get(cont)).getCodePaciente().equalsIgnoreCase(codigo)) {
					encontrado = true;
					indPaciente = cont;
				}
			}
			
			cont++;
		}
		
		return indPaciente;
	}
	
	public int buscarIndexMedicoByCode(String codigo) {
		
		boolean encontrado = false;
		int cont = 0, indMedico = -1;
		
		while (!encontrado && cont < misPersonas.size()) {
			
			if (misPersonas.get(cont) instanceof Medico) {
				
				if (((Medico) misPersonas.get(cont)).getCodeMedico().equalsIgnoreCase(codigo)) {
					encontrado = true;
					indMedico = cont;
				}
			}
		
			cont++;
		}
		
		return indMedico;
	}

	public Vacuna buscarVacunaByCode(String codigo) {
		
		Vacuna vacunaABuscar = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < misVacunas.size()) {
			
			if (misVacunas.get(index).getCodeVacuna().equalsIgnoreCase(codigo)) {
				
				vacunaABuscar = misVacunas.get(index);
				encontrado = true;
				
			}
			
			index++;
		}
		
		return vacunaABuscar;
	}
	
	public Paciente buscarPacienteByCode(String codigo) {
		
		Paciente pacienteABuscar = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < misPersonas.size()) {
			
			if (misPersonas.get(index) instanceof Paciente) {
				
				if (((Paciente) misPersonas.get(index)).getCodePaciente().equalsIgnoreCase(codigo)) {
					pacienteABuscar = (Paciente) misPersonas.get(index);
					encontrado = true;
				}
				
			}
			
			index++;
		}
		
		return pacienteABuscar;
	}
	
	public Medico buscarMedicoByCode(String codigo) {
		
		Medico medicoABuscar = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < misPersonas.size()) {
			
			if (misPersonas.get(index) instanceof Medico) {
				
				if (((Medico) misPersonas.get(index)).getCodeMedico().equalsIgnoreCase(codigo)) {
					medicoABuscar  = (Medico) misPersonas.get(index);
					encontrado = true;
				}
				
			}
			
			index++;
		}
		
		return medicoABuscar;
	}
	
	public Paciente buscarPacienteByCedula(String cedula) {
		
		Paciente pacienteABuscar = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < misPersonas.size()) {
			
			if (misPersonas.get(index) instanceof Paciente) {
				
				if (misPersonas.get(index).getCedula().equalsIgnoreCase(cedula)) {
					pacienteABuscar = (Paciente) misPersonas.get(index);
					encontrado = true;
				}
				
			}
			
			index++;
		}
		
		return pacienteABuscar;
	}
	
	public Vivienda buscarViviendaByNum(String num) {
		
		Vivienda viviendaABuscar = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < misViviendas.size()) {
			
			if (misViviendas.get(index).getNumero().equalsIgnoreCase(num)) {
				
				viviendaABuscar = misViviendas.get(index);
				encontrado = true;
		
			}
			
			index++;
		}
		
		return viviendaABuscar;
	}

	public Enfermedad buscarEnfermedadByNombre(String nombreEnfermedad) {
		
		Enfermedad enfermedadABuscar = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < misEnfermedades.size()) {
			if (misEnfermedades.get(index).getNombre().equalsIgnoreCase(nombreEnfermedad)) {
				enfermedadABuscar = misEnfermedades.get(index);
				encontrado = true;
			}
			
			index++;
		}
		
		return enfermedadABuscar;
	}

	public void consultaRealizada() {
		
		generadorCodeConsMed++;
	}
	
}
