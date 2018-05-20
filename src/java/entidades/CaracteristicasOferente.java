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
public class CaracteristicasOferente {
    private Integer idCO;
    private Oferente oferente;
    private Caracteristicas caracteristicas;
    private int valor;

    public CaracteristicasOferente() {
    }

    public CaracteristicasOferente(Integer idCO, Oferente oferente, Caracteristicas caracteristicas, int valor) {
        this.idCO = idCO;
        this.oferente = oferente;
        this.caracteristicas = caracteristicas;
        this.valor = valor;
    }

    public Integer getIdCO() {
        return idCO;
    }

    public void setIdCO(Integer idCO) {
        this.idCO = idCO;
    }

    public Oferente getOferente() {
        return oferente;
    }

    public void setOferente(Oferente oferente) {
        this.oferente = oferente;
    }

    public Caracteristicas getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Caracteristicas caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
}
