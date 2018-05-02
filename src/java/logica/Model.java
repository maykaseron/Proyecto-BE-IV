/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dao.datos.Dao;
import entidades.Caracteristicas;
import entidades.CaracteristicasPuestos;
import entidades.Empresa;
import entidades.Oferente;
import entidades.Puestos;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
//import java.util.Collections;

/**
 *
 * @author pc
 */
public class Model {
    
     private final Dao dao;
     
      private static Model uniqueInstance;
      
         public static Model instance() throws ClassNotFoundException, SQLException, IOException{
        if (uniqueInstance == null){
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }
    private Model() throws ClassNotFoundException, SQLException, IOException{
       
         dao = new Dao();
    }
    
    /*********************Empresa********************************/
    
    public void updateEmpresa(Empresa p) throws Exception{
        dao.EmpresaUpdate(p);
    }

    public void deleteEmpresa(Empresa p) throws Exception{
        dao.EmpresaDelete(p);
    }

    public void addEmpresa(Empresa p) throws Exception{
        dao.EmpresaAdd(p);
    }

    public Empresa getEmpresa(int id1) throws Exception{
        return dao.EmpresaGet(id1);
    }

    public List<Empresa> getAllEmpresa() throws Exception{
        return  dao.EmpresaGetAll();
    }
       
    public Empresa getEmpresaLogin( Empresa e ) throws Exception{
        return dao.EmpresaLogin( e );
    }
    /*********************Oferente********************************/
    
    public void updateOferente(Oferente p) throws Exception{
        dao.OferenteUpdate(p);
    }

    public void deleteOferente(Oferente p) throws Exception{
        dao.OferenteDelete(p);
    }

    public void addOferente(Oferente p) throws Exception{
        dao.OferenteAdd(p);
    }

    public Oferente getOferente(String id1) throws Exception{
        return dao.OferenteGet(id1);
    }

    public List<Oferente> getAllOferente() throws Exception{
        return  dao.OferenteGetAll();
    }
    
    public Oferente getOferenteLogin( Oferente o ) throws Exception{
        return dao.OferenteLogin( o );
    }
    /*********************CARACTERISTICAS********************************/
/*
    public void updateCaracteristicas(Caracteristicas p) throws Exception{
        dao.CaracteristicasUpdate(p);
    }
*/
    public void deleteCaracteristicas(Caracteristicas p) throws Exception{
        dao.CaracteristicasDelete(p);
    }
/*
    public void addCaracteristicas(Caracteristicas p) throws Exception{
        dao.CaracteristicasAdd(p);
    }
*/
    public Caracteristicas getCaracteristicas(int id1) throws Exception{
        return dao.CaracteristicasGet(id1);
    }
    /*
     // busca por areaTrabajo 
    public List<Caracteristicas> getCaracteristicasEspecializ(String id1) throws Exception{
        return dao.CaracteristicasEspecializ(id1);
    }
    
    public List<Caracteristicas> getCaracteristicasAreaTrabajo( ) throws Exception{
        return dao.CaracteristicasAreaTrabajo(  );
    }
    */
    public List<Caracteristicas> getAllCaracteristicas() throws Exception{
        return  dao.CaracteristicasGetAll();
    }
    // los padres o raiz
    public List<Caracteristicas> getAllCaracteristicasPadres() throws Exception{ // los raiz
        return  dao.AllCaracteristicasPadres();
    }
    
    public List<Caracteristicas> getBuscarCaracteristicas(int idP) throws Exception{ // los hijos de un padre del árlbol
        return  dao.BuscarCaracteristicas(idP);
    }
    
    /*********************PUESTOS********************************/
            
    public void updatePuestos(Puestos p) throws Exception {
        dao.PuestosUpdate(p);
    }

    public void deletePuestos(Puestos p) throws Exception {
        dao.PuestosDelete(p);
    }

    public void addPuestos(Puestos p) throws Exception {
        dao.PuestosAdd(p);
    }

    public Puestos getPuestos(int id1) throws Exception {
        return dao.PuestosGet(id1);
    }

    public List<Puestos> getAllPuestos() throws Exception {
        return  dao.PuestosGetAll();
    }
    /*
    public List<Puestos> ListTop5() throws Exception{
       return dao.ListTop5();
    }
    */
      
    
    /*********************CARACTERISTICAS PUESTOS********************************/
    
    public void deleteCaracteristicasPuestos (CaracteristicasPuestos p) throws Exception{
        dao.CaracteristicasPuestosDelete(p);
    }

    public void addCaracteristicasPuestos (CaracteristicasPuestos p) throws Exception{
        dao.CaracteristicasPuestosAdd(p);
    }
      
    public CaracteristicasPuestos getCaracteristicasPuestos (int id1) throws Exception{
        return dao.CaracteristicasPuestosGet(id1);
    }
       
    public List<CaracteristicasPuestos> getAllCaracteristicasPuestos () throws Exception{
        return  dao.CaracteristicasPuestosGetAll();
    }
    
    public List<Puestos> ListTop5() throws Exception{
        return dao.ListTop5();
    }
    /*
    public List<CaracteristicasPuestos> getPuestosPorCaracteristicas( String especializacion ) throws Exception{
        return dao.PuestosPorCaracteristicas( especializacion );
    }*/
    
    
}
