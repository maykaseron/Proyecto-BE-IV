/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.datos;

import entidades.Aplicado;
import entidades.Caracteristicas;
import entidades.CaracteristicasIncluidos;
import entidades.Empresa;
import entidades.Habilidades;
import entidades.HabilidadesIncluidas;
import entidades.Oferente;
import entidades.Puestos;
import entidades.PuestosPublicados;
import entidades.Servicios;
import entidades.ServiciosPublicados;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;




/**
 *
 * @author pc
 */
public class Dao {
    
    RelDatabase db;  
    
    public Dao() throws ClassNotFoundException, SQLException, IOException{
        db= new RelDatabase();
    }
    
    
      private Aplicado aplicado(ResultSet rs){
        try {
            Aplicado ec= new Aplicado();
          
                ec.setFechaAplicacion(rs.getDate("fechaAplicacion"));
                ec.setIdOferente(rs.getString("idOferente"));
                ec.setIdPuestos(rs.getInt("idPuestos"));
               
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
      
              
              
                 public void AplicadoDelete(Aplicado p) throws Exception{
        String sql="delete from bolsaempleo.Aplicado where cedulaOferente='%s' and idPuesto='%s' ";
        sql = String.format(sql,p.getIdOferente(), p.getIdPuestos());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("puesto buscado inexistente");
        }
    }
    
        
      public void AplicadoAdd(Aplicado p) throws Exception{
           
            
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.Aplicado (fechaAplicacion , cedulaOferente , idPuesto ) "+
                "values(? ,? ,? )";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setDate(1, p.getFechaAplicacion());
        preparedStmt.setString (2, p.getIdOferente());
        preparedStmt.setInt(3, p.getIdPuestos());
        
      
       preparedStmt.execute();
       
    }
      
      
        public Aplicado AplicadoGet(String codigo, int codigo2) throws Exception{
        String sql="select * from aplicado where cedulaOferente='%s' and idPuesto='%s'";
        sql = String.format(sql,codigo, codigo2);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return aplicado(rs);
        }
        else{
            throw new Exception ("aplicacion no Existe");
            
        }
    }
        
            public Collection<Aplicado> AplicadoGetAll(){
        Vector<Aplicado> estados=new Vector<Aplicado>();
        try {
            String sql="select * from Aplicado";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(aplicado(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    
    
    
    
    /********************************************************************/
      
    
     private PuestosPublicados puestosPublicados(ResultSet rs){
        try {
            PuestosPublicados ec= new PuestosPublicados();
          
                ec.setEmpresa(rs.getInt("idEmpresa"));
                ec.setEstadoPuesto(rs.getBoolean("estadoPuestos"));
                ec.setidPuestos(rs.getInt("idPuestos"));
               
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
              public void PuestosPublicadosUpdate(PuestosPublicados p) throws Exception{
        String sql="update bolsaempleo.puestos_publicados set estadoPuesto='%s'"   +
                "where idEmp='%s' and idPuesto='%s'";
        sql=String.format(sql,p.getEstadoPuesto(),
                p.getidEmpresa(),p.getidPuestos());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("puesto buscado inexistente");
        }
    }
              
              
                 public void PuestosPublicadosDelete(PuestosPublicados p) throws Exception{
        String sql="delete from bolsaempleo.puestos_publicados where idEmp='%s' and idPuesto='%s' ";
        sql = String.format(sql,p.getidEmpresa(), p.getidPuestos());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("puesto buscado inexistente");
        }
    }
    
        
      public void PuestosPublicadosAdd(PuestosPublicados p) throws Exception{
           
            
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.puestos_publicados (idEmp , idPuesto , estadoPuesto ) "+
                "values(? ,? ,? )";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setInt(1, p.getidEmpresa());
        preparedStmt.setInt (2, p.getidPuestos());
        preparedStmt.setBoolean(3, p.getEstadoPuesto());
        
      
       preparedStmt.execute();
       
    }
      
      
        public PuestosPublicados PuestosPublicadosGet(int codigo, int codigo2) throws Exception{
        String sql="select * from puestos_publicados where idEmp='%s' and idPuesto='%s'";
        sql = String.format(sql,codigo, codigo2);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return puestosPublicados(rs);
        }
        else{
            throw new Exception ("puesto no Existe");
            
        }
    }
        
            public Collection<PuestosPublicados> PuestosPublicadosGetAll(){
        Vector<PuestosPublicados> estados=new Vector<PuestosPublicados>();
        try {
            String sql="select * from puestos_publicados";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(puestosPublicados(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    /*********************************************************************/
    
    
      private CaracteristicasIncluidos caracteristicasIncluidos(ResultSet rs){
        try {
            CaracteristicasIncluidos ec= new CaracteristicasIncluidos();
          
                ec.setFechaInclusion(rs.getDate("fechaInclusion"));
                ec.setIdCaracteristicaIn(rs.getString("idCaracteristicaIn"));
                ec.setidPuestos(rs.getInt("idServicio"));
                
               
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
      
              
              
                 public void CaracteristicasIncluidosDelete(CaracteristicasIncluidos p) throws Exception{
        String sql="delete from bolsaempleo.caracteristicas_incluidos where idPuesto='%s' and idCaracteristica='%s'";
        sql = String.format(sql,p.getidPuestos(), p.getIdCaracteristicaIn());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("caracteristica no ha sido incluida");
        }
    }
    
        
      public void CaracteristicasIncluidosAdd(CaracteristicasIncluidos p) throws Exception{
           
            
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.caracteristicas_incluidos (idPuesto , idCaracteristica , fecha_Inclusion ) "+
                "values(? ,? ,? )";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setInt(1, p.getidPuestos());
        preparedStmt.setString (2, p.getIdCaracteristicaIn());
        preparedStmt.setDate(3, p.getFechaInclusion());
        
      
       preparedStmt.execute();
       
    }
      
      
        public CaracteristicasIncluidos CaracteristicasIncluidosGet(int codigo, String codigo2) throws Exception{
        String sql="select * from caracteristicas_incluidos where idPuesto='%s' and idCaracteristica='%s'";
        sql = String.format(sql,codigo,codigo2);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return caracteristicasIncluidos(rs);
        }
        else{
            throw new Exception ("servicio no ha sido publicado");
            
        }
    }
        
            public Collection<CaracteristicasIncluidos> CaracteristicasIncluidosGetAll(){
        Vector<CaracteristicasIncluidos> estados=new Vector<CaracteristicasIncluidos>();
        try {
            String sql="select * from caracteristicas_incluidos";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(caracteristicasIncluidos(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    
    
    
    
    
    
    
    
    
    /************************************************************************/
    
     private HabilidadesIncluidas habilidadesIncluidas(ResultSet rs){
        try {
            HabilidadesIncluidas ec= new HabilidadesIncluidas();
          
                ec.setFechaInclusion(rs.getDate("fechaInclusion"));
                ec.setIdHabilidad(rs.getInt("idHabilidad"));
                ec.setServicios(rs.getInt("idServicio"));
       
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
      
              
              
                 public void HabilidadesIncluidasDelete(HabilidadesIncluidas p) throws Exception{
        String sql="delete from bolsaempleo.habilidades_incluidas where idServicio='%s' and idHabilidad='%s'";
        sql = String.format(sql,p.getidServicios(), p.getIdHabilidad());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("habilidad no ha sido incluida");
        }
    }
    
        
      public void HabilidadesIncluidasAdd(HabilidadesIncluidas p) throws Exception{
           
            
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.habilidades_incluidas (idServicio , idHabilidad , fecha_Inclusion ) "+
                "values(? ,? ,? )";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setInt(1, p.getidServicios());
        preparedStmt.setInt (2, p.getIdHabilidad());
        preparedStmt.setDate(3, p.getFechaInclusion());
        
      
       preparedStmt.execute();
       
    }
      
      
        public HabilidadesIncluidas HabilidadesIncluidasGet(String codigo, int codigo2) throws Exception{
        String sql="select * from habilidades_incluidas where idServicio='%s' and idHabilidad='%s'";
        sql = String.format(sql,codigo,codigo2);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return habilidadesIncluidas(rs);
        }
        else{
            throw new Exception ("habilidad no ha sido incluida");
            
        }
    }
        
            public Collection<HabilidadesIncluidas> HabilidadesIncluidasGetAll(){
        Vector<HabilidadesIncluidas> estados=new Vector<HabilidadesIncluidas>();
        try {
            String sql="select * from habilidades_incluidas";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(habilidadesIncluidas(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    
    
    
    
    
    
    
    
    /**********************************************************************/
    
      private ServiciosPublicados serviciosPublicados(ResultSet rs){
        try {
            ServiciosPublicados ec= new ServiciosPublicados();
          
                ec.setCedulaOferente(rs.getString("cedulaOferente"));
                ec.setEstadoServicio(rs.getBoolean("estadoServicios"));
                ec.setIdServicio(rs.getInt("idServicio"));
                
               
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
              public void ServiciosPublicadosUpdate(ServiciosPublicados p) throws Exception{
        String sql="update bolsaempleo.servios_publicados set estadoServicio='%s'"   +
                "where cedulaOferente='%s' and idServicio='%s'";
        sql=String.format(sql,p.getEstadoServicio(),
                p.getCedulaOferente(),p.getIdServicio());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("servicio no ha sido publicado");
        }
    }
              
              
                 public void ServiciosPublicadosDelete(ServiciosPublicados p) throws Exception{
        String sql="delete from bolsaempleo.servios_publicados where cedulaOferente='%s' and idServicio='%s'";
        sql = String.format(sql,p.getCedulaOferente(), p.getIdServicio());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("servicio no ha sido publicado");
        }
    }
    
        
      public void ServiciosPublicadosAdd(ServiciosPublicados p) throws Exception{
           
            
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.servios_publicados (cedulaOferente , idServicio , estadoServicio ) "+
                "values(? ,? ,? )";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setString(1, p.getCedulaOferente());
        preparedStmt.setInt (2, p.getIdServicio());
        preparedStmt.setBoolean(3, p.getEstadoServicio());
        
      
       preparedStmt.execute();
       
    }
      
      
        public ServiciosPublicados ServiciosPublicadosGet(String codigo, int codigo2) throws Exception{
        String sql="select * from servios_publicados where cedulaOferente='%s' and idServicio='%s'";
        sql = String.format(sql,codigo,codigo2);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return serviciosPublicados(rs);
        }
        else{
            throw new Exception ("servicio no ha sido publicado");
            
        }
    }
        
            public Collection<ServiciosPublicados> ServiciosPublicadosGetAll(){
        Vector<ServiciosPublicados> estados=new Vector<ServiciosPublicados>();
        try {
            String sql="select * from servios_publicados";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(serviciosPublicados(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    
    
    
    
    /*********************************************************************/
    
    
    
    
     private Caracteristicas caracteristicas(ResultSet rs){
        try {
            Caracteristicas ec= new Caracteristicas();
          
                ec.setIdCaracteristica(rs.getString("idCaracteristica"));
                ec.setAreaTrabajo(rs.getString("areaTrabajo"));
                ec.setEspecializacion(rs.getString("especializacion"));
               
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
              public void CaracteristicasUpdate(Caracteristicas p) throws Exception{
        String sql="update bolsaempleo.caracteristicas set areaTrabajo='%s', especializacion='%s'"   +
                "where idCaracteristica='%s'";
        sql=String.format(sql,p.getAreaTrabajo(),
                p.getEspecializacion(),p.getIdCaracteristica());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("caracteristica no existe");
        }
    }
              
              
                 public void CaracteristicasDelete(Caracteristicas p) throws Exception{
        String sql="delete from bolsaempleo.caracteristicas where idCaracteristica='%s'";
        sql = String.format(sql,p.getIdCaracteristica());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("puesto no existe");
        }
    }
    
        
      public void CaracteristicasAdd(Caracteristicas p) throws Exception{
           
            
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.caracteristicas (idCaracteristica , areaTrabajo , especializacion ) "+
                "values(? ,? ,? )";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setString(1, p.getIdCaracteristica());
        preparedStmt.setString (2, p.getAreaTrabajo());
        preparedStmt.setString (3, p.getEspecializacion());
        
      
       preparedStmt.execute();
       
    }
      
      
        public Caracteristicas CaracteristicasGet(String codigo) throws Exception{
        String sql="select * from caracteristicas where idCaracteristica='%s'";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return caracteristicas(rs);
        }
        else{
            throw new Exception ("puesto no Existe");
            
        }
    }
        
            public Collection<Caracteristicas> CaracteristicasGetAll(){
        Vector<Caracteristicas> estados=new Vector<Caracteristicas>();
        try {
            String sql="select * from caracteristicas";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(caracteristicas(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    
    
    
    
    
    
    
    
    
    
    /**********************************************************************/
    
    
    
    private Puestos puestos(ResultSet rs){
        try {
            Puestos ec= new Puestos();
          
                ec.setDescripcionPuesto(rs.getString("descripcionPuesto"));
                ec.setIdPuesto(rs.getInt("idPuesto"));
                ec.setNombrePuesto(rs.getString("nombrePuesto"));
                ec.setSalario(rs.getFloat("salario"));
                ec.setUbicacion(rs.getString("ubicacion"));
                
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
              public void PuestosUpdate(Puestos p) throws Exception{
        String sql="update bolsaempleo.puestos set nombrePuesto='%s', salario='%s' , descripcionPuesto='%s', ubicacion='%s'"   +
                "where idPuesto='%s'";
        sql=String.format(sql,p.getNombrePuesto(),
                p.getSalario(),p.getDescripcionPuesto(),p.getUbicacion(), p.getIdPuesto());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("puestos no existe");
        }
    }
              
              
                 public void PuestosDelete(Puestos p) throws Exception{
        String sql="delete from bolsaempleo.puestos where idPuesto='%s'";
        sql = String.format(sql,p.getIdPuesto());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("puesto no existe");
        }
    }
    
        
      public void PuestosAdd(Puestos p) throws Exception{
           
            
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.puestos (nombrePuesto , salario , descripcionPuesto , ubicacion ) "+
                "values(? ,? ,? ,?)";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setString(1, p.getNombrePuesto());
        preparedStmt.setFloat (2, p.getSalario());
        preparedStmt.setString (3, p.getDescripcionPuesto());
        preparedStmt.setString (4, p.getUbicacion());
      
       preparedStmt.execute();
       
    }
      
      
        public Puestos PuestosGet(String codigo) throws Exception{
        String sql="select * from puestos where idPuesto='%s'";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return puestos(rs);
        }
        else{
            throw new Exception ("puesto no Existe");
            
        }
    }
        
            public Collection<Puestos> PuestosGetAll(){
        Vector<Puestos> estados=new Vector<Puestos>();
        try {
            String sql="select * from puestos";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(puestos(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    
    
    
    /***********************************************************************/
     
    private Empresa empresa(ResultSet rs){
        try {
            Empresa ec= new Empresa();
          
                ec.setCorreoEmp(rs.getString("correoEmpresa"));
                ec.setDescripcionEmp(rs.getString("descripcionEmpresa"));
                ec.setFechaRegistro(rs.getDate("fechaRegistro"));
                ec.setIdEmp(rs.getInt("idEmpresa"));
                ec.setNombreEmp(rs.getString("nombreEmpresa"));
                ec.setUbicacionEmp(rs.getString("ubicacionEmpresa"));
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
              public void EmpresaUpdate(Empresa p) throws Exception{
        String sql="update bolsaempleo.empresa set nombreEmp='%s', ubicacionEmp='%s' , latitud='%s', longitud='%s', descripcionEmp='%s', correoEmp='%s'"   +
                "where idEmp='%s'";
        sql=String.format(sql,p.getNombreEmp(),
                p.getUbicacionEmp(),p.getLatitud(),p.getLongitud(), p.getDescripcionEmp(),p.getCorreoEmp(), p.getIdEmp());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("empresa no existe");
        }
    }
              
              
                 public void EmpresaDelete(Empresa p) throws Exception{
        String sql="delete from bolsaempleo.empresa where idEmp='%s'";
        sql = String.format(sql,p.getIdEmp());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("empresa no existe");
        }
    }
    
        
    public void EmpresaAdd(Empresa p) throws Exception{
        String sql="insert into EMPRESA (nombreEmp,ubicacionEmp,descripcionEmp,correoEmp,telefono)"
                + " values ('%s','%s','%s','%s','%s');";
       sql=String.format( sql,p.getNombreEmp(), p.getUbicacionEmp(), p.getDescripcionEmp(), 
                p.getCorreoEmp(), p.getTeléfono() );
        db.executeUpdate(sql);
        
        /*System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.empresa (nombreEmp , ubicacionEmp , latitud , longitud, descripcionEmp, correoEmp ) "+
                "values(? ,? ,? ,? ,? ,?)";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setString(1, p.getNombreEmp());
        preparedStmt.setString (2, p.getUbicacionEmp());
        preparedStmt.setString (3, p.getLatitud());
        preparedStmt.setString (4, p.getLongitud());
        preparedStmt.setString (5, p.getDescripcionEmp());
        preparedStmt.setString (6, p.getCorreoEmp());
                
      
       preparedStmt.execute();
       */
    }
      
      
        public Empresa EmpresaGet(String codigo) throws Exception{
        String sql="select * from empresa where idEmp='%s'";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return empresa(rs);
        }
        else{
            throw new Exception ("Habilidad no Existe");
            
        }
    }
        
            public Collection<Empresa> EmpresaGetAll(){
        Vector<Empresa> estados=new Vector<Empresa>();
        try {
            String sql="select * from empresa";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(empresa(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    
    
    
    
    /********************************************************************/
    
                public void HabilidadesUpdate(Habilidades p) throws Exception{
        String sql="update bolsaempleo.habilidades set  nombreHabilidad='%s', areaTrabajo='%s' , especializacion='%s'"   +
                "where idHabilidad='%s'";
        sql=String.format(sql,p.getNombreHabilidad(),
                p.getAreaTrabajo(),p.getEspecializacion(), p.getIdHabilidad());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("habilidad no existe");
        }
    }
    
          public void HabilidadesDelete(Habilidades p) throws Exception{
        String sql="delete from bolsaempleo.habilidades where idHabilidad='%s'";
        sql = String.format(sql,p.getIdHabilidad());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Habilidad no existe");
        }
    }
    
      public void HabilidadesAdd(Habilidades p) throws Exception{
           
            
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.habilidades (idHabilidad , nombreHabilidad , areaTrabajo , especializacion ) "+
                "values(? ,? ,? ,?)";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setInt(1, p.getIdHabilidad());
        preparedStmt.setString (2, p.getNombreHabilidad());
        preparedStmt.setString (3, p.getAreaTrabajo());
        preparedStmt.setString (4, p.getEspecializacion());
      
       preparedStmt.execute();
       
    }
    
    public Habilidades HabilidadesGet(String codigo) throws Exception{
        String sql="select * from habilidades where idHabilidad='%s'";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return habilidades(rs);
        }
        else{
            throw new Exception ("Habilidad no Existe");
            
        }
    }
    
      public Collection<Habilidades> HabilidadesGetAll(){
        Vector<Habilidades> estados=new Vector<Habilidades>();
        try {
            String sql="select * from habilidades";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(habilidades(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
       
    
    
     private Habilidades habilidades(ResultSet rs){
        try {
            Habilidades ec= new Habilidades();
          
                ec.setAreaTrabajo(rs.getString("areaTrabajo"));
                ec.setEspecializacion(rs.getString("especializacion"));
                ec.setIdHabilidad(rs.getInt("idHabilidad"));
                ec.setNombreHabilidad(rs.getString("nombreHabilidad"));
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    /***************************************************************************/
    
      public Oferente OferenteGet(String codigo) throws Exception{
        String sql="select * from oferente where cedulaOferente='%s'";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return oferente(rs);
        }
        else{
            throw new Exception ("Oferente no Existe");
            
        }
    }
      
       public Collection<Oferente> OferenteGetAll(){
        Vector<Oferente> estados=new Vector<Oferente>();
        try {
            String sql="select * from oferente";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(oferente(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
       
       
        public void OferenteAdd(Oferente p) throws Exception{
           
            
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.oferente (cedulaOferente , nombreOferente , primerApellido , segundoApellido , celular, nacionalidad, correoOferente, ubicacion  ) "+
                "values(? ,? ,? ,? ,?, ?, ?, ?)";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setString (1, p.getCedulaOferente());
        preparedStmt.setString (2, p.getNombreOferente());
        preparedStmt.setString (3, p.getPrimerApellido());
        preparedStmt.setString (4, p.getSegundoApellido());
        preparedStmt.setString (5, p.getCelular());
        preparedStmt.setString (6, p.getNacionalidad());
        preparedStmt.setString (7, p.getCorreoOferente());
        preparedStmt.setString (8, p.getUbicacion());
        
      
      
       preparedStmt.execute();
       
    }
        
         public void OferenteDelete(Oferente p) throws Exception{
        String sql="delete from bolsaempleo.oferente where cedulaOferente='%s'";
        sql = String.format(sql,p.getCedulaOferente());
        db.getConnection();
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Oferente no existe");
        }
    }
         
          public void OferenteUpdate(Oferente p) throws Exception{
        String sql="update bolsaempleo.oferente set  nombreOferente='%s', primerApellido='%s' , segundoApellido='%s' , celular='%s', nacionalidad='%s' , correoOferente='%s' , ubicacion='%s'"   +
                "where cedulaOferente='%s'";
        sql=String.format(sql,p.getNombreOferente(),
                p.getPrimerApellido(),p.getSegundoApellido(),p.getCelular() , p.getNacionalidad(), p.getCorreoOferente(), p.getUbicacion(), p.getCedulaOferente());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Oferente no existe");
        }
    }
      
    
      
        private Oferente oferente(ResultSet rs){
        try {
            Oferente ec= new Oferente();
          
                ec.setCedulaOferente(rs.getString("cedulaOferente"));
                ec.setPrimerApellido(rs.getString("primerApellido"));
                ec.setNombreOferente(rs.getString("nombreOferente"));
                ec.setSegundoApellido(rs.getString("segundoApellido"));
                ec.setNacionalidad(rs.getString("nacionalidad"));
                ec.setCorreoOferente(rs.getString("correoOferente"));
                ec.setUbicacion(rs.getString("ubicacion"));
                   
            
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
        
  /*************************************************************************************************************************/      
        
         public Servicios ServicioGet(String codigo) throws Exception{
        String sql="select * from servicios  where idServicio='%s'";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return servicios(rs);
        }
        else{
            throw new Exception ("Servicio no Existe");
            
        }
    }
         
         
         public Collection<Servicios> ServiciosGetAll(){
        Vector<Servicios> estados=new Vector<Servicios>();
        try {
            String sql="select * from servicios";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(servicios(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
         
         
            public void ServiciosAdd(Servicios p) throws Exception{
           
            
            System.out.println("en ServiciosAdd");
        String sql="insert into bolsaempleo.servicios (idServicio , nombreServicio , salarioEsperado , descripcionDescripcion ) "+
                "values(? ,? ,? ,? )";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setInt(1, p.getIdServicio());
        preparedStmt.setString (2, p.getNombreServicio());
        preparedStmt.setFloat(3, p.getSalarioEsperado());
        preparedStmt.setString (4, p.getDescripcionDescripcion());
      
        
      
      
       preparedStmt.execute();
       
    }
            
            
                public void ServiciosDelete(Servicios p) throws Exception{
        String sql="delete from bolsaempleo.servicios where idServicio='%s'";
        sql = String.format(sql,p.getIdServicio());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Servicio no existe");
        }
    }
                
                 public void ServiciosUpdate(Servicios p) throws Exception{
        String sql="update bolsaempleo.servicios set  nombreServicio='%s' , salarioEsperado='%s' , descripcionDescripcion='%s'"   +
                "where idServicio='%s'";
        sql=String.format(sql,p.getNombreServicio(),
                p.getSalarioEsperado(),p.getDescripcionDescripcion(), p.getIdServicio());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Servicio no existe");
        }
    }
      
        
          private Servicios servicios(ResultSet rs){
        try {
            Servicios ec= new Servicios();
          
                ec.setDescripcionDescripcion(rs.getString("descripcion"));
                ec.setNombreServicio(rs.getString("nombreServicio"));
                ec.setSalarioEsperado(rs.getFloat("salarioEsperado"));
                ec.setIdServicio(rs.getInt("idServicio"));
               
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
      
    
    
}
