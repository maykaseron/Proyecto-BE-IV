package entidades;
// Generated Mar 5, 2018 4:14:29 PM by Hibernate Tools 4.3.1



/**
 * Caracteristicas generated by hbm2java
 */
public class Caracteristicas  implements java.io.Serializable {
    // no lo voy a usar mas
  /*  private String areaTrabajo;
    private String especializacion;
    */
    // esto si
    private Integer idCaracteristica;
    Caracteristicas papa_carac;
    private String habilidad;
    boolean padre;  // true si es padre

    public Caracteristicas() {
        papa_carac =null;
    }
	
    public Caracteristicas(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }
    
    public Caracteristicas(Integer idCaracteristica,/* String areaTrabajo, String especializacion,*/ String habilidad) {
       this.idCaracteristica = idCaracteristica;
       /*this.areaTrabajo = areaTrabajo;
       this.especializacion = especializacion;*/
       papa_carac =null;
       this.habilidad = habilidad;
    }
   
    public Integer getIdCaracteristica() {
        return this.idCaracteristica;
    }
    
    public void setIdCaracteristica(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }/*
    public String getAreaTrabajo() {
        return this.areaTrabajo;
    }
    
    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }
    public String getEspecializacion() {
        return this.especializacion;
    }
    
    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }*/

    public Caracteristicas getPapa_carac() {
        return papa_carac;
    }

    public void setPapa_carac(Caracteristicas papa_carac) {
        this.papa_carac = papa_carac;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public boolean isPadre() {
        return padre;
    }

    public void setPadre(boolean padre) {
        this.padre = padre;
    }
    
    
}


