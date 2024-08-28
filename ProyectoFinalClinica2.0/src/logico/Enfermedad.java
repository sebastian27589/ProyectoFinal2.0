package logico;

import java.io.Serializable;

public class Enfermedad implements Serializable {

    /**
     
*/
  private static final long serialVersionUID = -7074745685948383811L;
  private int ID_Enfermedad;
  private int ID_Tipo_Enfermedad;
  private String Nombre_Enfermedad;
  private boolean vigilada;
  private int indPeligro;
  public Enfermedad(int iD_Enfermedad, int iD_Tipo_Enfermedad, String nombre_Enfermedad, boolean vigilada,
                    int indPeligro) {
      super();
      this.ID_Enfermedad = iD_Enfermedad;
      this.ID_Tipo_Enfermedad = iD_Tipo_Enfermedad;
      this.Nombre_Enfermedad = nombre_Enfermedad;
      this.vigilada = vigilada;
      this.indPeligro = indPeligro;}

    public int getID_Tipo_Enfermedad() {
        return ID_Tipo_Enfermedad;
    }

    public void setID_Tipo_Enfermedad(int iD_Tipo_Enfermedad) {
        ID_Tipo_Enfermedad = iD_Tipo_Enfermedad;
    }

    public String getNombre_Enfermedad() {
        return Nombre_Enfermedad;
    }

    public void setNombre_Enfermedad(String nombre_Enfermedad) {
        Nombre_Enfermedad = nombre_Enfermedad;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getID_Enfermedad() {
        return ID_Enfermedad;
    }


}