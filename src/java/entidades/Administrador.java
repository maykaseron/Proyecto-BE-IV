/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author anderson
 */
public class Administrador {
    private String cedulaOferente; 
    private String contrasena;

    public Administrador() {
    }

    public String getCedulaOferente() {
        return cedulaOferente;
    }

    public void setCedulaOferente(String cedulaOferente) {
        this.cedulaOferente = cedulaOferente;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
