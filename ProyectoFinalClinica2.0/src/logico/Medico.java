package logico;

import java.util.ArrayList;
import java.util.Date;

public class Medico extends Persona {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9003456030882977992L;
	
	private String codeMedico;
    private ArrayList<String> especialidades;
    
	public Medico(String cedula, String nombre, Date fechaDeNacimiento, char sexo, String telefono, String direccion,
			String codeMedico) {
		super(cedula, nombre, fechaDeNacimiento, sexo, telefono, direccion);
		this.codeMedico = codeMedico;
		this.especialidades = new ArrayList<String>();
	}

	public String getCodeMedico() {
		return codeMedico;
	}
	
	public void setCodeMedico(String codeMedico) {
		this.codeMedico = codeMedico;
	}

	public ArrayList<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(ArrayList<String> especialidades) {
		this.especialidades = especialidades;
	}

}