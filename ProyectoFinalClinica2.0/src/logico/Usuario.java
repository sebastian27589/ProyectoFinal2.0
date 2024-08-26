package logico;

import java.util.Date;

public class Usuario extends Persona {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4896148487402102437L;
	
	private String cargoUsuario;
    private String nombreUsuario;
    private String contrasena;
    
	public Usuario(String cedula, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String telefono, String direccion, char sexo, Date fechaDeNacimiento,
			String cargoUsuario, String nombreUsuario, String contrasena) {
		super(cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, telefono, direccion, sexo,
				fechaDeNacimiento);
		this.cargoUsuario = cargoUsuario;
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
	}

	public String getCargoUsuario() {
		return cargoUsuario;
	}

	public void setCargoUsuario(String cargoUsuario) {
		this.cargoUsuario = cargoUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
    
    

}