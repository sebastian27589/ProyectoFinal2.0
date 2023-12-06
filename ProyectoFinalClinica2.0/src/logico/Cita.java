package logico;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 9179413854043792750L;
	
	private String numCita;
    private Persona cliente;
    private String codeMedico;
    private Date fechaDeCita;
    private String horaCita;
    private boolean pendiente;
    
	public Cita(String numCita, Persona cliente, String codeMedico, Date fechaDeCita, String horaCita) {
		super();
		this.numCita = numCita;
		this.cliente = cliente;
		this.codeMedico = codeMedico;
		this.fechaDeCita = fechaDeCita;
		this.horaCita = horaCita;
		this.pendiente = true;
	}
	
	public String getNumCita() {
		return numCita;
	}
	
	public void setNumCita(String numCita) {
		this.numCita = numCita;
	}
	
	public Persona getCliente() {
		return cliente;
	}
	
	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}
	
	public String getCodeMedico() {
		return codeMedico;
	}
	
	public void setCodeMedico(String codeMedico) {
		this.codeMedico = codeMedico;
	}
	
	public Date getFechaDeCita() {
		return fechaDeCita;
	}
	
	public void setFechaDeCita(Date fechaDeCita) {
		this.fechaDeCita = fechaDeCita;
	}
	
	public String getHoraCita() {
		return horaCita;
	}
	
	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}
	
	public boolean isPendiente() {
		return pendiente;
	}
	
	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
  
}