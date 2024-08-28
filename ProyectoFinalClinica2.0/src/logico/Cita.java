package logico;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Cita implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 9179413854043792750L;
	
	private int ID_Cita;
    private String cedula;
    private int ID_Administrativo;
    private int ID_Medico;
    private Date fechaCita;
    private Time horaCita;
    private boolean pendiente;
    
	public Cita(int iD_Cita, String cedula, int iD_Administrativo, int iD_Medico, Date fechaCita, Time horaCita,
			boolean pendiente) {
		super();
		ID_Cita = iD_Cita;
		this.cedula = cedula;
		ID_Administrativo = iD_Administrativo;
		ID_Medico = iD_Medico;
		this.fechaCita = fechaCita;
		this.horaCita = horaCita;
		this.pendiente = pendiente;
	}

	public int getID_Cita() {
		return ID_Cita;
	}

	public void setID_Cita(int iD_Cita) {
		ID_Cita = iD_Cita;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getID_Administrativo() {
		return ID_Administrativo;
	}

	public void setID_Administrativo(int iD_Administrativo) {
		ID_Administrativo = iD_Administrativo;
	}

	public int getID_Medico() {
		return ID_Medico;
	}

	public void setID_Medico(int iD_Medico) {
		ID_Medico = iD_Medico;
	}

	public Date getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public Time getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(Time horaCita) {
		this.horaCita = horaCita;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}
	
	
    
  
}