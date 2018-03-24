/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.datos;

/**
 *
 * @author pc
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author jsanchez
 */
public class RelDatabase {
    public static final String PROPERTIES_FILE_NAME="/bolsaempleo.properties";        
    Connection cnx;
    private Statement stmt;
    public RelDatabase(){
        cnx=this.getConnection();            
    }
    public Connection getConnection(){
        
        
        
        
        try {
            System.out.println("Intentado conectar a " + PROPERTIES_FILE_NAME );
            Properties prop = new Properties();
            
            
            System.out.println("Intentado conectar 1");
            /* URL resourceUrl = getClass().getResource(PROPERTIES_FILE_NAME);
            System.out.println("Intentado conectar 2 " + resourceUrl);
            File file = new File(resourceUrl.toURI());
            System.out.println("Intentado conectar 3");     
            
            prop.load(new BufferedInputStream(new FileInputStream(file)));*/
            
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("bolsaempleo.properties")) {
        if (input == null) {
            throw new IOException("bolsaempleo.properties not found");
        }
        prop.load(input);
            
            }
            System.out.println("Intentado conectar 4 ");
            String driver = prop.getProperty("database_driver");
            String server = prop.getProperty("database_server");
            String port = prop.getProperty("database_port");
            String user = prop.getProperty("database_user");
            String password = prop.getProperty("database_password");
            String database = prop.getProperty("database_name");
            System.out.println("user " + user);
            System.out.println("pass " + password);
            System.out.println("database " + database);
            System.out.println("server " + server);
            System.out.println("port " + port);
                System.out.println("driver " + driver);
            
       
            
            
            
            
      /*  String URL_conexion="jdbc:mysql://"+ server+":"+port+"/"+
                    database+"?user="+user+"&password="+password;
        
         System.out.println("url conexion "+URL_conexion);
           Class.forName("com.mysql.jdbc.Driver");
            
            
            return DriverManager.getConnection(URL_conexion);*/
            
           try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        cnx = DriverManager.getConnection("jdbc:mysql://localhost/"+database , user , password);
        stmt = cnx.createStatement();
        System.out.println("conectado");

    } catch (Exception ex) {
        System.out.println(ex.toString());
    }
            
            
         
            
            
            
        } catch (Exception e) {
            System.out.println("Error de conexion");
            System.err.println(e.getMessage());
            System.exit(-1);
        } 
        return null;
    }

    public int executeUpdate(String statement) {
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(statement);
            return stm.getUpdateCount();
        } catch (SQLException ex) {
            return 0;
        }
    }
    public ResultSet executeQuery(String statement){
        try {
            Statement stm = cnx.createStatement();
            return stm.executeQuery(statement);
        } catch (SQLException ex) {
        }
        return null;
    }
    
     
    
    public int executeUpdateWithKeys(String statement) {
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(statement,Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stm.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
        } catch (SQLException ex) {
            return -1;
        }
    }    
}