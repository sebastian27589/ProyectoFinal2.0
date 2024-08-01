package logico;

import java.util.Date;

public class Usuario extends Persona {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4896148487402102437L;
	
	private String rolUsuario;
    private String nombreUsuario;
    private String contrasena;

    public Usuario(String cedula, String nombre, Date fechaDeNacimiento, char sexo, String telefono, String direccion,
            String rolUsuario, String nombreUsuario, String contrasena) {
        super(cedula, nombre, fechaDeNacimiento, sexo, telefono, direccion);
        this.rolUsuario = rolUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
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