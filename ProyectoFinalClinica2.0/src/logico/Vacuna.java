package logico;

import java.io.Serializable;

public class Vacuna implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5747793496309731506L;
	
	private int ID_vacuna;
	private int ID_enfermedad;
    private String nombre_vacuna;
    private String nombre_laboratorio;
    
	public Vacuna(int iD_vacuna, int iD_enfermedad, String nombre_vacuna, String nombre_laboratorio) {
		super();
		this.ID_vacuna = iD_vacuna;
		this.ID_enfermedad = iD_enfermedad;
		this.nombre_vacuna = nombre_vacuna;
		this.nombre_laboratorio = nombre_laboratorio;
	}

	public int getID_enfermedad() {
		return ID_enfermedad;
	}

	public void setID_enfermedad(int iD_enfermedad) {
		ID_enfermedad = iD_enfermedad;
	}

	public String getNombre_vacuna() {
		return nombre_vacuna;
	}

	public void setNombre_vacuna(String nombre_vacuna) {
		this.nombre_vacuna = nombre_vacuna;
	}

	public String getNombre_laboratorio() {
		return nombre_laboratorio;
	}

	public void setNombre_laboratorio(String nombre_laboratorio) {
		this.nombre_laboratorio = nombre_laboratorio;
	}

	public int getID_vacuna() {
		return ID_vacuna;
	}
}
