package entidades;
// Generated Mar 5, 2018 4:14:29 PM by Hibernate Tools 4.3.1



/**
 * Caracteristicas generated by hbm2java
 */
public class Caracteristicas  implements java.io.Serializable {
     private Integer idCaracteristica;
     private String areaTrabajo;
     private String especializacion;

    public Caracteristicas() {
    }

	
    public Caracteristicas(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }
    public Caracteristicas(Integer idCaracteristica, String areaTrabajo, String especializacion) {
       this.idCaracteristica = idCaracteristica;
       this.areaTrabajo = areaTrabajo;
       this.especializacion = especializacion;
    }
   
    public Integer getIdCaracteristica() {
        return this.idCaracteristica;
    }
    
    public void setIdCaracteristica(Integer idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }
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
    }
    // usado para  el hashmap del PuestosCaracteristicas.jsp T.T
    @Override
    public String toString () {
        return areaTrabajo;
    }


}


