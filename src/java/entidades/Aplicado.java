package entidades;
// Generated Mar 5, 2018 4:14:29 PM by Hibernate Tools 4.3.1

import java.sql.Date;





/**
 * Aplicado generated by hbm2java
 */
public class Aplicado  implements java.io.Serializable {


   
     private String idOferente;
     private int idPuestos;
     private Date fechaAplicacion;

    public Aplicado() {
    }

	
    
    public Aplicado(String idOferente, int idPuestos,Date fechaAplicacion) {
       this.idOferente = idOferente;
       this.idPuestos = idPuestos;
       this.fechaAplicacion = fechaAplicacion;
    }

    public String getIdOferente() {
        return idOferente;
    }

    public void setIdOferente(String idOferente) {
        this.idOferente = idOferente;
    }

    public int getIdPuestos() {
        return idPuestos;
    }

    public void setIdPuestos(int idPuestos) {
        this.idPuestos = idPuestos;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }
   
   


}

