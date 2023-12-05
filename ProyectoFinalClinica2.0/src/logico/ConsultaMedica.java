package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ConsultaMedica implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -562385869732232731L;
	
	private String codeConsMed;
    private String codePaciente;
    private String codeMedico;
    private Enfermedad enfermedad;
    private String sintomas;
    private String diagnostico;
    private ArrayList<String> analisis;
    private Date fechaConsulta;
    
	public ConsultaMedica(String codeConsMed, String codePaciente, String codeMedico, Enfermedad enfermedad,
			String sintomas, String diagnostico, Date fechaConsulta) {
		super();
		this.codeConsMed = codeConsMed;
		this.codePaciente = codePaciente;
		this.codeMedico = codeMedico;
		this.enfermedad = enfermedad;
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.analisis = new ArrayList<String>();
		this.fechaConsulta = fechaConsulta;
	}

	public String getCodeConsMed() {
		return codeConsMed;
	}
	
	public void setCodeConsMed(String codeConsMed) {
		this.codeConsMed = codeConsMed;
	}
	
	public String getCodePaciente() {
		return codePaciente;
	}
	
	public void setCodePaciente(String codePaciente) {
		this.codePaciente = codePaciente;
	}
	
	public String getCodeMedico() {
		return codeMedico;
	}
	
	public void setCodeMedico(String codeMedico) {
		this.codeMedico = codeMedico;
	}
	
	public Enfermedad getEnfermedad() {
		return enfermedad;
	}
	
	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}
	
	public String getSintomas() {
		return sintomas;
	}
	
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}
	
	public String getDiagnostico() {
		return diagnostico;
	}
	
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
	public ArrayList<String> getAnalisis() {
		return analisis;
	}
	
	public void setAnalisis(ArrayList<String> analisis) {
		this.analisis = analisis;
	}
	
	public Date getFechaConsulta() {
		return fechaConsulta;
	}
	
	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

}