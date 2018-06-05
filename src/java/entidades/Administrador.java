/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.List;

/**
 *
 * @author anderson
 */
public class Administrador {
    
    private String correoAdministrador;
    private String nombreAdministrador;
    private String cedulaAdministrador; 
    private String contrasena;
    private List<Oferente> listaOferentesNuevos;
    private List<Empresa> listaEmpresaNuevos;
    
    public Administrador() {
    }

    public String getCorreoAdministrador() {
        return correoAdministrador;
    }

    public void setCorreoAdministrador(String correoAdministrador) {
        this.correoAdministrador = correoAdministrador;
    }
    
    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getCedulaAdministrador() {
        return cedulaAdministrador;
    }

    public void setCedulaAdministrador(String cedulaAdministrador) {
        this.cedulaAdministrador = cedulaAdministrador;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Oferente> getListaOferentesNuevos() {
        return listaOferentesNuevos;
    }

    public void setListaOferentesNuevos(List<Oferente> listaOferentesNuevos) {
        this.listaOferentesNuevos = listaOferentesNuevos;
    }

    public List<Empresa> getListaEmpresaNuevos() {
        return listaEmpresaNuevos;
    }

    public void setListaEmpresaNuevos(List<Empresa> listaEmpresaNuevos) {
        this.listaEmpresaNuevos = listaEmpresaNuevos;
    }
    
    
}
