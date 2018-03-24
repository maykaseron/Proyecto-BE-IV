package entidades;
// Generated Mar 5, 2018 4:14:29 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Servicios generated by hbm2java
 */
public class Servicios  implements java.io.Serializable {


     private Integer idServicio;
     private String nombreServicio;
     private Float salarioEsperado;
     private String descripcionDescripcion;
     private Set<HabilidadesIncluidas> habilidadesIncluidases = new HashSet<HabilidadesIncluidas>(0);
     private Set<ServiciosPublicados> serviciosPublicadoses = new HashSet<ServiciosPublicados>(0);

    public Servicios() {
    }

    public Servicios(String nombreServicio, Float salarioEsperado, String descripcionDescripcion, Set<HabilidadesIncluidas> habilidadesIncluidases, Set<ServiciosPublicados> serviciosPublicadoses) {
       this.nombreServicio = nombreServicio;
       this.salarioEsperado = salarioEsperado;
       this.descripcionDescripcion = descripcionDescripcion;
       this.habilidadesIncluidases = habilidadesIncluidases;
       this.serviciosPublicadoses = serviciosPublicadoses;
    }
   
    public Integer getIdServicio() {
        return this.idServicio;
    }
    
    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }
    public String getNombreServicio() {
        return this.nombreServicio;
    }
    
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
    public Float getSalarioEsperado() {
        return this.salarioEsperado;
    }
    
    public void setSalarioEsperado(Float salarioEsperado) {
        this.salarioEsperado = salarioEsperado;
    }
    public String getDescripcionDescripcion() {
        return this.descripcionDescripcion;
    }
    
    public void setDescripcionDescripcion(String descripcionDescripcion) {
        this.descripcionDescripcion = descripcionDescripcion;
    }
    public Set<HabilidadesIncluidas> getHabilidadesIncluidases() {
        return this.habilidadesIncluidases;
    }
    
    public void setHabilidadesIncluidases(Set<HabilidadesIncluidas> habilidadesIncluidases) {
        this.habilidadesIncluidases = habilidadesIncluidases;
    }
    public Set<ServiciosPublicados> getServiciosPublicadoses() {
        return this.serviciosPublicadoses;
    }
    
    public void setServiciosPublicadoses(Set<ServiciosPublicados> serviciosPublicadoses) {
        this.serviciosPublicadoses = serviciosPublicadoses;
    }




}


