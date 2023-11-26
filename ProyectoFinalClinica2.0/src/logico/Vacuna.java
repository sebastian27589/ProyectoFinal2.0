package logico;

import java.util.ArrayList;

public class Vacuna {

    private String codeVacuna;
    private String nombre;
    private String laboratorio;
    private ArrayList<Enfermedad> enfermedadesQueTrata;

    public Vacuna(String codeVacuna, String nombre, String laboratorio) {
        super();
        this.codeVacuna = codeVacuna;
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.enfermedadesQueTrata = new ArrayList<Enfermedad>();
    }

    public String getCodeVacuna() {
        return codeVacuna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public ArrayList<Enfermedad> getEnfermedadesQueTrata() {
        return enfermedadesQueTrata;
    }

    public void setEnfermedadesQueTrata(ArrayList<Enfermedad> enfermedadesQueTrata) {
        this.enfermedadesQueTrata = enfermedadesQueTrata;
    }

}
