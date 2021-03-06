/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.datos;

import entidades.Administrador;
import entidades.Caracteristicas;
import entidades.CaracteristicasOferente;
import entidades.CaracteristicasPuestos;
import entidades.Empresa;
import entidades.Oferente;
import entidades.Puestos;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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
    
    /*********************Empresa********************************/
     
    private Empresa empresa(ResultSet rs) throws Exception{
        Empresa ec= new Empresa();

            ec.setNombreEmp(rs.getString("nombreEmp"));
            ec.setUbicacionEmp(rs.getString("ubicacionEmp"));
            ec.setDescripcionEmp(rs.getString("descripcionEmp"));
            ec.setCorreoEmp(rs.getString("correoEmp"));
            ec.setTeléfono( rs.getString("telefono") );
            ec.setContrasena( rs.getString("contrasena") );
            ec.setIdEmp(rs.getInt("idEmp"));
            ec.setAprobado( rs.getBoolean( "aprobado" ) );
            
        return ec;
    }
     
    public void EmpresaUpdate(Empresa p) throws Exception{
        String sql="update bolsaempleo.empresa set nombreEmp='%s', ubicacionEmp='%s', descripcionEmp='%s', correoEmp='%s', "
                + "telefono='%s', aprobado=%b where idEmp=%d";
        sql=String.format(sql,p.getNombreEmp(),
                p.getUbicacionEmp(),p.getDescripcionEmp(),p.getCorreoEmp(),  p.getTeléfono(),p.getAprobado(), p.getIdEmp() );
        System.out.println(sql);
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("empresa no existe");
        }
    }
              
    public void EmpresaDelete(Empresa p) throws Exception{
        String sql="delete from bolsaempleo.empresa where idEmp='%s'";
        sql = String.format(sql,p.getIdEmp());
        System.out.println(sql);
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("empresa no existe");
        }
    }
    
        
    public void EmpresaAdd(Empresa p) throws Exception{
        /*if ( this.compararEmpresaVacio(p) ) {
            String sql="insert into EMPRESA (nombreEmp,ubicacionEmp,descripcionEmp,correoEmp,telefono) values ('%s','%s','%s','%s','%s');";
            sql=String.format( sql,p.getNombreEmp(), p.getUbicacionEmp(), p.getDescripcionEmp(),p.getCorreoEmp(), p.getTeléfono() );
            int count = db.executeUpdate(sql);  
            if (count ==0 ){ throw new Exception ("Existe una cuenta con el nombre de esta empresa"); }}
        */
        if ( this.compararEmpresaVacio(p) ) {//  add si no hay espacios vacios agrega
            if ( this.EmpresaGetNombre( p.getNombreEmp() ) == null ) {  // add si no hay empresa cn el mismo nombre
                p.setAprobado(false);
                String sql="insert into bolsaempleo.empresa (nombreEmp , ubicacionEmp, descripcionEmp, correoEmp, telefono,contrasena, aprobado ) "+
                "values(? ,? ,? ,? ,?, ?, ?)";
                //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
                System.out.println(sql);
                db.getConnection();
                
                PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
                preparedStmt.setString(1, p.getNombreEmp());
                preparedStmt.setString (2, p.getUbicacionEmp());
                preparedStmt.setString (3, p.getDescripcionEmp());
                preparedStmt.setString (4, p.getCorreoEmp());
                preparedStmt.setString (5, p.getTeléfono() );
                preparedStmt.setString (6, p.getContrasena() );
                preparedStmt.setBoolean(7, p.getAprobado() );

                preparedStmt.execute();
            }
            else { throw new Exception ("Existe una cuenta con el nombre de esta empresa"); }
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
            throw new Exception ("Existe");
            
        }
    }
        
    public List<Empresa> EmpresaGetAll()  throws Exception{
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
    
    public Empresa EmpresaGetNombre(String codigo) throws Exception{ // busqueda por nombre
        String sql="select * from empresa where nombreEmp='%s'";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return empresa(rs); 
        }
        else { return null; }
    }
    
    public Empresa EmpresaLogin (Empresa e) throws Exception{ // busqueda por nombre
        String sql="select * from Empresa e where e.correoEmp='%s' and e.contrasena='%s'";
        sql = String.format( sql,e.getCorreoEmp(), e.getContrasena() );
        ResultSet rs =  db.executeQuery(sql);
        rs.next();
        return empresa(rs); 
    }
    /*********************Oferente********************************/
    
    private Oferente oferente(ResultSet rs) throws Exception{
        Oferente ec= new Oferente();
            ec.setCedulaOferente(rs.getString("cedulaOferente"));
            ec.setPrimerApellido(rs.getString("primerApellido"));
            ec.setNombreOferente(rs.getString("nombreOferente"));
            ec.setCelular(rs.getString("celular"));
            ec.setNacionalidad(rs.getString("nacionalidad"));
            ec.setCorreoOferente(rs.getString("correoOferente"));
            ec.setUbicacion(rs.getString("ubicacion"));
            ec.setContrasena( rs.getString("contrasena") );
            ec.setAprobado( rs.getBoolean( "aprobado" ) );
            
        return ec;
    }
    
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
      
    public List<Oferente> OferenteGetAll() throws Exception{
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
            p.setAprobado(false);
            String sql= "insert into OFERENTE (cedulaOferente,nombreOferente ,primerApellido ,celular,nacionalidad,correoOferente,ubicacion,contrasena, aprobado) "
                + "values ('%s','%s','%s','%s','%s','%s','%s','%s', %b); ";
            sql=String.format( sql, p.getCedulaOferente(), p.getNombreOferente(), p.getPrimerApellido(), p.getCelular(), p.getNacionalidad(),
                    p.getCorreoOferente(), p.getUbicacion(),p.getContrasena(), p.getAprobado() );
            System.out.println(sql);
            int count=db.executeUpdate(sql);
            if (count ==0 ){ // 0 == existe
                throw new Exception("Existe una cuenta con la misma cédula");
            }
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
        String sql="update bolsaempleo.oferente set  nombreOferente='%s', primerApellido='%s', celular='%s', nacionalidad='%s' , correoOferente='%s' , ubicacion='%s',"   +
                "aprobado=%b where cedulaOferente='%s'";
        sql=String.format(sql,p.getNombreOferente(),p.getPrimerApellido(),p.getCelular() , p.getNacionalidad(), p.getCorreoOferente(),
                p.getUbicacion(), p.getAprobado(), p.getCedulaOferente());
        System.out.println(sql);
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Oferente no existe");
        }
    }
            
    public boolean compararOferenteVacio (Oferente o) { // 
        return !( o.getNombreOferente().equals("") || o.getPrimerApellido().equals("") || o.getCedulaOferente().equals("") ||
                o.getCelular().equals("") || o.getNacionalidad().equals("") ); // no hay espacios vacios
    }
    
    public Oferente OferenteLogin(Oferente o) throws Exception {
        String sql="select * from Oferente o where o.correoOferente='%s' and o.contrasena='%s'";
        sql = String.format( sql,o.getCorreoOferente(), o.getContrasena());
        ResultSet rs =  db.executeQuery(sql);
        rs.next();
        return oferente(rs); 
    }
    
    
            
    
    /*********************CARACTERISTICAS********************************/
// el dilema recursivo = si hago esta llamada ec.setPapa_carac( this.CaracteristicasGet( rs.getInt("idPadre") ) );
// en caracteristicas, va ocurrir una recursiivdad o lago parecido, por eso voy hacer un metodo 
// q cosntruye al padre inmediato
    private Caracteristicas caracteristicas(ResultSet rs) throws Exception{
        try {
            Caracteristicas ec= new Caracteristicas();
            
                ec.setIdCaracteristica( rs.getInt("idCaracteristica") );
                ec.setHabilitado( rs.getBoolean( "habilitado" ) );
                ec.setHabilidad(rs.getString("habilidad"));
                ec.setPadre( rs.getBoolean( "ifPadre" ) );
                if ( !ec.isPadre() ){
                    ec.setPapa_carac( this.CaracteristicasGet( rs.getInt("idPadre") ) );}
                else ec.setPapa_carac (null); 
                
                /*  ec.setAreaTrabajo( rs.getString("areaTrabajo") ); ec.setEspecializacion( rs.getString("especializacion") );*/
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Caracteristicas CaracteristicasGet(int codigo) throws Exception{
        String sql="select * from caracteristicas where idCaracteristica=%d";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql); 
        if (rs.next()) {
            return caracteristicas(rs);
        }
        else{
            throw new Exception ("Caracteristicas no Existe");
        }
    }
     /*
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
          */    
    public void CaracteristicasDelete(Caracteristicas p) throws Exception{
        String sql="delete from bolsaempleo.caracteristicas where idCaracteristica=%d";
        sql = String.format(sql,p.getIdCaracteristica());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("puesto no existe");
        }
    }
    
       /* 
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
    }*/
    
    /*
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
    
    public List<Caracteristicas> CaracteristicasAreaTrabajo( ) throws Exception {
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
    */
    public List<Caracteristicas> CaracteristicasGetAll() throws Exception {
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

    public List<Caracteristicas> AllCaracteristicasPadres() throws Exception { // busca los padres
        List<Caracteristicas> padres=new Vector<Caracteristicas>();
        try {
            //String sql="select * from caracteristicas";
            String sql="select * from caracteristicas where ifPadre=true";
            ResultSet rs =  db.executeQuery(sql);
            Caracteristicas car;
            while (rs.next()) {
                car = caracteristicas(rs);
                padres.add( car );
            }
        } catch (SQLException ex) { throw new Exception("Error AllCaracteristicasPadres"); }
        return padres;        
    }
    
    public List<Caracteristicas> BuscarCaracteristicas( int idP) throws Exception { // busca los hijos de un padre
        List<Caracteristicas> estados=new Vector<Caracteristicas>();
        try {
            String sql="select * from caracteristicas where idPadre=%d";
            sql = String.format(sql,idP);
            ResultSet rs =  db.executeQuery(sql);
            Caracteristicas car;
            while (rs.next()) {
                car = caracteristicas(rs);
                estados.add( car );
            }
        } catch (SQLException ex) { throw new Exception("Error AllCaracteristicasPadres"); }
        return estados;        
    }
    /*********************PUESTOS********************************/
    
    private Puestos puestos(ResultSet rs) throws Exception {
        try {
            Puestos ec= new Puestos();
                ec.setIdPuesto(rs.getInt("idPuesto"));
                // ec.setEmpresa( this.empresa(rs) ); 29/5/2018 lo agregue 
                ec.setEmpresa( EmpresaGet(  rs.getInt("idEmp") ) );
                ec.setNombrePuesto(rs.getString("nombrePuesto"));
                ec.setSalario(rs.getFloat("salario"));
                ec.setDescripcionPuesto(rs.getString("descripcionPuesto"));
                ec.setTipoPublicacion( rs.getBoolean("tipoPublicacion") );
                ec.setActivo( rs.getBoolean("activo") );
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
   
    public void PuestosUpdate(Puestos p) throws Exception {
        System.out.print("PuestosUpdate");
        String sql="update bolsaempleo.puestos set nombrePuesto='%s', idEmp='%s', salario='%s' , descripcionPuesto='%s', "
                + "tipoPublicacion=%b, activo=%b where idPuesto='%s'";
        sql=String.format(sql,p.getNombrePuesto(), p.getEmpresa().getIdEmp(),
                p.getSalario(),p.getDescripcionPuesto(), p.getTipoPublicacion(), p.getActivo(),p.getIdPuesto() );
        System.out.println(sql);
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
        p.setActivo(true);
        String sql="insert into bolsaempleo.puestos (nombrePuesto, idEmp, salario, descripcionPuesto, tipoPublicacion, activo ) "+
                "values(?, ?, ?, ?, ?, ?)";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        preparedStmt.setString(1, p.getNombrePuesto());
        preparedStmt.setInt (2, p.getEmpresa().getIdEmp() );
        preparedStmt.setFloat (3, p.getSalario());
        preparedStmt.setString (4, p.getDescripcionPuesto());
        preparedStmt.setBoolean(5, p.getTipoPublicacion() );
        preparedStmt.setBoolean(6, p.getActivo() );
        
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
        
    public List<Puestos> PuestosGetAll() throws Exception {
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
    
    public List<Puestos> PuestosIDEmpresaGetAll (int IDem) throws Exception {
        Vector<Puestos> estados=new Vector<Puestos>();
        try {
            String sql="select * from puestos where idEmp=%d";
            sql = String.format(sql, IDem );
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(puestos(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    
    /*********************CARACTERISTICAS PUESTOS********************************/
    int keyCarPuesCar;
    private CaracteristicasPuestos caracteristicasPuestos(ResultSet rs){
        try {
            CaracteristicasPuestos ec= new CaracteristicasPuestos();
                    ec.setConsecutivo( rs.getInt("consecutivo") );
                    ec.setValor( rs.getInt("valor") );
                    keyCarPuesCar = rs.getInt( "idCaracteristica" );

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
        String sql="insert into bolsaempleo.CARACTERISTICAS_PUESTOS (consecutivo , caracteristica , idPuesto,idCaracteristica, "
                + "valor ) values(?, ?, ?, ? )";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        preparedStmt.setInt( 1, p.getConsecutivo() );
        preparedStmt.setInt ( 2,  p.getCaracteristicas().getIdCaracteristica() );
        preparedStmt.setInt ( 3,  p.getPuesto().getIdPuesto() );
        preparedStmt.setInt( 4,  p.getValor() );
      
        preparedStmt.execute();
    }
    public List<CaracteristicasPuestos> CaracteristicasPuestosGetALLPuesto(int codigo) throws Exception{
        // busca CARACTERISTICAS_PUESTOS q tengan el Puesto de interes
        Vector<CaracteristicasPuestos> List_CP=new Vector<CaracteristicasPuestos>();
        String sql="select * from CARACTERISTICAS_PUESTOS where idPuesto=%d";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        CaracteristicasPuestos ec= new CaracteristicasPuestos();
        while (rs.next()) {
            List_CP.add( caracteristicasPuestos2(rs) );
        }
        return List_CP;
    }
    public List<CaracteristicasPuestos> CaracteristicasPuestosGetAll(){
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
    
    private CaracteristicasPuestos caracteristicasPuestos2(ResultSet rs) throws Exception{
        try {
            CaracteristicasPuestos ec= new CaracteristicasPuestos();
                    ec.setConsecutivo( rs.getInt("consecutivo") );
                    ec.setValor( rs.getInt("valor") );
                    ec.setPuesto(  PuestosGet( rs.getInt("idPuesto") ) ); 
                    ec.setCaracteristicas( this.CaracteristicasGet( rs.getInt( "idCaracteristica" ) ) );
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
    /*
    public List<Puestos> CaracteristicasPuestosNivelPuGet( CaracteristicasPuestos[] listaN ) throws Exception{ 
        // para publicos nada mas
        Vector<CaracteristicasPuestos> respuesta=new Vector<CaracteristicasPuestos>();
        Vector<Puestos> puestos = new Vector<Puestos>();
        Puestos puesto;
        CaracteristicasPuestos CP; boolean flag =false;
        try {
            String sql="select * from CARACTERISTICAS_PUESTOS;";
            ResultSet rs =  db.executeQuery(sql);
            while ( rs.next() ) {
                CP = caracteristicasPuestos2(rs); // construyo CaracteristicasPuestos
                for ( CaracteristicasPuestos Ca_P: listaN ) { // busco en lista q llego
                    // if ( Ca_P.getCaracteristicas().getIdCaracteristica()  == CP.getCaracteristicas().getIdCaracteristica() ) {
                    if ( Objects.equals(Ca_P.getCaracteristicas().getIdCaracteristica(), CP.getCaracteristicas().getIdCaracteristica()) ) {
                        if ( CP.getValor() == Ca_P.getValor() ) {
                            flag = true;
                        } 
                    }
                }
               if ( flag == true ) {
                    if ( CP.getPuesto().getTipoPublicacion() ) {
                        puesto =  CP.getPuesto();
                        respuesta.add(CP);
                       // puesto.getCaracteristicasPuestos().add(CP);
                        puestos.add( puesto );
                        flag = false;
                    }
                }
            }
        } catch (SQLException ex) {   }
        return puestos;
    }
    */
    public List<Puestos> CaracteristicasPuestosNivelPuGet( CaracteristicasPuestos[] listaN ) throws Exception{ 
        // para publicos nada mas
     //   Vector<CaracteristicasPuestos> ListaCP =new Vector<CaracteristicasPuestos>();
        Vector<Puestos> puestiños = new Vector<Puestos>();
        CaracteristicasPuestos CP; boolean flag =false;
        try {
            String sql="select * from CARACTERISTICAS_PUESTOS;";
            ResultSet rs =  db.executeQuery(sql);
            while ( rs.next() ) {
                ListTop5 = new ArrayList();
                CP = caracteristicasPuestos2(rs); // construyo CaracteristicasPuestos
                for ( CaracteristicasPuestos Ca_P: listaN ) { // busco en lista q llego
                    // if ( Ca_P.getCaracteristicas().getIdCaracteristica()  == CP.getCaracteristicas().getIdCaracteristica() ) {
                    if ( Objects.equals(Ca_P.getCaracteristicas().getIdCaracteristica(), CP.getCaracteristicas().getIdCaracteristica()) ) {
                        if ( CP.getValor() == Ca_P.getValor() ) { //    if ( CP.getValor() >= Ca_P.getValor() ) {
                            flag = true;
                        } 
                    }
                }
               if ( flag == true ) {
                    if ( CP.getPuesto().getTipoPublicacion() ) {
                        puestiños.add( CP.getPuesto() );
                        flag = false;
                    }
                }
            }
        } catch (SQLException ex) {   }
       return puestiños;
     }
    
    public List<Puestos> PPPPP (  List<Puestos> listaP  ) throws Exception{
       try { 
           String sql="select * from CARACTERISTICAS_PUESTOS";
            ResultSet rs =  db.executeQuery(sql);
            CaracteristicasPuestos c;
             while ( rs.next() ) {
                c = caracteristicasPuestos(rs); // encontró uno
                c.setCaracteristicas( this.CaracteristicasGet(keyCarPuesCar) ); // obtengo la caracteristica
                 for ( int i=0; i<listaP.size(); i++ ) { 
                     if ( listaP.get(i).getIdPuesto() == c.getPuesto().getIdPuesto() ) 
                         listaP.get(i).getCaracteristicasPuestos().add(c);
                 }
            }
        } catch (SQLException ex) { }
       return listaP;
    }    
    
    
    public void CaracteristicasPuestosIDpuesto(int codigo, Puestos p) throws Exception{
        // es usado por public List<Puestos> ListTop5 ()
       try { // este metodo busca CaracteristicasPuestos q tengan el id del puesto
            String sql="select * from CARACTERISTICAS_PUESTOS where idPuesto=%d;";
            sql = String.format(sql,codigo);
            ResultSet rs =  db.executeQuery(sql);
            CaracteristicasPuestos c;
            while ( rs.next() ) {
                c = caracteristicasPuestos(rs); // encontró uno
                c.setPuesto(p); // setea el atributo de puesto
                c.setCaracteristicas( this.CaracteristicasGet(keyCarPuesCar) ); // obtengo la caracteristica
                ListTop5.add(c); // lo agrego a lista magica, para agregarlo en puesto
            }
        } catch (SQLException ex) { }
    }    
    
    List<CaracteristicasPuestos> ListTop5 = new ArrayList<>();
    public List<Puestos> ListTop5 () throws Exception {
        List<Puestos> ListPuest = new ArrayList();
    //    CaracteristicasPuestos c; // borrarlo
           try {
            String sql="select * from Puestos p order by p.idPuesto desc limit 5;";
            ResultSet rs =  db.executeQuery(sql);
            Puestos p;
            while ( rs.next() ) {
                ListTop5 = new ArrayList(); // limpio la lista de la busqueda anterior
                p = this.puestos(rs); // obtengo puestos
                // el sguiente hace falta o lo quito
                p.setEmpresa( this.EmpresaGet( rs.getInt( "idEmp" ) ) ); // obtengo la empresa asociada al puesto
                this.CaracteristicasPuestosIDpuesto ( p.getIdPuesto(), p ); // busca CaracteristicasPuestos q tengan el id del puesto
                p.setCaracteristicasPuestos ( ListTop5 ); // agrego la lista CaracteristicasPuestos aL puesto
                ListPuest.add(p); // lo agrego a lista magica depuestoa
            }
        } catch (SQLException ex) { }
        return ListPuest;
    }
    
    
    // 1_PuestosPorCaracteristicas trae puetosXpuesto, cada vez q tiene uno "setea" atributo empresa.luego 2_CaracteristicasPuestosIDpuestoAll
    // ,este busca en los CARACTERISTICAS_PUESTOS quien tiene el mismo idPuesto. Ahora tiene construido a puesto,empresa 
    // y construye a CARACTERISTICAS_PUESTOS, depues por medio de su atributo idCaracteristica trae a CARACTERISTICA y lo compara 
    // con el q tengo por parametro, si es? entonces lo agrega a puetosAll. 
  /*  List<CaracteristicasPuestos> puetosAll = new ArrayList<>();
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
    */
    
    /********************* CARACTERISTICAS OFERENTE ---- CARACTERISTICAS OFERENTE ********************************/
    private CaracteristicasOferente caracteristicasOferente(ResultSet rs) throws Exception{
        try {
            CaracteristicasOferente ec= new CaracteristicasOferente();
                    ec.setIdCO( rs.getInt("idCO") );
                    ec.setValor( rs.getInt("valor") );
                    ec.setOferente( OferenteGet ( rs.getString( "cedulaOferente" ) ) );
                    ec.setCaracteristicas( CaracteristicasGet ( rs.getInt( "idCaracteristica" ) ) );
                    
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public void CaracteristicasOferentesAdd ( CaracteristicasOferente p ) throws SQLException, Exception {
        String sql="insert into CARACTERISTICAS_OFERENTE ( valor,cedulaOferente,idCaracteristica ) "
                + "values(%d, '%s','%s');";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        sql=String.format( sql, p.getValor(),p.getOferente().getCedulaOferente(),p.getCaracteristicas().getIdCaracteristica() );
        int count=db.executeUpdate(sql);
        if (count ==0 ){ // 0 == existe
            throw new Exception("Existe una cuenta con la misma cédula");
        }
    }
    /*
    public void CaracteristicasOferentesAdd ( CaracteristicasOferente p ) throws SQLException {
        String sql="insert into bolsaempleo.CARACTERISTICAS_OFERENTE ( valor,cedulaOferente,idCaracteristica ) "
                + "values(?, ?, ?)";
        //db.cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+"bolsaempleo" , "root" , "root");
        db.getConnection();
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        preparedStmt.setInt( 1,  p.getValor() );
        preparedStmt.setString ( 2,  p.getOferente().getCedulaOferente() );
        preparedStmt.setInt ( 3,  p.getCaracteristicas().getIdCaracteristica() );
        preparedStmt.execute();
    }
    */
    public void CaracteristicasOferenteUpdate (CaracteristicasOferente c) throws Exception {
        String sql="update bolsaempleo.CARACTERISTICAS_OFERENTE set valor='%s' where idCaracteristica=%d;";
        sql = String.format(sql,c.getValor(), c.getCaracteristicas().getIdCaracteristica());
        int count=db.executeUpdate(sql);
        if (count ==0 ){ // 0 == existe
            throw new Exception("error CaracteristicasOferenteUpdate");
        }
    }
    
    public void CaracteristicasOferentesDelete(int ID) throws Exception {
         String sql="delete from CARACTERISTICAS_OFERENTE where idCO=%d";
        sql = String.format(sql,ID);
        System.out.println( ID );
        int count=db.executeUpdate(sql);
        if (count==0){
            System.out.println("CaracteristicasOferentesDelete X.X linea 769");
            throw new Exception("empresa no existe");
        }
    }
    
    public List<CaracteristicasOferente> CaracteristicasOferenteGetCed (String cedula) throws Exception {
        Vector<CaracteristicasOferente> ListCarOfe = new Vector<CaracteristicasOferente>();
        try { // busca todos los Car_Ofe con esa cedula, lo uso en el servlet de registro para "set" de la lista q tiene Oferente
            String sql="select * from CARACTERISTICAS_OFERENTE where cedulaOferente='%s';";
            sql = String.format(sql,cedula);
            ResultSet rs =  db.executeQuery(sql);
            int x =0;
            while (rs.next()) {
                ListCarOfe.add( caracteristicasOferente(rs) );
            }
        }
        catch (SQLException ex) {  throw new Exception ("error en CaracteristicasOferenteGet"); }
        return ListCarOfe;
    }
    
    public CaracteristicasOferente CaracteristicasOferenteGetIdCar (int idCar) throws Exception {
        // busca aquel q tiene ese id Y lo manda
        String sql="select * from CARACTERISTICAS_OFERENTE where idCaracteristica=%d;";
        sql = String.format(sql,idCar);
        ResultSet rs =  db.executeQuery(sql);
        if ( rs.next() ) {
            return caracteristicasOferente( rs );
        }   else {   throw new Exception ("error en CaracteristicasOferenteGetIdCar"); }
    }
    
    public List<Oferente> CaracteristicasOferentesNivelPuGet( CaracteristicasOferente[] listaN ) throws Exception{ 
        // busca Candidatos segun las caracteristicas
        Vector<Oferente> listaO = new Vector<Oferente>();
        CaracteristicasOferente CO; boolean flag =false;
        try {
            String sql="select * from CARACTERISTICAS_OFERENTE;";
            ResultSet rs =  db.executeQuery(sql);
            while ( rs.next() ) {
                CO = caracteristicasOferente(rs); // construyo CaracteristicasOferente
                for ( CaracteristicasOferente Ca_O: listaN ) { // busco en lista q llego
                    if ( Objects.equals(Ca_O.getCaracteristicas().getIdCaracteristica(), CO.getCaracteristicas().getIdCaracteristica()) ) {
                        if ( CO.getValor() == Ca_O.getValor() ) { //    if ( CO.getValor() >= Ca_O.getValor() ) {
                            listaO.add( CO.getOferente() );
                        } 
                    }
                }
            }
        } catch (SQLException ex) {   }
       return listaO;
     }
    
    
    private Administrador administrador(ResultSet rs) throws Exception{
        Administrador ec= new Administrador();
            ec.setCorreoAdministrador( rs.getString("correoAdministrador") );
            ec.setNombreAdministrador( rs.getString("nombreAdministrador") );
            ec.setCedulaAdministrador( rs.getString("cedulaAdministrador") );
            ec.setContrasena( rs.getString("contrasena") );
        return ec;
    }
    
    public Administrador AdministradorLogin(Administrador a) throws SQLException, Exception {
        String sql="select * from Administrador a where a.correoAdministrador='%s' and a.contrasena='%s'";
        sql = String.format( sql,a.getCorreoAdministrador() , a.getContrasena());
        System.out.println( sql );
        ResultSet rs =  db.executeQuery(sql);
        rs.next();
        return administrador(rs); 
    }

    public List<Oferente> OferenteDesaprobadosGetAll()  throws Exception {
        Vector<Oferente> estados=new Vector<Oferente>();
        try {
            String sql="select * from oferente where aprobado=false";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(oferente(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    public List<Empresa> EmpresaDesaprobadosGetAll()  throws Exception {
        Vector<Empresa> estados=new Vector<Empresa>();
        try {
            String sql="select * from empresa where aprobado=false";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(empresa(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
}