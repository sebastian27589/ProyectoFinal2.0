package logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Clinica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8656317760583002498L;
	
	private ArrayList<Vivienda> misViviendas;
	private ArrayList<Cita> misCitas;
	private ArrayList<Persona> misPersonas;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<ConsultaMedica> misConsultasMedicas;
	private ArrayList<HistorialMedico> misHistorialesMedicos;
	private ArrayList<Usuario> misUsuarios;
	public static int generadorCodePaciente = 1;
	public static int generadorCodeMedico = 1;
	public static int generadorCodeConsMed = 1;
	public static int generadorCodeHistMed = 1;
	public static int generadorCodeVacuna = 1;
	public static int generadorNumCita = 1;
	public static Clinica clinica = null; 
	private static Usuario usuarioLogueado; 
	
	public Clinica() {
		super();
		this.misViviendas = new ArrayList<Vivienda>();
		this.misCitas = new ArrayList<Cita>();
		this.misPersonas = new ArrayList<Persona>();
		this.misVacunas = new ArrayList<Vacuna>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misConsultasMedicas = new ArrayList<ConsultaMedica>();
		this.misHistorialesMedicos = new ArrayList<HistorialMedico>();
		this.misUsuarios = new ArrayList<Usuario>();
	}
	
	public static Clinica getClinica() {
		return clinica;
	}
	
	public static void setClinica(Clinica clinica) {
		Clinica.clinica = clinica;
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
	
	public ArrayList<ConsultaMedica> getMisConsultasMedicas() {
		return misConsultasMedicas;
	}

	public void setMisConsultasMedicas(ArrayList<ConsultaMedica> misConsultasMedicas) {
		this.misConsultasMedicas = misConsultasMedicas;
	}

	public ArrayList<HistorialMedico> getMisHistorialesMedicos() {
		return misHistorialesMedicos;
	}

	public void setMisHistorialesMedicos(ArrayList<HistorialMedico> misHistorialesMedicos) {
		this.misHistorialesMedicos = misHistorialesMedicos;
	}
	
	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	public void setMisUsuarios(ArrayList<Usuario> misUsuarios) {
		this.misUsuarios = misUsuarios;
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
	
	public static Usuario getUsuarioLogueado() {
		return usuarioLogueado;
	}
	
	public static void setLoginUser(Usuario usuarioLogueado) {
		Clinica.usuarioLogueado = usuarioLogueado;
	}
	
	public void insertarVivienda(Vivienda vivienda) {
		
		misViviendas.add(vivienda);
		// Sysout de verificaci�n [[Borrar m�s tarde]]
		System.out.println(misViviendas.size()+" viviendas");
	}
	
	public void insertarVacuna(Vacuna vacuna) {
		
		misVacunas.add(vacuna);
		// Sysout de verificaci�n [[Borrar m�s tarde]]
		System.out.println(misVacunas.size()+" vacunas");
		generadorCodeVacuna++;
	}
	
	public void insertarPaciente(Paciente paciente) {
		
		misPersonas.add(paciente);
		generadorCodePaciente++;
		// Sysout de verificaci�n [[Borrar m�s tarde]]
		System.out.println(misPersonas.size()+" pacientes");
	}
	
	public void insertarMedico(Medico medico) {
		
		misPersonas.add(medico);
		generadorCodeMedico++;
		// Sysout de verificaci�n [[Borrar m�s tarde]]
		System.out.println(misPersonas.size()+" medicos");
	}
	
	public void insertarEnfermedad(Enfermedad nuevaEnfermedad) {
		
		misEnfermedades.add(nuevaEnfermedad);
		// Sysout de verificaci�n [[Borrar m�s tarde]]
		System.out.println(misEnfermedades.size()+" enfermedades");
		for (Enfermedad enfermedad : misEnfermedades) {
			System.out.println("Nombre: " +enfermedad.getNombre()+ ", S�ntomas: " + enfermedad.getSintomas()+ ", Tipo: " +enfermedad.getTipo()+ ", Indice de peligrosidad: " +enfermedad.getIndPeligro());
		}
		
	}
	
	public void insertarConsultaMedica(ConsultaMedica consultaMedica) {
		
		misConsultasMedicas.add(consultaMedica);
		generadorCodeConsMed++;
		// Sysout de verificaci�n [[Borrar m�s tarde]]
		System.out.println(misHistorialesMedicos.size() +" consultas m�dicas");
	}
	
	public void insertarHistorialMedico(HistorialMedico historialMedico) {
		
		misHistorialesMedicos.add(historialMedico);
		generadorCodeHistMed++;
		// Sysout de verificaci�n [[Borrar m�s tarde]]
		System.out.println(misHistorialesMedicos.size() +" historiales m�dicos");
	}
	
	public void insertarUsuario(Usuario usuario) {
		
		misUsuarios.add(usuario);
		// Sysout de verificaci�n [[Borrar m�s tarde]]
		System.out.println(misPersonas.size()+" usuarios");
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
	
	public ConsultaMedica buscarConsMedByCode(String codigo) {
		
		ConsultaMedica consultaABuscar = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < misConsultasMedicas.size()) {
			
			if (misConsultasMedicas.get(index).getCodeConsMed().equalsIgnoreCase(codigo)) {
				
				consultaABuscar  = misConsultasMedicas.get(index);
				encontrado = true;
			}
				
			index++;
		}
		
		return consultaABuscar;
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
	
	public HistorialMedico buscarHistMedByCodePaciente(String codigo) {
		
		HistorialMedico histMedABuscar = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < misHistorialesMedicos.size()) {
			
			if (misHistorialesMedicos.get(index).getPaciente().getCodePaciente().equalsIgnoreCase(codigo)) {
				
				histMedABuscar = misHistorialesMedicos.get(index);
				encontrado = true;
				
			}
			
			index++;
		}
		
		return histMedABuscar;
	}
	
    public ArrayList<Enfermedad> enfermedadesDelPaciente(String codePaciente) {
    	
    	Paciente paciente = buscarPacienteByCode(codePaciente);
    	ArrayList<Enfermedad> enfermedades = new ArrayList<Enfermedad>();
    	
    	if (paciente != null) {
    		
        	for (ConsultaMedica consulta : misConsultasMedicas) {
    			
        		if (consulta.getCodePaciente().equalsIgnoreCase(paciente.getCodePaciente())) {
        			
            		if (consulta.getEnfermedad() != null) {
            			
            			enfermedades.add(consulta.getEnfermedad());
            		}
        			
        		}
        
    		}
    		
    	}
    
    	return enfermedades;
    }
	
	public int edadByFechaDeNacim(Date fechaDeNacimiento, Date fechaActual) {
		
	    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
	    int fecha1 = Integer.parseInt(formatter.format(fechaDeNacimiento));                            
	    int fecha2 = Integer.parseInt(formatter.format(fechaActual));                          
	    int edad = (fecha2 - fecha1) / 10000;   
	    
	    return edad;
	}
	
	// Funcionas para login y control de usuarios
	
	public boolean permitirInicioSesion(String nombreUsuario, String contrasena) {
		
		boolean permitir = false;
		
		for (Usuario usuario : misUsuarios) {
			
			if(usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
				
				usuarioLogueado = usuario;
				permitir = true;
			}
		}
		
		return permitir;
	}
	
	public void registrarUsuario(Usuario usuario) {
		
		misUsuarios.add(usuario);
		// Sysout de verificaci�n [[Borrar m�s tarde]]
		System.out.println(misUsuarios.size()+" usuarios");
	}
	
}
