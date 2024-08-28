package logico;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

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
	public static int generadorCodeEnfermedad = 1;
	public static Clinica clinica = null; 
	private static Usuario usuarioLogueado; 
	
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(generadorCodePaciente);
        out.writeInt(generadorCodeMedico);
        out.writeInt(generadorCodeConsMed);
        out.writeInt(generadorCodeHistMed);
        out.writeInt(generadorCodeVacuna);
        out.writeInt(generadorNumCita);

        out.defaultWriteObject();
    }
	
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        generadorCodePaciente = in.readInt();
        generadorCodeMedico = in.readInt();
        generadorCodeConsMed = in.readInt();
        generadorCodeHistMed = in.readInt();
        generadorCodeVacuna = in.readInt();
        generadorNumCita = in.readInt();

        in.defaultReadObject();
    }

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
	
	public static int getGeneradorCodeEnfermedad() {
		return generadorCodeEnfermedad;
	}
	
	public static void setGeneradorCodeEnfermedad(int generadorCodeEnfermedad) {
		Clinica.generadorCodeEnfermedad = generadorCodeEnfermedad;
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
	
	public void insertarEnfermedad(Enfermedad nuevaEnfermedad) {
		
		misEnfermedades.add(nuevaEnfermedad);
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misEnfermedades.size()+" enfermedades");
		for (Enfermedad enfermedad : misEnfermedades) {
			System.out.println("Nombre: " +enfermedad.getNombre()+ ", Síntomas: " + enfermedad.getSintomas()+ ", Tipo: " +enfermedad.getTipo()+ ", Indice de peligrosidad: " +enfermedad.getIndPeligro());
		}
		
	}
	
	public void insertarConsultaMedica(ConsultaMedica consultaMedica) {
		
		misConsultasMedicas.add(consultaMedica);
		generadorCodeConsMed++;
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misHistorialesMedicos.size() +" consultas médicas");
	}
	
	public void insertarHistorialMedico(HistorialMedico historialMedico) {
		
		misHistorialesMedicos.add(historialMedico);
		generadorCodeHistMed++;
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misHistorialesMedicos.size() +" historiales médicos");
	}
	
	public void insertarUsuario(Usuario usuario) {
		
		misUsuarios.add(usuario);
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misPersonas.size()+" usuarios");
	}
	
	public void insertarCita(Cita cita) {
		
		misCitas.add(cita);
		generadorNumCita++;
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misPersonas.size()+" citas");
	}
	
	public void actualizarPaciente(Paciente paciente) {
		
		int index = buscarIndexPacienteByCode(paciente.getCodePaciente());
		
		misPersonas.set(index, paciente);
	}
	
	public void actualizarUsuario(Usuario usuario) {
		
		int index = buscarIndexUsuario(usuario.getNombreUsuario());
		
		misUsuarios.set(index, usuario);
	}
	
	public void actualizarCita(Cita cita) {
		
		int index = buscarIndexCitaByNum(cita.getNumCita());
		
		misCitas.set(index, cita);
	}
	
	public void eliminarPaciente(Paciente pacienteAEliminar) {
		
		misPersonas.remove(pacienteAEliminar);
	}
	
	public void eliminarMedico(Medico medicoAEliminar) {
		
		misPersonas.remove(medicoAEliminar);
	}
	
	public void eliminarUsuario(Usuario usuarioAEliminar) {
		
		misUsuarios.remove(usuarioAEliminar);
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
	
	public int buscarIndexUsuario(String nombreUsuario) {
		
		boolean encontrado = false;
		int cont = 0, indUsuario = -1;
		
		while (!encontrado && cont < misUsuarios.size()) {
				
			if (misUsuarios.get(cont).getNombreUsuario().equals(nombreUsuario)) {
				encontrado = true;
				indUsuario = cont;
			}
			
			cont++;
		}
		
		return indUsuario;
	}
	
	public int buscarIndexCitaByNum(String numCita) {
		
		boolean encontrado = false;
		int cont = 0, indCita = -1;
		
		while (!encontrado && cont < misCitas.size()) {
				
			if (misCitas.get(cont).getNumCita().equals(numCita)) {
				encontrado = true;
				indCita = cont;
			}
			
			cont++;
		}
		
		return indCita;
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
	
	public Usuario buscarUsuario(String nombreUsuario) {
		
		Usuario usuarioABuscar = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < misUsuarios.size()) {
			
			if (misUsuarios.get(index).getNombreUsuario().equals(nombreUsuario)) {
				usuarioABuscar  = misUsuarios.get(index);
				encontrado = true;
			}
			
			index++;
		}
		
		return usuarioABuscar;
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
	
	public Cita buscarCitaByNum(String numCita) {
		
		Cita citaABuscar = null;
		boolean encontrada = false;
		int index = 0;
		
		while (!encontrada && index < misCitas.size()) {
			
			if (misCitas.get(index).getNumCita().equalsIgnoreCase(numCita)) {
				
				citaABuscar = misCitas.get(index);
				encontrada = true;
				
			}
			
			index++;
		}
		
		return citaABuscar;
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
	
	public Medico buscarMedicoByCedula(String cedula) {
		
		Medico medicoABuscar = null;
		boolean encontrado = false;
		int index = 0;
		
		while (!encontrado && index < misPersonas.size()) {
			
			if (misPersonas.get(index) instanceof Medico) {
				
				if (misPersonas.get(index).getCedula().equalsIgnoreCase(cedula)) {
					medicoABuscar = (Medico) misPersonas.get(index);
					encontrado = true;
				}
				
			}
			
			index++;
		}
		
		return medicoABuscar;
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
	
	public boolean validarUsuario(String userNombre) {
		
		for (Usuario usuario : misUsuarios) {
			if (usuario.getNombreUsuario().equalsIgnoreCase(userNombre)) {
				return true;
			}
			
		}
		return false;
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
	
	public ArrayList<ConsultaMedica> getConsultasImportantesPaciente(String codePaciente) {
		
		ArrayList<ConsultaMedica> consultasPrioritarias = new ArrayList<ConsultaMedica>();
		
		for (ConsultaMedica consulta : misConsultasMedicas) {
			
			if (consulta.getCodePaciente().equalsIgnoreCase(codePaciente)) {
				
				if (consulta.getEnfermedad() != null) {
					
					if (consulta.getEnfermedad().isVigilada()) {
						
						consultasPrioritarias.add(consulta);
					}
				}
				
			}
		
		}
		
		return consultasPrioritarias;
	}
	
	public boolean containsALetter(String str) {
		
		char[] strAAnalizar = str.toCharArray();
		int strLength = strAAnalizar.length;
		boolean containsALetter = false;
		
		for (int index = 0; index < strLength; index++) {
			
			if (!(strAAnalizar[index] >= '0' && strAAnalizar[index] <= '9') && strAAnalizar[index] != '.') {
				containsALetter = true;
			}
			
		}
		
		return containsALetter;
	}
	
	public boolean cedulaValida(String cedula) {
		
		char[] cedulaAAnalizar = cedula.toCharArray();
		boolean documentoValido = true;
		
		if (containsALetter(cedula) || cedulaAAnalizar.length != 11) {
			documentoValido = false;
		}
		
		return documentoValido;
		
	}
	
	public boolean numDeTelefonoValido(String numDeTelefono) {
		
		char[] telefonoAAnalizar = numDeTelefono.toCharArray();
		boolean telefonoValido = true;
		
		if (containsALetter(numDeTelefono) || telefonoAAnalizar.length != 10) {
			telefonoValido = false;
		}
		
		return telefonoValido;
	}
	
	// Funciones para control de Citas
	
	public ArrayList<Cita> citasPendientesByCodeMedico(String codeMedico) {
		
		ArrayList<Cita> citasPendientes = new ArrayList<Cita>();
		
		for (Cita cita : misCitas) {
			
			if (cita.getCodeMedico().equalsIgnoreCase(codeMedico)) {
				
				if (cita.isPendiente()) {
					
					citasPendientes.add(cita);
				}
			}
		}
		
		return citasPendientes;
	}
	
	public void registrarUsuario(Usuario usuario) {
		
		misUsuarios.add(usuario);
		// Sysout de verificación [[Borrar más tarde]]
		System.out.println(misUsuarios.size()+" usuarios");
	}

	
	public ArrayList<Paciente> obtenerPacientesEnVigilancia() {
		ArrayList<Paciente> pacientesVigilados = new ArrayList<Paciente>();
		
		for (HistorialMedico historiales : misHistorialesMedicos) {
			for (Enfermedad enfermedad : historiales.getMisEnfermedades()) {
				if (enfermedad.isVigilada()) {
					pacientesVigilados.add(historiales.getPaciente());
				}
				
			}
		}
		
		return pacientesVigilados;
	}

	public  HistorialMedico buscarHistorialPorPaciente(Paciente paciente, ArrayList<HistorialMedico> historialesMedicos) {
		for (HistorialMedico historial : historialesMedicos) {
	        if (historial.getPaciente().equals(paciente)) {
	            return historial;
	        }
	    }
	    return null;
	}
	
	//Estas son las funciones nuevas. Las demas probablemente tengamos que borrarlas.
	
	public boolean permitirInicioSesion(String nombreUsuario, String contrasena, Connection conexion) {
		
		boolean permitir = false;
		boolean encontrado = false;
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT Cargo, Nombre_Usuario, Pass FROM Administrativo;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next() && encontrado == false) {
            	String cargo = resultSet.getString("Cargo");
                String usuario = resultSet.getString("Nombre_Usuario");
                String contra = resultSet.getString("Pass");
                if(usuario.equals(nombreUsuario) && contra.equals(contrasena)) {
    				usuarioLogueado = new Usuario("", "", "", "", "", "", "", 'x', null, cargo, usuario, "");
    				permitir = true;
    				encontrado = true;
                }
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return permitir;
	}

	public boolean insertarPersona(Connection conexion, String cedula, String primerNombre, String segundoNombre, String primerApellido,
								String segundoApellido, String telefono, String direccion, char sexo, Date fechaDeNacimiento) {
		
		Statement statement;
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = formateador.format(fechaDeNacimiento);  
		
		try {
			statement = conexion.createStatement();
            String insertSql = "INSERT INTO Persona VALUES ('"+cedula+"','"+primerNombre+"','"+segundoNombre+"','"+primerApellido+"', '"+segundoApellido+"', '"+telefono+"', '"+direccion+"', '"+sexo+"', '"+strDate+"');";
            statement.executeUpdate(insertSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
	}
	
	public boolean insertarMedico(Connection conexion, String cedula, String nombreUsuario, String pass) {
		
		Statement statement;
		
		try {
			statement = conexion.createStatement();
            String insertSql = "INSERT INTO Medico (Doc_Identidad, Nombre_Usuario, Pass) VALUES ('"+cedula+"', "+nombreUsuario+", "+pass+");";
            statement.executeUpdate(insertSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public Persona buscarPersonaByCode(Connection conexion, String codigo) {
		
		Persona personaABuscar = null;
		boolean encontrado = false;
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT Doc_Identidad, Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido, Telefono, Direccion, Sexo, Fecha_Nacimiento FROM Persona;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next() && encontrado == false) {
            	
                if(resultSet.getString("Doc_Identidad").equals(codigo)) {
                	personaABuscar = new Persona(resultSet.getString("Doc_Identidad"), resultSet.getString("Primer_Nombre"), resultSet.getString("Segundo_Nombre"), resultSet.getString("Primer_Apellido"), resultSet.getString("Segundo_Apellido"), resultSet.getString("Telefono"), resultSet.getString("Direccion"), resultSet.getString("Sexo").charAt(0), resultSet.getDate("Fecha_Nacimiento"));
    				encontrado = true;
                }
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return personaABuscar;
	}
	
	public Medico buscarMedicoByCode(Connection conexion, String codigo) {
		
		Medico MedicoABuscar = null;
		boolean encontrado = false;
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT Persona.Doc_Identidad, Medico.ID_Medico, Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido, Telefono, Direccion, Sexo, Fecha_Nacimiento FROM Persona INNER JOIN Medico ON Persona.Doc_Identidad = Medico.Doc_Identidad;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next() && encontrado == false) {
            	
                if(resultSet.getString("Doc_Identidad").equals(codigo)) {
                	MedicoABuscar = new Medico(resultSet.getString("Doc_Identidad"), resultSet.getString("Primer_Nombre"), resultSet.getString("Segundo_Nombre"), resultSet.getString("Primer_Apellido"), resultSet.getString("Segundo_Apellido"), resultSet.getString("Telefono"), resultSet.getString("Direccion"), resultSet.getString("Sexo").charAt(0), resultSet.getDate("Fecha_Nacimiento"), resultSet.getInt("ID_Medico"), null, null);
    				encontrado = true;
                }
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return MedicoABuscar;
	}

	public boolean eliminarPersona(Connection conexion, String codigo) {
		
		Statement statement;
		
		try {
			statement = conexion.createStatement();
            String deleteSql = "DELETE FROM Persona WHERE Doc_Identidad = '"+codigo+"';";
            statement.executeUpdate(deleteSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
	}
	
	public boolean eliminarMedico(Connection conexion, int codigo) {
		
		Statement statement;
		
		try {
			statement = conexion.createStatement();
            String deleteSql = "DELETE FROM Medico WHERE ID_Medico = '"+codigo+"';";
            statement.executeUpdate(deleteSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
	}

	public boolean modificarPersona(Connection conexion, String cedula, String primerNombre, String segundoNombre, String primerApellido,
									String segundoApellido, String telefono, String direccion, char sexo, Date fechaDeNacimiento) 
	{
		
		Statement statement;
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate= formateador.format(fechaDeNacimiento);  
		
		try {
			statement = conexion.createStatement();
			String updateSql = "UPDATE Persona SET Primer_Nombre = '"+primerNombre+"', Segundo_Nombre = '"+segundoNombre+"', Primer_Apellido = '"+primerApellido+"', Segundo_Apellido = '"+segundoApellido+"', Telefono = '"+telefono+"', Direccion = '"+direccion+"', Sexo = '"+sexo+"', Fecha_Nacimiento = '"+strDate+"' WHERE Doc_Identidad = '"+cedula+"';";
            statement.executeUpdate(updateSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean insertarEnfermedad(Connection conexion, String tipoEnfermedad, String nombreEnfermedad, boolean vigilancia, Integer mortalidad) 
	{
		int idTipoEnfermedad = obtenerIdTipoEnfermedad(conexion, tipoEnfermedad);
		
		if(idTipoEnfermedad == -1) {
			return false;
		}
		Statement statement;
		
		try {
			statement = conexion.createStatement();
            String insertSql = "INSERT INTO Enfermedad VALUES ('"+idTipoEnfermedad+"','"+nombreEnfermedad+"','"+vigilancia+"','"+mortalidad+"');";
            statement.executeUpdate(insertSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
	}
	
	public boolean modificarEnfermedad(Connection conexion, String tipoEnfermedad, String nombreEnfermedad, boolean vigilancia, Integer mortalidad, int id_Enfermedad) 
	{
		int idTipoEnfermedad = obtenerIdTipoEnfermedad(conexion, tipoEnfermedad);
		
		try {
			Statement statement = conexion.createStatement();
			String updateSql = "UPDATE Enfermedad SET ID_Tipo_Enfermedad = '"+idTipoEnfermedad+"', Nombre_Enfermedad = '"+nombreEnfermedad+"', Vigilancia = '"+vigilancia+"', Peligrosidad = '"+mortalidad+"' WHERE ID_Enfermedad = '"+id_Enfermedad+"';";
            statement.executeUpdate(updateSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean eliminarEnfermedad(Connection conexion, int id_Enfermedad) {
		
		try {
			Statement statement = conexion.createStatement();
			String deleteSQL = "DELETE FROM Enfermedad WHERE ID_Enfermedad = '" + id_Enfermedad + "';";	
			statement.executeUpdate(deleteSQL);
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}


	public int obtenerIdTipoEnfermedad(Connection conexion, String tipoEnfermedad) {
		
		int idTipoEnfermedad = -1;
		String nombreTipoEnfermedad;
		
		try {
			
			Statement statement = conexion.createStatement();
			String selectSQL = "SELECT ID_Tipo_Enfermedad, Nombre_Tipo_Enfermedad FROM Tipo_Enfermedad";
			ResultSet resultSet = statement.executeQuery(selectSQL);
			
            while (resultSet.next()) {
            	nombreTipoEnfermedad = resultSet.getString("Nombre_Tipo_Enfermedad");
            	if(nombreTipoEnfermedad.equals(tipoEnfermedad)) {
            		idTipoEnfermedad = resultSet.getInt("ID_Tipo_Enfermedad");
            		break;
            	}
            }
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al obtener el tipo de enfermedad: "+e.getMessage());
			e.printStackTrace();
		}

		return idTipoEnfermedad;
	}

	public Enfermedad buscarEnfermedadByCode(Connection conexion, int id_Enfermedad) {
		
		Enfermedad enfermedadABuscar = null;
		
		try {
			Statement statement = conexion.createStatement();
			String selectSQL = "SELECT ID_Enfermedad, ID_Tipo_Enfermedad, Nombre_Enfermedad, Vigilancia, Peligrosidad FROM Enfermedad";
			ResultSet resultSet = statement.executeQuery(selectSQL);
			
			while(resultSet.next()) {
				if(resultSet.getInt("ID_Enfermedad") == id_Enfermedad) {
					enfermedadABuscar = new Enfermedad(resultSet.getInt("ID_Enfermedad"), resultSet.getInt("ID_Tipo_Enfermedad"), resultSet.getString("Nombre_Enfermedad"), resultSet.getBoolean("Vigilancia"), resultSet.getInt("Peligrosidad"));
					break;
				}
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return enfermedadABuscar;
	}


	public boolean insertarEspecialidades(Connection conexion, int ID_Medico, ArrayList<Integer> selectedEsp, int tamArr) {
		
		Statement statement;
		
		try {
			statement = conexion.createStatement();
			for(int ind = 0; ind < tamArr; ind++) {
	            String insertSql = "INSERT INTO Med_Especialidad (ID_Medico, ID_Especialidad) VALUES ("+ID_Medico+", "+selectedEsp.get(ind)+");";
	            statement.executeUpdate(insertSql);
			}
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean eliminarEspecialidades(Connection conexion, int codigoMedico) {
		
		Statement statement;
		
		try {
			statement = conexion.createStatement();
			String deleteSql = "DELETE FROM Med_Especialidad WHERE ID_Medico = "+codigoMedico+";";
            statement.executeUpdate(deleteSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
	}
	
	public boolean modificarEspecialidades(Connection conexion, int ID_Medico, ArrayList<Integer> selectedEsp, int tamArr) {
		
		boolean elim = eliminarEspecialidades(conexion, ID_Medico);
		
		if(elim == true) {
			
			elim  = insertarEspecialidades(conexion, ID_Medico, selectedEsp, tamArr);
			
			if(elim == true) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
	}
	
}
