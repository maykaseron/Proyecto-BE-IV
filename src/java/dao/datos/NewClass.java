/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.datos;

import entidades.Oferente;
import java.sql.SQLException;

/**
 *
 * @author pc
 */
public class NewClass {
    
    
      public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception {
          
          Dao d;
          
          d = new Dao();
          
       //   Oferente of = new Oferente("23333333", "Juan" , "Perez" , "Zeledon" , "83737373" , "CR" , "jpz@hmail.com" , "HER" );
          
       Oferente of = new Oferente("343426666", "Jon" , "Paz" , "Zel" , "45323455" , "CR" , "ccc@hmail.com" , "CAR" );
          
        //d.OferenteAdd(of);
        d.OferenteDelete(of);
          
          
          
        System.out.println("Hello World!"); // Display the string.
    }
    
}
