package logico;

import java.util.Date;

public class Medico extends Persona {

    private String codeMedico;
    private String especialidad;

    public Medico(String cedula, String nombre, Date fechaDeNacimiento, char sexo, String telefono, String direccion,
            String codeMedico, String especialidad) {
        super(cedula, nombre, fechaDeNacimiento, sexo, telefono, direccion);
        this.codeMedico = codeMedico;
        this.especialidad = especialidad;
    }

    public String getCodeMedico() {
        return codeMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}