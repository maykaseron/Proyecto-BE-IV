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
    private Integer consecutivo;
    private Oferente oferente;
    private Caracteristicas caracteristicas;
    private int valor;

    public CaracteristicasOferente() {
    }

    public CaracteristicasOferente(Integer consecutivo, Oferente oferente, Caracteristicas caracteristicas, int valor) {
        this.consecutivo = consecutivo;
        this.oferente = oferente;
        this.caracteristicas = caracteristicas;
        this.valor = valor;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
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
