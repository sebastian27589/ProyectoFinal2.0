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
    private boolean pendiente;
    private Date fechaDeCita;

    public Cita(String numCita, Persona cliente, String codeMedico, boolean pendiente, Date fechaDeCita) {
        super();
        this.numCita = numCita;
        this.cliente = cliente;
        this.codeMedico = codeMedico;
        this.pendiente = pendiente;
        this.fechaDeCita = fechaDeCita;
    }

    public String getNumCita() {
        return numCita;
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

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    public Date getFechaDeCita() {
        return fechaDeCita;
    }

    public void setFechaDeCita(Date fechaDeCita) {
        this.fechaDeCita = fechaDeCita;
    }


}