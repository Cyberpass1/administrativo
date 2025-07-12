/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;



import java.sql.Connection;
import java.sql.DriverManager;

public class EnlaceBackup {
   
       
    
 private static Connection con;
 public  static Connection getConnection()
 {
     
 
        
 try{       
 Class.forName("com.mysql.cj.jdbc.Driver");
//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fundaginebrabd","root","elefante1999");
//con=DriverManager.getConnection("jdbc:mysql://192.168.0.47:3306/fundaginebrabd","DBfundaginbra","1234");

//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fundaginebra","root","");//FACTURACION
//con=DriverManager.getConnection("jdbc:mysql://192.168.0.70:3306/fundaginebrabd","DBfundaginebra"," "); 
//con=DriverManager.getConnection("jdbc:mysql://192.168.0.70:3306/fundaginebrabd","DBfundaginebra","elefante1999");
//con=DriverManager.getConnection("jdbc:mysql://192.168.0.71:3306/fundaginebrabd","DBfundaginebra","elefante1999");


con = DriverManager.getConnection("jdbc:mysql://193.203.175.84:3306/u437876818_fundagin", "u437876818_DBfundagin", "naibedTI.1"); //Prueba

//-----------------------------NUBE
//con = DriverManager.getConnection("jdbc:mysql://94.130.74.47:3306/fundagin_fundaginbd", "fundagin_DBfundagin", "naibedTI.1"); //Actual
//con = DriverManager.getConnection("jdbc:mysql://104.243.46.249:3306/fundagin_fundaginbd", "fundagin_DBfundagin", "naibedTI.1"); 
//con = DriverManager.getConnection("jdbc:mysql://209.133.196.202:3306/fundagin_fundaginebrabd", "fundagin_DBfundagin", "naibedTI.1"); //Prueba





       }catch(Exception e){  System.err.println("Error al conectar "+ e);
       }
return con;
 }

 
 
   public void desconectar() {
        try {
            con.close();
        } catch (Exception ex) {    System.out.println(ex);
        }
    }
 
 
 
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   

}
