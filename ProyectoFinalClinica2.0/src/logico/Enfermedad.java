package logico;

import java.io.Serializable;

public class Enfermedad implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7074745685948383811L;
	
	private String nombre;
    private String tipo;
    private String sintomas;
    private int indPeligro;
    private boolean vigilada;

    public Enfermedad(String nombre, String tipo, String sintomas, int indPeligro, boolean vigilada) {
        super();
        this.nombre = nombre;
        this.tipo = tipo;
        this.sintomas = sintomas;
        this.indPeligro = indPeligro;
        this.vigilada = vigilada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public boolean isVigilada() {
        return vigilada;
    }

    public void setVigilada(boolean vigilada) {
        this.vigilada = vigilada;
    }

    public int getIndPeligro() {
        return indPeligro;
    }

    public void setIndPeligro(int indPeligro) {
        this.indPeligro = indPeligro;
    }

}