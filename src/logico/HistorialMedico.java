package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class HistorialMedico implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4349096348610847614L;
	
	private String codeHistMed;
    private Paciente paciente;
    private ArrayList<Enfermedad> misEnfermedades;

    public HistorialMedico(String codeHistMed, Paciente paciente) {
        super();
        this.codeHistMed = codeHistMed;
        this.paciente = paciente;
        this.misEnfermedades = new ArrayList<Enfermedad>();
    }

    public String getCodeHistMed() {
        return codeHistMed;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ArrayList<Enfermedad> getMisEnfermedades() {
        return misEnfermedades;
    }

    public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
        this.misEnfermedades = misEnfermedades;
    }

}