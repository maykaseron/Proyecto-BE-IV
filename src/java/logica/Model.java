/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dao.datos.Dao;
import entidades.Aplicado;
import entidades.Caracteristicas;
import entidades.CaracteristicasPuestos;
import entidades.Empresa;
import entidades.Habilidades;
import entidades.HabilidadesIncluidas;
import entidades.Oferente;
import entidades.Puestos;
import entidades.PuestosPublicados;
import entidades.Servicios;
import entidades.ServiciosPublicados;
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
    
    
    
       /*********************Servicio********************************/
    
      public void updateServicios(Servicios p) throws Exception{

                    dao.ServiciosUpdate(p);
       }

        public void deleteServicios(Servicios p) throws Exception{

            dao.ServiciosDelete(p);
       }

         public void addServicios(Servicios p) throws Exception{
           dao.ServiciosAdd(p);
       }

          public Servicios getServicios(String id1) throws Exception{
           return dao.ServicioGet(id1);
       }

           public Collection<Servicios> getAllServicios() throws Exception{
            return  dao.ServiciosGetAll();
    
           }
    
    
    
    
    
    /*********************Oferente********************************/
    
           public void updateOferente(Oferente p) throws Exception{

                    dao.OferenteUpdate(p);
       }

        public void deleteOferente(Oferente p) throws Exception{

            dao.OferenteDelete(p);
       }

         public void addOferente(Oferente p) throws Exception{
           System.out.println("en DAO oferenteAdd");
           dao.OferenteAdd(p);
       }

          public Oferente getOferente(String id1) throws Exception{
           return dao.OferenteGet(id1);
       }

           public Collection<Oferente> getAllOferente() throws Exception{
            return  dao.OferenteGetAll();
    
           }
    
    
    
    
     /*********************Habilidades********************************/
    
   
    public void updateHabilidades(Habilidades p) throws Exception{

                    dao.HabilidadesUpdate(p);
       }

        public void deleteHabilidades(Habilidades p) throws Exception{

            dao.HabilidadesDelete(p);
       }

         public void addHabilidades(Habilidades p) throws Exception{
           dao.HabilidadesAdd(p);
       }

          public Habilidades getHabilidades(String id1) throws Exception{
           return dao.HabilidadesGet(id1);
       }

           public Collection<Habilidades> getAllHabilidades() throws Exception{
            return  dao.HabilidadesGetAll();
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

    public Empresa getEmpresa(String id1) throws Exception{
           return dao.EmpresaGet(id1);
    }

    public Collection<Empresa> getAllEmpresa() throws Exception{
            return  dao.EmpresaGetAll();
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

    public Puestos getPuestos(String id1) throws Exception {
        return dao.PuestosGet(id1);
    }

    public Collection<Puestos> getAllPuestos() throws Exception {
        return  dao.PuestosGetAll();
    }
    
    public List<Puestos> sugerenciaListTop5() throws Exception{
       return dao.ListTop5();
    }
    
      
    /*********************CARACTERISTICAS********************************/

    public void updateCaracteristicas(Caracteristicas p) throws Exception{
        dao.CaracteristicasUpdate(p);
    }

    public void deleteCaracteristicas(Caracteristicas p) throws Exception{
        dao.CaracteristicasDelete(p);
    }

    public void addCaracteristicas(Caracteristicas p) throws Exception{
        dao.CaracteristicasAdd(p);
    }

    public Caracteristicas getCaracteristicas(int id1) throws Exception{
        return dao.CaracteristicasGet(id1);
    }

    public Collection<Caracteristicas> getAllCaracteristicas() throws Exception{
        return  dao.CaracteristicasGetAll();
    }
    
      /*********************SERVICIOS PUBLICADOS********************************/
    
         public void updateServiciosPublicados(ServiciosPublicados p) throws Exception{
        dao.ServiciosPublicadosUpdate(p);
    }
    
     public void deleteServiciosPublicados(ServiciosPublicados p) throws Exception{
        dao.ServiciosPublicadosDelete(p);
    }

      public void addServiciosPublicados(ServiciosPublicados p) throws Exception{
        dao.ServiciosPublicadosAdd(p);
    }
      
       public ServiciosPublicados getServiciosPublicados(String id1, int id2) throws Exception{
        return dao.ServiciosPublicadosGet(id1, id2);
    }
       
        public Collection<ServiciosPublicados> getAllServiciosPublicados() throws Exception{
         return  dao.ServiciosPublicadosGetAll();
    }
    
    
    
    
    
      /*********************HABILIDADES INCLUIDAS********************************/
    
    
        public void deleteHabilidadesIncluidas(HabilidadesIncluidas p) throws Exception{
        dao.HabilidadesIncluidasDelete(p);
    }

      public void addHabilidadesIncluidas(HabilidadesIncluidas p) throws Exception{
        dao.HabilidadesIncluidasAdd(p);
    }
      
       public HabilidadesIncluidas getHabilidadesIncluidas(String id1, int id2) throws Exception{
        return dao.HabilidadesIncluidasGet(id1, id2);
    }
       
        public Collection<HabilidadesIncluidas> getAllHabilidadesIncluidas() throws Exception{
         return  dao.HabilidadesIncluidasGetAll();
    }
    
    
    
    
     /*********************CARACTERISTICAS PUESTOS********************************/
    
    
         public void deleteCaracteristicasPuestos (CaracteristicasPuestos p) throws Exception{
        dao.CaracteristicasPuestosDelete(p);
    }

      public void addCaracteristicasPuestos (CaracteristicasPuestos p) throws Exception{
        dao.CaracteristicasPuestosAdd(p);
    }
      
       public CaracteristicasPuestos getCaracteristicasPuestos (int id1, int id2) throws Exception{
        return dao.CaracteristicasPuestosGet(id1, id2);
    }
       
        public Collection<CaracteristicasPuestos> getAllCaracteristicasPuestos () throws Exception{
         return  dao.CaracteristicasPuestosGetAll();
    }
    
    
    
    
    
    /************************PUESTOS PUBLICADOS********************************/
    
     public void updatePuestosPublicados(PuestosPublicados p) throws Exception{
        dao.PuestosPublicadosUpdate(p);
    }
    
     public void deletePuestosPublicados(PuestosPublicados p) throws Exception{
        dao.PuestosPublicadosDelete(p);
    }

      public void addPuestosPublicados(PuestosPublicados p) throws Exception{
        dao.PuestosPublicadosAdd(p);
    }
      
       public PuestosPublicados getPuestosPublicados(int id1, int id2) throws Exception{
        return dao.PuestosPublicadosGet(id1, id2);
    }
       
        public Collection<PuestosPublicados> getAllPuestosPublicados() throws Exception{
         return  dao.PuestosPublicadosGetAll();
    }
     
    
    /********************************* APLICADO********************************/
    
    public void deleteAplicado(Aplicado p) throws Exception{
        dao.AplicadoDelete(p);
    }

     public void addAplicado(Aplicado p) throws Exception{
        dao.AplicadoAdd(p);
    }
     
      public Aplicado getAplicado(String id1, int id2) throws Exception{
        return dao.AplicadoGet(id1, id2);
    }
      
       public Collection<Aplicado> getAllAplicado() throws Exception{
         return  dao.AplicadoGetAll();
    }
}
