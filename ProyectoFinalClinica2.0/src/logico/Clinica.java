package logico;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class Clinica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8656317760583002498L;
	
	private ArrayList<Cita> misCitas;
	private ArrayList<Persona> misPersonas;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<ConsultaMedica> misConsultasMedicas;
	private ArrayList<Usuario> misUsuarios;
	private int idUsuarioLogueado;
	public static int generadorCodePaciente = 1;
	public static int generadorCodeMedico = 1;
	public static int generadorCodeConsMed = 1;
	public static int generadorCodeHistMed = 1;
	public static int generadorCodeVacuna = 1;
	public static int generadorNumCita = 1;
	public static int generadorCodeEnfermedad = 1;
	public static int generadorCodeUsuario = 1;
	public static Clinica clinica = null; 
	private static Usuario usuarioLogueado; 

	public Clinica() {
		super();
		this.misCitas = new ArrayList<Cita>();
		this.misPersonas = new ArrayList<Persona>();
		this.misVacunas = new ArrayList<Vacuna>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misConsultasMedicas = new ArrayList<ConsultaMedica>();
		this.misUsuarios = new ArrayList<Usuario>();
	}
	
	public static void setClinica(Clinica clinica) {
		Clinica.clinica = clinica;
	}
	
	public static Clinica getClinica() {
		return clinica;
	}

	public static Clinica getInstance() {
		
		if (clinica == null) {
			clinica = new Clinica();
		}
				
		return clinica;
	}
	
    public static int getGeneradorCodeUsuario() {
        return generadorCodeUsuario;
    }

    public static void setGeneradorCodeUsuario(int generadorCodeUsuario) {
        Clinica.generadorCodeUsuario = generadorCodeUsuario;
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
	
	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	public void setMisUsuarios(ArrayList<Usuario> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}
	
	public int getIdUsuarioLogueado() {
		return idUsuarioLogueado;
	}

	public void setIdUsuarioLogueado(int idUsuarioLogueado) {
		this.idUsuarioLogueado = idUsuarioLogueado;
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
	
	//Estas son las funciones nuevas. Las demas probablemente tengamos que borrarlas.
	
	public int permitirInicioSesion(String nombreUsuario, String contrasena, Connection conexion) {
		
		int permitir = -1;
		int encontrado = -1;
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Administrativo, Persona.Doc_Identidad, CONCAT(Persona.Primer_Nombre, ' ', Persona.Segundo_Nombre, ' ', Persona.Primer_Apellido, ' ', Persona.Segundo_Apellido) AS NombreCompleto, Cargo, Nombre_Usuario, Pass FROM Administrativo JOIN Persona ON Persona.Doc_Identidad = Administrativo.Doc_Identidad;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next() && encontrado == -1) {
            	String cargo = resultSet.getString("Cargo");
                String usuario = resultSet.getString("Nombre_Usuario");
                String contra = resultSet.getString("Pass");
                String nomCompleto = resultSet.getString("NombreCompleto");
                
                if(usuario.equals(nombreUsuario) && contra.equals(contrasena)) {
                	Clinica.getInstance().setIdUsuarioLogueado(resultSet.getInt("ID_Administrativo"));
    				usuarioLogueado = new Usuario("", "", "", "", "", "", "", 'x', null, cargo, nomCompleto, "");
    				permitir = 0;
    				encontrado = 1;
                }
            }
            
            if(encontrado == -1) {
            	selectSql = "SELECT ID_Medico, Persona.Doc_Identidad, CONCAT(Persona.Primer_Nombre, ' ', Persona.Segundo_Nombre, ' ', Persona.Primer_Apellido, ' ', Persona.Segundo_Apellido) AS NombreCompleto, Nombre_Usuario, Pass FROM Medico JOIN Persona ON Persona.Doc_Identidad = Medico.Doc_Identidad;";
            	resultSet = statement.executeQuery(selectSql);
            	
                while (resultSet.next() && encontrado == -1) {
                    String contra = resultSet.getString("Pass");
                    String usuario = resultSet.getString("Nombre_Usuario");
                    String nomCompleto = resultSet.getString("NombreCompleto");
                	
                	if(resultSet.getString("Nombre_Usuario") == null) {
                		usuario = "Patata";
                		contra = "Patata";
                		nomCompleto = "Patata";
                	}
                    
                    if(usuario.equals(nombreUsuario) && contra.equals(contrasena)) {
                    	Clinica.getInstance().setIdUsuarioLogueado(resultSet.getInt("ID_Medico"));
        				usuarioLogueado = new Usuario("", "", "", "", "", "", "", 'x', null, "Médico", nomCompleto, "");
        				permitir = 1;
        				encontrado = 1;
                    }
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
	
	public boolean insertarCita(Connection conexion, Integer ID_Cita, String cedula, int ID_Administrativo, int ID_Medico, Date fechaCita, String hora) {

		Statement statement;
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = formateador.format(fechaCita);  
		
		try {
			statement = conexion.createStatement();
            String insertSql = "INSERT INTO Cita (Doc_Identidad, ID_Administrativo, ID_Medico, Fecha_Cita, Hora_Cita, Pendiente) VALUES ('"+cedula+"', "+ID_Administrativo+", "+ID_Medico+", '"+strDate+"', '"+hora+"', 1);";
            statement.executeUpdate(insertSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean insertaConsulta(Connection conexion, String cedulaPersona, Integer altura, Integer peso, String alergia,
			int idMedico, ArrayList<Integer> selectedSintomas, ArrayList<Integer> selectedAnalisis,
			ArrayList<Integer> selectedEnfermedades, ArrayList<Integer> selectedVacunas, String tipoSangre, String diagnostico) 
	{
		Statement statement; 
		int ID_Paciente = Clinica.getInstance().buscarPacienteByCedula(conexion, cedulaPersona);
		int ID_Consulta = 0;
		
		if(ID_Paciente == -1) {
			try {
				statement = conexion.createStatement();
	            String insertSql = "INSERT INTO Paciente (Doc_Identidad, ID_Tipo_Sangre, Altura, Peso, Alergia) VALUES ('"+cedulaPersona+"', '2', "+altura+", "+peso+", '"+alergia+"');";
	            statement.executeUpdate(insertSql);
	            ID_Paciente = Clinica.getInstance().buscarPacienteByCedula(conexion, cedulaPersona);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			statement = conexion.createStatement();
            String insertSql = "INSERT INTO Consulta (ID_Medico, ID_Paciente, Diagnostico) VALUES("+idMedico+", "+ID_Paciente+", '"+diagnostico+"');";
            statement.executeUpdate(insertSql);
            ID_Consulta = Clinica.getInstance().buscarConsultaByID(conexion, idMedico, ID_Paciente, diagnostico);
            
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			statement = conexion.createStatement();
			int tamArr = selectedSintomas.size();
			for(int ind = 0; ind < tamArr; ind++) {
	            String insertSql = "INSERT INTO Consulta_Sintoma (ID_Consulta, ID_Sintoma) VALUES("+ID_Consulta+", "+selectedSintomas.get(ind)+");";
	            statement.executeUpdate(insertSql);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			statement = conexion.createStatement();
			int tamArr = selectedAnalisis.size();
			for(int ind = 0; ind < tamArr; ind++) {
	            String insertSql = "INSERT INTO Consulta_Analisis (ID_Consulta, ID_Analisis) VALUES("+ID_Consulta+", "+selectedAnalisis.get(ind)+");";
	            statement.executeUpdate(insertSql);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			statement = conexion.createStatement();
			int tamArr = selectedEnfermedades.size();
			for(int ind = 0; ind < tamArr; ind++) {
	            String insertSql = "INSERT INTO Consulta_Enfermedad (ID_Consulta, ID_Enfermedad) VALUES("+ID_Consulta+", "+selectedEnfermedades.get(ind)+");";
	            statement.executeUpdate(insertSql);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			statement = conexion.createStatement();
			int tamArr = selectedVacunas.size();
			for(int ind = 0; ind < tamArr; ind++) {
	            String insertSql = "INSERT INTO Consulta_Vacuna (ID_Consulta, ID_Vacuna) VALUES("+ID_Consulta+", "+selectedVacunas.get(ind)+");";
	            statement.executeUpdate(insertSql);
			}
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
	}
	
	private int buscarPacienteByCedula(Connection conexion, String cedulaPersona) {
		boolean encontrado = false;
		int ID_Paciente = -1;
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT Persona.Doc_Identidad, Paciente.ID_Paciente, Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido, Telefono, Direccion, Sexo, Fecha_Nacimiento FROM Persona INNER JOIN Paciente ON Persona.Doc_Identidad = Paciente.Doc_Identidad;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next() && encontrado == false) {
            	
                if(resultSet.getString("Doc_Identidad").equals(cedulaPersona)) {
                	ID_Paciente = resultSet.getInt("ID_Paciente");
    				encontrado = true;
                }
            }
            return ID_Paciente;

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
			return ID_Paciente;
		}
	}
	
	private int buscarConsultaByID(Connection conexion, int ID_Medico, int ID_Paciente, String diagnostico) {
		boolean encontrado = false;
		int ID_Consulta = -1;
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Consulta, ID_Medico, ID_Paciente, Diagnostico FROM Consulta;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next() && encontrado == false) {
            	
                if(resultSet.getInt("ID_Medico") == ID_Medico && resultSet.getInt("ID_Paciente") == ID_Paciente && resultSet.getString("Diagnostico").equals(diagnostico)) {
                	ID_Consulta = resultSet.getInt("ID_Consulta");
    				encontrado = true;
                }
            }
            return ID_Consulta;

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
			return ID_Consulta;
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
	
	public Medico buscarMedicoByID(Connection conexion, int codigo) {
		
		Medico MedicoABuscar = null;
		boolean encontrado = false;
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT Persona.Doc_Identidad, Medico.ID_Medico, Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido, Telefono, Direccion, Sexo, Fecha_Nacimiento FROM Persona INNER JOIN Medico ON Persona.Doc_Identidad = Medico.Doc_Identidad;";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next() && encontrado == false) {
            	
                if(resultSet.getInt("ID_Medico") == codigo) {
                	MedicoABuscar = new Medico(resultSet.getString("Doc_Identidad"), resultSet.getString("Primer_Nombre"), resultSet.getString("Segundo_Nombre"), resultSet.getString("Primer_Apellido"), resultSet.getString("Segundo_Apellido"), resultSet.getString("Telefono"), resultSet.getString("Direccion"), resultSet.getString("Sexo").charAt(0), resultSet.getDate("Fecha_Nacimiento"), resultSet.getInt("ID_Medico"), null, null);
    				encontrado = true;
                }
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return MedicoABuscar;
	}
	
	public Cita buscarCitaByCode(Connection conexion, Integer codigo) {
		
		Cita CitaABuscar = null;
		boolean encontrado = false;
		
		try {
			Statement statement = conexion.createStatement();
            String selectSql = "SELECT ID_Cita, Doc_Identidad, ID_Administrativo, ID_Medico, Fecha_Cita, Hora_Cita, Pendiente FROM Cita WHERE ID_Cita = "+codigo+";";
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next() && encontrado == false) {
            	
                if(resultSet.getInt("ID_Cita") == codigo) {
                	CitaABuscar = new Cita(resultSet.getInt("ID_Cita"), resultSet.getString("Doc_Identidad"), resultSet.getInt("ID_Administrativo"), resultSet.getInt("ID_Medico"), resultSet.getDate("Fecha_Cita"), resultSet.getTime("Hora_Cita"), resultSet.getBoolean("Pendiente"));
    				encontrado = true;
                }
            }

		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return CitaABuscar;
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
	
	public boolean eliminarCita(Connection conexion, int codigoCita) {
		
		try {
			Statement statement = conexion.createStatement();
			String deleteSQL = "DELETE FROM Cita WHERE ID_Cita = '" + codigoCita + "';";	
			statement.executeUpdate(deleteSQL);
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
	
	public boolean modificarCita(Connection conexion, int id_Cita, String cedula, int idUsuarioLogueado2, 
			int id_Medico, Date fechaCita, String horaCita) {
		
		Statement statement;
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate= formateador.format(fechaCita);  
		Date fechaActual = new Date();
		byte pendiente;
		
		if(fechaActual.getTime() < fechaCita.getTime()) {
			pendiente = 1;
		} else {
			pendiente = 0;
		}
		
		try {
			statement = conexion.createStatement();
			String updateSql = "UPDATE Cita SET Doc_Identidad = '"+cedula+"', ID_Administrativo = "
			+idUsuarioLogueado2+", ID_Medico = "+id_Medico+", Fecha_Cita = '"+strDate+"', Hora_Cita = '"
					+horaCita+"', Pendiente = "+pendiente+" WHERE ID_Cita = "+id_Cita+";";
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


	public boolean insertarEspecialidades(Connection conexion, int ID_Medico, ArrayList<Integer> selectedEsp, 
											int tamArr) {
		
		Statement statement;
		
		try {
			statement = conexion.createStatement();
			for(int ind = 0; ind < tamArr; ind++) {
	            String insertSql = "INSERT INTO Med_Especialidad (ID_Medico, ID_Especialidad) VALUES ("+ID_Medico+","
	            		+ " "+selectedEsp.get(ind)+");";
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
	
public boolean insertarSintomas(Connection conexion, int ID_enfermedad, ArrayList<Integer> selectedSin, int tamArr) {
		
		Statement statement;
		
		try {
			statement = conexion.createStatement();
			for(int ind = 0; ind < tamArr; ind++) {
	            String insertSql = "INSERT INTO Enfermedad_Sintoma (ID_Enfermedad, ID_Sintoma) VALUES ("+ID_enfermedad+", "+selectedSin.get(ind)+");";
	            statement.executeUpdate(insertSql);
			}
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}


	public boolean modificarSintomas(Connection conexion, int ID_Enfermedad, ArrayList<Integer> selectedSin, int tamArr) {
		
		boolean elim = eliminarSintomas(conexion, ID_Enfermedad);
		
		if(elim == true) {
			
			elim  = insertarSintomas(conexion, ID_Enfermedad, selectedSin, tamArr);
			
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
	
	public boolean eliminarSintomas(Connection conexion, int ID_Enfermedad) {
		
		Statement statement;
		
		try {
			statement = conexion.createStatement();
			String deleteSql = "DELETE FROM Enfermedad_Sintoma WHERE ID_Enfermedad = "+ID_Enfermedad+";";
            statement.executeUpdate(deleteSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		
	}


	public boolean insertarVacuna(Connection conexion, int idEnfermedad, String nombreVacuna, String nombreLab) {
		
		Statement statement;
		
		try {
			statement = conexion.createStatement();
            String insertSql = "INSERT INTO Vacuna VALUES ('"+idEnfermedad+"','"+nombreVacuna+"','"+nombreLab+"');";
            statement.executeUpdate(insertSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public Vacuna buscarVacunaByCodeSQL(Connection conexion, int ID_vacuna) {
		
		Vacuna vacunaAbuscar = null;
		
		try {
			Statement statement = conexion.createStatement();
			String selectSQL = "SELECT ID_Vacuna, ID_Enfermedad, Nombre_Vacuna, Laboratorio FROM Vacuna";
			ResultSet resultSet = statement.executeQuery(selectSQL);
			
			while(resultSet.next()) {
				if(resultSet.getInt("ID_Vacuna") == ID_vacuna) {
					vacunaAbuscar = new Vacuna(resultSet.getInt("ID_Vacuna"), resultSet.getInt("ID_Enfermedad"), 
							resultSet.getString("Nombre_Vacuna"), resultSet.getString("Laboratorio"));
					break;
				}
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return vacunaAbuscar;
	}



	public boolean modificarVacuna(Connection conexion, Integer idEnfermedad, String nombreVacuna, String nombreLab, int id_Vacuna) {
		
		try {
			Statement statement = conexion.createStatement();
			String updateSql = "UPDATE Vacuna SET ID_Enfermedad = '"+idEnfermedad+"', Nombre_Vacuna = '"+nombreVacuna+"', Laboratorio = '"+nombreLab+"' WHERE ID_Vacuna = '"+id_Vacuna+"';";
            statement.executeUpdate(updateSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public boolean eliminarVacuna(Connection conexion, int idVacuna) {
		
		try {
			Statement statement = conexion.createStatement();
			String deleteSQL = "DELETE FROM Vacuna WHERE ID_Vacuna = " + idVacuna + ";";	
			statement.executeUpdate(deleteSQL);
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean insertarUsuarioSQL(Connection conexion, String doc_identidad, String usuario, String password, String cargo) {
		
		Statement statement;

		try {
			statement = conexion.createStatement();
            String insertSql = "INSERT INTO Administrativo VALUES ('"+doc_identidad+"','"+cargo+"','"+usuario+"','"+password+"');";
            statement.executeUpdate(insertSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}

	}
	
	public boolean insertarUsuarioSQL2(Connection conexion, String doc_identidad, String usuario, String password, String cargo) {
		
		Statement statement;

		try {
			statement = conexion.createStatement();
            String insertSql = "UPDATE Medico SET Nombre_Usuario = '"+usuario+"', Pass = '"+password+"' WHERE Doc_Identidad = '"+doc_identidad+"';";
            statement.executeUpdate(insertSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public Usuario buscarAdmByCode(Connection conexion, int ID_Usuario) {
		
		Usuario admAbuscar = null;
		
		try {
			Statement statement = conexion.createStatement();
			String selectSQL = "SELECT Persona.Doc_Identidad, Administrativo.ID_Administrativo, Administrativo.Cargo, Administrativo.Nombre_Usuario, Administrativo.Pass, Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido, Telefono, Direccion, Sexo, Fecha_Nacimiento FROM Persona INNER JOIN Administrativo ON Persona.Doc_Identidad = Administrativo.Doc_Identidad;";
			ResultSet resultSet = statement.executeQuery(selectSQL);
			
			while(resultSet.next()) {
				if(resultSet.getInt("ID_Administrativo") == ID_Usuario) {
					admAbuscar = new Usuario(resultSet.getString("Doc_Identidad"), resultSet.getString("Primer_Nombre"), resultSet.getString("Segundo_Nombre"), resultSet.getString("Primer_Apellido"), resultSet.getString("Segundo_Apellido"), 
											 resultSet.getString("Telefono"), resultSet.getString("Direccion"), resultSet.getString("Sexo").charAt(0), resultSet.getDate("Fecha_Nacimiento"), resultSet.getString("Cargo"), resultSet.getString("Nombre_Usuario"), 
											 resultSet.getString("Pass"));
					break;
				}
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		
		return admAbuscar;
	}
	
	public boolean modificarUsuario(Connection conexion, String idAdministrativo, String usuario, String pass, String cargo) {
		
		try {
			Statement statement = conexion.createStatement();
			String updateSql = "UPDATE Administrativo SET Cargo = '"+cargo+"', Nombre_Usuario = '"+usuario+"', Pass = '"+pass+"' WHERE ID_Administrativo = '"+idAdministrativo+"';";
            statement.executeUpdate(updateSql);
            return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public boolean eliminarUsuarioSQL(Connection conexion, int idAdministrativo) {
		
		try {
			Statement statement = conexion.createStatement();
			String deleteSQL = "DELETE FROM Administrativo WHERE ID_Administrativo = " + idAdministrativo + ";";	
			statement.executeUpdate(deleteSQL);
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
}
