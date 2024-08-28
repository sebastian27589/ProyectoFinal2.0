package logico;

import java.util.Date;

public class Medico extends Persona {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9003456030882977992L;
	
	private int ID_Medico;
	private String nombreUsuario;
	private String pass;
	
	public Medico(String cedula, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String telefono, String direccion, char sexo, Date fechaDeNacimiento, int iD_Medico,
			String nombreUsuario, String pass) {
		super(cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono, direccion, sexo,
				fechaDeNacimiento);
		ID_Medico = iD_Medico;
		this.nombreUsuario = nombreUsuario;
		this.pass = pass;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getID_Medico() {
		return ID_Medico;
	}

}