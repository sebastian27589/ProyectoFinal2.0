package logico;

import java.util.ArrayList;
import java.util.Date;

public class ConsultaMedica {

    private String codeConsMed;
    private String codeMedico;
    private Enfermedad enfermedad;
    private String sintomas;
    private String diagnostico;
    private ArrayList<String> analisis;
    private Date fechaConsulta;

    public ConsultaMedica(String codeConsMed, String codeMedico, Enfermedad enfermedad, String sintomas,
            String diagnostico, Date fechaConsulta) {
        super();
        this.codeConsMed = codeConsMed;
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

    public String getCodeMedico() {
        return codeMedico;
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

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public ArrayList<String> getAnalisis() {
        return analisis;
    }

    public void setAnalisis(ArrayList<String> analisis) {
        this.analisis = analisis;
    }

}