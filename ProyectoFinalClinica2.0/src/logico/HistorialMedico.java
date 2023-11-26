package logico;

import java.util.ArrayList;

public class HistorialMedico {

    private String codeHistMed;
    private Paciente paciente;
    private ArrayList<ConsultaMedica> misConsultas;
    private ArrayList<Enfermedad> misEnfermedades;
    private ArrayList<Vacuna> misVacunas;

    public HistorialMedico(String codeHistMed, Paciente paciente) {
        super();
        this.codeHistMed = codeHistMed;
        this.paciente = paciente;
        this.misConsultas = new ArrayList<ConsultaMedica>();
        this.misEnfermedades = new ArrayList<Enfermedad>();
        this.misVacunas = new ArrayList<Vacuna>();
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

    public ArrayList<ConsultaMedica> getMisConsultas() {
        return misConsultas;
    }

    public void setMisConsultas(ArrayList<ConsultaMedica> misConsultas) {
        this.misConsultas = misConsultas;
    }

    public ArrayList<Enfermedad> getMisEnfermedades() {
        return misEnfermedades;
    }

    public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
        this.misEnfermedades = misEnfermedades;
    }

    public ArrayList<Vacuna> getMisVacunas() {
        return misVacunas;
    }

    public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
        this.misVacunas = misVacunas;
    }

}