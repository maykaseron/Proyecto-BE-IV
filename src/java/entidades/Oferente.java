package entidades;
// Generated Mar 5, 2018 4:14:29 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Oferente generated by hbm2java
 */
public class Oferente  implements java.io.Serializable {


     private String cedulaOferente; 
     private String nombreOferente;
     private String primerApellido;
     private String celular; 
     private String nacionalidad; 
     private String correoOferente;
     private String ubicacion;
     private Set<Aplicado> aplicados = new HashSet<Aplicado>(0);
     private Set<ServiciosPublicados> serviciosPublicadoses = new HashSet<ServiciosPublicados>(0);

    public Oferente() {
       this.cedulaOferente = "";
       this.nombreOferente = "";
       this.primerApellido = "";
       this.celular = "";
       this.nacionalidad = "";
       this.correoOferente = "";
       this.ubicacion = "";
    }

	
    public Oferente(String cedulaOferente) {
        this.cedulaOferente = cedulaOferente;
    }
    public Oferente(String cedulaOferente, String nombreOferente, String primerApellido, String celular, String nacionalidad, String correoOferente, String ubicacion, Set<Aplicado> aplicados, Set<ServiciosPublicados> serviciosPublicadoses) {
       this.cedulaOferente = cedulaOferente;
       this.nombreOferente = nombreOferente;
       this.primerApellido = primerApellido;
       this.celular = celular;
       this.nacionalidad = nacionalidad;
       this.correoOferente = correoOferente;
       this.ubicacion = ubicacion;
       this.aplicados = aplicados;
       this.serviciosPublicadoses = serviciosPublicadoses;
    }
    
      public Oferente(String cedulaOferente, String nombreOferente, String primerApellido, String celular, String nacionalidad, String correoOferente, String ubicacion) {
       this.cedulaOferente = cedulaOferente;
       this.nombreOferente = nombreOferente;
       this.primerApellido = primerApellido;
       this.celular = celular;
       this.nacionalidad = nacionalidad;
       this.correoOferente = correoOferente;
       this.ubicacion = ubicacion;
       
    }
   
    public String getCedulaOferente() {
        return this.cedulaOferente;
    }
    
    public void setCedulaOferente(String cedulaOferente) {
        this.cedulaOferente = cedulaOferente;
    }
    public String getNombreOferente() {
        return this.nombreOferente;
    }
    
    public void setNombreOferente(String nombreOferente) {
        this.nombreOferente = nombreOferente;
    }
    public String getPrimerApellido() {
        return this.primerApellido;
    }
    
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getNacionalidad() {
        return this.nacionalidad;
    }
    
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public String getCorreoOferente() {
        return this.correoOferente;
    }
    
    public void setCorreoOferente(String correoOferente) {
        this.correoOferente = correoOferente;
    }
    public String getUbicacion() {
        return this.ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public Set<Aplicado> getAplicados() {
        return this.aplicados;
    }
    
    public void setAplicados(Set<Aplicado> aplicados) {
        this.aplicados = aplicados;
    }
    public Set<ServiciosPublicados> getServiciosPublicadoses() {
        return this.serviciosPublicadoses;
    }
    
    public void setServiciosPublicadoses(Set<ServiciosPublicados> serviciosPublicadoses) {
        this.serviciosPublicadoses = serviciosPublicadoses;
    }




}


