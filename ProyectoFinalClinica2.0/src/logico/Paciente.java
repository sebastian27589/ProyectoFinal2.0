package logico;

import java.util.ArrayList;
import java.util.Date;

public class Paciente extends Persona {

    private String codePaciente;
    private ArrayList<ConsultaMedica> misConsultas;
    private ArrayList<Vacuna> misVacunas;

    public Paciente(String cedula, String nombre, Date fechaDeNacimiento, char sexo, String telefono, String direccion,
            String codePaciente) {
        super(cedula, nombre, fechaDeNacimiento, sexo, telefono, direccion);
        this.codePaciente = codePaciente;
        this.misConsultas = new ArrayList<ConsultaMedica>();
        this.misVacunas = new ArrayList<Vacuna>();
    }

    public String getCodePaciente() {
        return codePaciente;
    }

    public ArrayList<ConsultaMedica> getMisConsultas() {
        return misConsultas;
    }

    public void setMisConsultas(ArrayList<ConsultaMedica> misConsultas) {
        this.misConsultas = misConsultas;
    }

    public ArrayList<Vacuna> getMisVacunas() {
        return misVacunas;
    }

    public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
        this.misVacunas = misVacunas;
    }
}