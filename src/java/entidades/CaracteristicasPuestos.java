package entidades;
// Generated Mar 5, 2018 4:14:29 PM by Hibernate Tools 4.3.1

import java.sql.Date;

public class CaracteristicasPuestos  implements java.io.Serializable {
    private Integer consecutivo;
    private Puestos puesto;
    private Caracteristicas caracteristicas;
    private int valor;

    public CaracteristicasPuestos() {
    }

    
    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Puestos getPuesto() {
        return puesto;
    }

    public void setPuesto(Puestos puesto) {
        this.puesto = puesto;
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