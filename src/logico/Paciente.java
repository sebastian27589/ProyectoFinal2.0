package logico;

import java.util.ArrayList;
import java.util.Date;

public class Paciente extends Persona {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3828098654321780441L;
	
	private String codePaciente;
    private String tipoDeSangre;
    private float altura;
    private float peso;
    private String alergias;
    private String infoImportante;
    private ArrayList<Vacuna> misVacunas;
    
	public Paciente(String cedula, String nombre, Date fechaDeNacimiento, char sexo, String telefono, String direccion,
			String codePaciente, String tipoDeSangre, float altura, float peso, String alergias, String infoImportante) {
		super(cedula, nombre, fechaDeNacimiento, sexo, telefono, direccion);
		this.codePaciente = codePaciente;
		this.tipoDeSangre = tipoDeSangre;
		this.altura = altura;
		this.peso = peso;
		this.alergias = alergias;
		this.infoImportante = infoImportante;
		this.misVacunas = new ArrayList<Vacuna>();
	}

	public String getCodePaciente() {
		return codePaciente;
	}
	
	public void setCodePaciente(String codePaciente) {
		this.codePaciente = codePaciente;
	}
	
	public String getTipoDeSangre() {
		return tipoDeSangre;
	}
	
	public void setTipoDeSangre(String tipoDeSangre) {
		this.tipoDeSangre = tipoDeSangre;
	}
	
	public float getAltura() {
		return altura;
	}
	
	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	public float getPeso() {
		return peso;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public String getAlergias() {
		return alergias;
	}
	
	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}
	
	public String getInfoImportante() {
		return infoImportante;
	}
	
	public void setInfoImportante(String infoImportante) {
		this.infoImportante = infoImportante;
	}
	
	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}
	
	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	
}