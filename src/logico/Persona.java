package logico;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 9151005347606071992L;
	
	protected String cedula;
    protected String nombre;
    protected Date fechaDeNacimiento;
    protected char sexo;
    protected String telefono;
    protected String direccion;

    public Persona(String cedula, String nombre, Date fechaDeNacimiento, char sexo, String telefono, String direccion) {
        super();
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}