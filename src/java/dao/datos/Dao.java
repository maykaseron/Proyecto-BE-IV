/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.datos;

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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    int keyCarPuesCar;
    private CaracteristicasPuestos caracteristicasPuestos(ResultSet rs){
        try {
            CaracteristicasPuestos ec= new CaracteristicasPuestos();
                
                ec.setConsecutivo( rs.getInt("consecutivo") );
                ec.setValor( rs.getInt("valor") );
                keyCarPuesCar = rs.getInt( "idCaracteristica" );
                ec.setFechaInclusion( rs.getDate("fecha_Inclusion") );

                return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
              
    public void CaracteristicasPuestosDelete(CaracteristicasPuestos p) throws Exception{
        String sql="delete from bolsaempleo.CARACTERISTICAS_PUESTOS where consecutivo=%d";
        sql = String.format(sql,p.getConsecutivo() );
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("caracteristica no ha sido incluida");
        }
    }
    
        
    public void CaracteristicasPuestosAdd(CaracteristicasPuestos p) throws Exception{
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.CARACTERISTICAS_PUESTOS (consecutivo , caracteristica , idPuesto,idCaracteristica, "
                + "valor ) values(?, ?, ?, ? )";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setInt( 1, p.getConsecutivo() );
        preparedStmt.setInt ( 2,  p.getCaracteristicas().getIdCaracteristica() );
        preparedStmt.setInt ( 3,  p.getPuesto().getIdPuesto() );
        preparedStmt.setInt( 4,  p.getValor() );
      
        preparedStmt.execute();
    }
            
    public CaracteristicasPuestos CaracteristicasPuestosGet(int codigo) throws Exception{
        String sql="select * from CARACTERISTICAS_PUESTOS where idPuesto=%d";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return caracteristicasPuestos(rs);
        }
        else{
            throw new Exception ("servicio no ha sido publicado");
        }
    }
    
    public Collection<CaracteristicasPuestos> CaracteristicasPuestosGetAll(){
        Vector<CaracteristicasPuestos> estados=new Vector<CaracteristicasPuestos>();
        try {
            String sql="select * from CARACTERISTICAS_PUESTOS";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(caracteristicasPuestos(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    public void CaracteristicasPuestosIDpuesto(int codigo, Puestos p) throws Exception{
       try { // este metodo busca CaracteristicasPuestos q tengan el id del puesto
            String sql="select * from CARACTERISTICAS_PUESTOS where idPuesto=%d;";
            sql = String.format(sql,codigo);
            ResultSet rs =  db.executeQuery(sql);
            CaracteristicasPuestos c;
            while ( rs.next() ) {
                c = caracteristicasPuestos(rs); // encotro uno
                c.setPuesto(p); // setea el atributo de puesto
                c.setCaracteristicas( this.CaracteristicasGet(keyCarPuesCar) ); // obtengo la caracteristica
                ListTop5.add(c); // lo agrego a lista magica, para agregarlo en puesto
            }
        } catch (SQLException ex) { }
    }    
    
    
    List<CaracteristicasPuestos> ListTop5 = new ArrayList<>();
    public List<Puestos> ListTop5 () throws Exception {
        List<Puestos> ListPuest = new ArrayList();
        CaracteristicasPuestos c;
           try {
            String sql="select * from Puestos p order by p.idPuesto desc limit 5;";
            ResultSet rs =  db.executeQuery(sql);
             Puestos p;
            while ( rs.next() ) {
                ListTop5 = new ArrayList(); // limpio la lista de la busqueda anterior
                p = this.puestos(rs); // obtengo puestos
                p.setEmpresa( this.EmpresaGet( rs.getInt( "idEmp" ) ) ); // obtengo la empresa asociada al puesto
                this.CaracteristicasPuestosIDpuesto ( p.getIdPuesto(), p ); // busca CaracteristicasPuestos q tengan el id del puesto
                p.setCaracteristicasPuestos ( ListTop5 ); // agrego la lista CaracteristicasPuestos aL puesto
                ListPuest.add(p); // lo agrego a lista magica
            }
        } catch (SQLException ex) { }
        return ListPuest;
    }
    
    // 1_PuestosPorCaracteristicas trae puetosXpuesto, cada vez q tiene uno "setea" atributo empresa.luego 2_CaracteristicasPuestosIDpuestoAll
    // ,este busca en los CARACTERISTICAS_PUESTOS quien tiene el mismo idPuesto. Ahora tiene construido a puesto,empresa 
    // y construye a CARACTERISTICAS_PUESTOS, depues por medio de su atributo idCaracteristica trae a CARACTERISTICA y lo compara 
    // con el q tengo por parametro, si es? entonces lo agrega a puetosAll. 
    List<CaracteristicasPuestos> puetosAll = new ArrayList<>();
    public List<CaracteristicasPuestos> PuestosPorCaracteristicas ( String especializacion ) throws Exception {
        List<CaracteristicasPuestos> resultado = new ArrayList<>();
        puetosAll = new ArrayList<>(); // lo limpia de la busqueda pasada
        try {
            String sql="select * from Puestos;";
            ResultSet rs =  db.executeQuery(sql);
            Puestos p;
            while ( rs.next() ) {
                p = this.puestos(rs);
                p.setEmpresa( this.EmpresaGet( rs.getInt( "idEmp" ) ) );
                CaracteristicasPuestosIDpuestoAll ( p.getIdPuesto(), p, especializacion );
            }
        } catch (SQLException ex) { }
        return puetosAll;
    }
    
    public void CaracteristicasPuestosIDpuestoAll(int codigo, Puestos p, String especializacion ) throws Exception{
       try {
            String sql="select * from CARACTERISTICAS_PUESTOS where idPuesto=%d;";
            sql = String.format(sql,codigo);
            ResultSet rs =  db.executeQuery(sql);
            CaracteristicasPuestos c;
            while ( rs.next() ) {
                c = caracteristicasPuestos(rs);
                c.setPuesto(p);
                c.setCaracteristicas( this.CaracteristicasGet(keyCarPuesCar) );
                if ( especializacion.equals( c.getCaracteristicas().getEspecializacion() ))
                    puetosAll.add(c);
            }
        } catch (SQLException ex) { }
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
                ec.setIdCaracteristica( rs.getInt("idCaracteristica") );
                ec.setAreaTrabajo( rs.getString("areaTrabajo") );
                ec.setEspecializacion( rs.getString("especializacion") );
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
    public void CaracteristicasUpdate(Caracteristicas p) throws Exception{
        String sql="update bolsaempleo.caracteristicas set areaTrabajo='%s', especializacion='%s'"   +
                " where idCaracteristica='%s'";
        sql=String.format(sql,p.getAreaTrabajo(),
                p.getEspecializacion(),p.getIdCaracteristica());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("caracteristica no existe");
        }
    }
              
    public void CaracteristicasDelete(Caracteristicas p) throws Exception{
        String sql="delete from bolsaempleo.caracteristicas where idCaracteristica=%d";
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
        preparedStmt.setInt( 1, p.getIdCaracteristica() );
        preparedStmt.setString ( 2, p.getAreaTrabajo() );
        preparedStmt.setString ( 3, p.getEspecializacion() );
      
       preparedStmt.execute();
    }
      
      
    public Caracteristicas CaracteristicasGet(int codigo) throws Exception{
        String sql="select * from caracteristicas where idCaracteristica=%d";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql); 
        if (rs.next()) {
            return caracteristicas(rs);
        }
        else{
            throw new Exception ("puesto no Existe");
        }
    }
    
    // busca por areaTrabajo
    public List<Caracteristicas> CaracteristicasEspecializ(String areaTrabajo) throws Exception{
        List<Caracteristicas> estados=new Vector<Caracteristicas>();
        Caracteristicas aux;
        try {
            String sql="select * from caracteristicas";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                aux = caracteristicas(rs);
                if ( areaTrabajo.equals( aux.getAreaTrabajo() ) )
                    estados.add( aux );
            }
        } catch (SQLException ex) { }
        return estados;  
    }
    
    public List<Caracteristicas> CaracteristicasAreaTrabajo( ) throws Exception{
        List<Caracteristicas> estados=new Vector<Caracteristicas>();
        Caracteristicas aux; String areaTrabajo = "as";
        try {
            String sql="select * from caracteristicas";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                aux = caracteristicas(rs);
                if ( !areaTrabajo.equals( aux.getAreaTrabajo() ) ) 
                    estados.add( aux );
                areaTrabajo = aux.getAreaTrabajo();
            }
        } catch (SQLException ex) { }
        return estados;  
    }
    
    public Collection<Caracteristicas> CaracteristicasGetAll(){
        List<Caracteristicas> estados=new Vector<Caracteristicas>();
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
    
    private Puestos puestos(ResultSet rs) {
        try {
            Puestos ec= new Puestos();
                ec.setIdPuesto(rs.getInt("idPuesto"));
                // ec.setEmpresa( this.empresa(rs) );
                ec.setNombrePuesto(rs.getString("nombrePuesto"));
                ec.setSalario(rs.getFloat("salario"));
                ec.setDescripcionPuesto(rs.getString("descripcionPuesto"));
                ec.setTipoPublicacion( rs.getBoolean("tipoPublicacion") );
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
    public void PuestosUpdate(Puestos p) throws Exception {
        String sql="update bolsaempleo.puestos set nombrePuesto='%s', idEmp='%s', salario='%s' , descripcionPuesto='%s', "
                + "tipoPublicacion=%b where idPuesto='%s'";
        sql=String.format(sql,p.getNombrePuesto(), p.getEmpresa().getIdEmp(),
                p.getSalario(),p.getDescripcionPuesto(), p.getIdPuesto(), p.getTipoPublicacion() );
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("puestos no existe");
        }
    }
              
    public void PuestosDelete(Puestos p) throws Exception {
        String sql="delete from bolsaempleo.puestos where idPuesto='%s'";
        sql = String.format(sql,p.getIdPuesto());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("puesto no existe");
        }
    }
        
    public void PuestosAdd(Puestos p) throws Exception {
            System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.puestos (nombrePuesto, idEmp, salario, descripcionPuesto, tipoPublicacion ) "+
                "values(?, ?, ?, ?, ?)";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setString(1, p.getNombrePuesto());
        preparedStmt.setFloat (2, p.getSalario());
        preparedStmt.setString (3, p.getDescripcionPuesto());
        preparedStmt.setBoolean(4, p.getTipoPublicacion() );
        
       preparedStmt.execute();
    }
      
    public Puestos PuestosGet(int codigo) throws Exception {
        String sql="select * from puestos where idPuesto=%d";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return puestos(rs);
        }
        else{
            throw new Exception ("puesto no Existe");
        }
    }
        
    public Collection<Puestos> PuestosGetAll() {
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
    /*
    public List<Puestos> ListTop5 () throws Exception {
        List<Puestos> resultado = new ArrayList<>();
           try {
            String sql="select * from Puestos p inner join CARACTERISTICAS_PUESTOS c on p.idPuesto=c.consecutivo order "
                    + "by p.idPuesto desc limit 5;";
            ResultSet rs =  db.executeQuery(sql);
            Puestos p;
            CaracteristicasPuestos c;
            while (rs.next()) {
                p = puestos(rs);
                //c = this.caracteristicasPuestos(rs);
                
                //p.setEmpresa(empresa(rs));
                resultado.add(p);
            }
        } catch (SQLException ex) { }
        return resultado;
    }
    */
    
    /***********************************************************************/
     
    private Empresa empresa(ResultSet rs){
        try {
            Empresa ec= new Empresa();
                ec.setNombreEmp(rs.getString("nombreEmp"));
                ec.setUbicacionEmp(rs.getString("ubicacionEmp"));
                ec.setDescripcionEmp(rs.getString("descripcionEmp"));
                ec.setCorreoEmp(rs.getString("correoEmp"));
                ec.setTeléfono( rs.getString("telefono") );
                ec.setIdEmp(rs.getInt("idEmp"));
        
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
     
    public void EmpresaUpdate(Empresa p) throws Exception{
        String sql="update bolsaempleo.empresa set nombreEmp='%s', ubicacionEmp='%s', descripcionEmp='%s', correoEmp='%s', "
                + "telefono='%s' where idEmp=%d";
        sql=String.format(sql,p.getNombreEmp(),
                p.getUbicacionEmp(),p.getDescripcionEmp(),p.getCorreoEmp(), p.getIdEmp(), p.getTeléfono() );
        
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
       /* String sql="insert into EMPRESA (nombreEmp,ubicacionEmp,descripcionEmp,correoEmp,telefono)"
                + " values ('%s','%s','%s','%s','%s');";
       sql=String.format( sql,p.getNombreEmp(), p.getUbicacionEmp(), p.getDescripcionEmp(), 
                p.getCorreoEmp(), p.getTeléfono() );
        db.executeUpdate(sql);      */
       if ( this.compararEmpresaVacio(p) ) {  //  add si no hay espacios vacios agrega
           String sql="insert into bolsaempleo.empresa (nombreEmp , ubicacionEmp, descripcionEmp, correoEmp, telefono ) "+
                "values(? ,? ,? ,? ,?)";
            //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
            db.getConnection();
            PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
            preparedStmt.setString(1, p.getNombreEmp());
            preparedStmt.setString (2, p.getUbicacionEmp());
            preparedStmt.setString (3, p.getDescripcionEmp());
            preparedStmt.setString (4, p.getCorreoEmp());
            preparedStmt.setString (5, p.getTeléfono() );

           preparedStmt.execute();
       }
    }
      
      
    public Empresa EmpresaGet(int codigo) throws Exception{
        String sql="select * from empresa where idEmp=%d";
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
    
    public boolean compararEmpresaVacio (Empresa e) { 
        return ! ( e.getNombreEmp().equals("") || e.getTeléfono().equals("") 
                || e.getCorreoEmp().equals("") || e.getDescripcionEmp().equals("") );  // no hay espacios vacios
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
       
    public void OferenteAdd(Oferente p) throws Exception {
        if ( this.compararOferenteVacio(p) ) { // add si no hay espacios vacios
            String sql= "insert into OFERENTE (cedulaOferente,nombreOferente ,primerApellido ,celular,nacionalidad,correoOferente,ubicacion) "
                + "values ('%s','%s','%s','%s','%s','%s','%s'); ";
            sql=String.format( sql, p.getCedulaOferente(), p.getNombreOferente(), p.getPrimerApellido(), p.getCelular(), p.getNacionalidad(),
                    p.getCorreoOferente(), p.getUbicacion() );
            db.executeUpdate(sql);  
        }
        /*
        System.out.println("en oferenteAdd");
        String sql="insert into bolsaempleo.oferente (cedulaOferente , nombreOferente , primerApellido , celular, nacionalidad, correoOferente, ubicacion  ) "+
                "values(? ,? ,? ,? ,?, ?, ?)";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        System.out.println("despues de prepared" );
        preparedStmt.setString (1, p.getCedulaOferente());
        preparedStmt.setString (2, p.getNombreOferente());
        preparedStmt.setString (3, p.getPrimerApellido());
        preparedStmt.setString (4, p.getCelular());
        preparedStmt.setString (5, p.getNacionalidad());
        preparedStmt.setString (6, p.getCorreoOferente());
        preparedStmt.setString (7, p.getUbicacion());
        preparedStmt.execute();*/
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
         
    public void OferenteUpdate(Oferente p) throws Exception {
        String sql="update bolsaempleo.oferente set  nombreOferente='%s', primerApellido='%s' , segundoApellido='%s' , celular='%s', nacionalidad='%s' , correoOferente='%s' , ubicacion='%s'"   +
                "where cedulaOferente='%s'";
        sql=String.format(sql,p.getNombreOferente(),
                p.getPrimerApellido(),p.getCelular() , p.getNacionalidad(), p.getCorreoOferente(), p.getUbicacion(), p.getCedulaOferente());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Oferente no existe");
        }
    }
      
    private Oferente oferente(ResultSet rs) {
        try {
            Oferente ec= new Oferente();
                ec.setCedulaOferente(rs.getString("cedulaOferente"));
                ec.setPrimerApellido(rs.getString("primerApellido"));
                ec.setNombreOferente(rs.getString("nombreOferente"));
                ec.setNacionalidad(rs.getString("nacionalidad"));
                ec.setCorreoOferente(rs.getString("correoOferente"));
                ec.setUbicacion(rs.getString("ubicacion"));

            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public boolean compararOferenteVacio (Oferente o) { // 
        return !( o.getNombreOferente().equals("") || o.getPrimerApellido().equals("") || o.getCedulaOferente().equals("") ||
                o.getCelular().equals("") || o.getNacionalidad().equals("") ); // no hay espacios vacios
        
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
