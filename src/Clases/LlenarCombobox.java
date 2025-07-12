/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class LlenarCombobox {
    
    
  static  Connection con;
  static EnlaceBd cn = new EnlaceBd();
  static PreparedStatement ps;
  static ResultSet rs;
    
      public static ArrayList<String> llenarRol(){
        ArrayList<String> lista = new ArrayList<String>();
        String sql = "SELECT * FROM table_nivel ORDER BY Nivelu ASC";
     
      
       

       
        
        try {
           con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
          
            while(rs.next()){
                lista.add(rs.getString("nivelu"));}
            
        } catch (Exception e) {System.out.println(e);}
        finally {  
  try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (Exception ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }   
    }
       
        return lista;
    }
      
      
      
      
      
      
      
      
      
      
public static ArrayList<String> llenarEspecialidad() {
    ArrayList<String> lista = new ArrayList<>();
    String sql = "SELECT * FROM table_especialidad ORDER BY especialidad ASC";

    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            String especialidad = rs.getString("especialidad");
            int estado = rs.getInt("Id_esta");

            // Verifica que 'especialidad' no sea nulo y que el estado sea 100
            if (especialidad != null && estado == 100) {
                lista.add(especialidad);
            }
        }
    } catch (Exception e) {
        System.err.println("Error en la consulta: " + e.getMessage());
    }

    return lista;
}
          
          
          
          
     
        public static ArrayList<String> llenarAdministrativo(){
        ArrayList<String> lista = new ArrayList<String>();
        String sql = "SELECT * FROM categorias_administrativas ORDER BY Categoria ASC";
       
      
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
          
            while(rs.next()){
           
                lista.add(rs.getString("Categoria"));}
            
        } catch (Exception e) {System.out.println(e);}
         finally{  
     try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (Exception ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }    
    }
       
        return lista;
    }
   
               
          
          
          
      
      
   
         
        public static ArrayList<String> llenarHorarios(){
        ArrayList<String> lista = new ArrayList<String>();
        String sql = "SELECT Doctor, EstadoHorario FROM horario_doctores ORDER BY Doctor ASC";
       
 
   
            
  
        
        try {
            
           con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int estado =rs.getInt("EstadoHorario");
               if( estado==100){
                lista.add(rs.getString("Doctor"));}
            }
        } catch (Exception e) {System.out.println(e);}
        finally{  
     try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (Exception ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }    
    }
       
        return lista;
    }

        
        
        
        
public static ArrayList<String> llenarResultado() {
    String sql = "SELECT diagnostico, estado FROM table_resultado ORDER BY diagnostico ASC";
    ArrayList<String> lista = new ArrayList<>();

    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int estado = rs.getInt("estado");
            if (estado == 100) {
                lista.add(rs.getString("diagnostico"));
            }
        }

    } catch (Exception e) {
        System.out.println("Error al llenar resultado: " + e.getMessage());
    }

    return lista;
}
     
     
     
        
       public static ArrayList<String> llenarCatgServicios() {
        ArrayList<String> lista = new ArrayList<>();
        String sql = "SELECT  `Catg_Servicio`, Catg_estado FROM `categorias_servicios` ORDER BY Catg_Servicio ASC";
        
        // Obtén la conexión a la base de datos
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Procesa los resultados
            while (rs.next()) {
                int estado = rs.getInt("Catg_estado");
                if (estado == 100) {
                    lista.add(rs.getString("Catg_Servicio"));
                }
            }
            
        } catch (Exception e) {
            // Maneja la excepción con un mensaje más específico
            System.err.println("Error al ejecutar la consulta en combobox Catg serv: " + e.getMessage());
        }
        
        return lista;
    }
 
        
       
       
       
       
        public static ArrayList<String> llenarCatgPrd() {
        ArrayList<String> lista = new ArrayList<>();
        String sql = "SELECT  Catg_Prd, Estado_Prd FROM categorias_productos "; //ORDER BY Catg_prd ASC
        
        // Obtén la conexión a la base de datos
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Procesa los resultados
            while (rs.next()) {
                int estado = rs.getInt("Estado_Prd");
                if (estado == 100) {
                    lista.add(rs.getString("Catg_Prd"));
                }
            }
            
        } catch (Exception e) {
            // Maneja la excepción con un mensaje más específico
            System.err.println("Error al ejecutar la consulta en combobox Catg serv: " + e.getMessage());
        }
        
        return lista;
    }
       
        
        
          
        
          public static ArrayList<String> llenarServicio(){
        ArrayList<String> lista = new ArrayList<String>();
        
        try {
              
            String sql = "SELECT `id_servicio`, `servicio`, `serv_estado` FROM `table_servicios` ORDER BY `servicio` ASC";

      
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
          
            while(rs.next()){
                int estado =rs.getInt("serv_estado");
                if( estado==100){
                lista.add(rs.getString("servicio"));}
            }
        } catch (Exception e) {System.out.println(e);}
       
        try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (Exception ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }
       
        return lista;
    }
        
        
          
          
 public static ArrayList<String> llenarConsultas(String especialidadSeleccionada) {
    ArrayList<String> lista = new ArrayList<>();
    String sql = "SELECT `id_servicio`, `serv_codigo`, `servicio`, `serv_estado` FROM `table_servicios` " +
                 "WHERE   `servicio` LIKE ? AND `serv_estado` = 100 ORDER BY servicio ASC";

    // Obtén la conexión a la base de datos
    try (Connection con = cn.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        // Establece el parámetro para filtrar por "Consulta"
     //   ps.setString(1, "%Consulta%");  
        
        // Establece el parámetro para la especialidad seleccionada
        ps.setString(1, "%" + especialidadSeleccionada + "%"); // Filtro para la especialidad
        
        try (ResultSet rs = ps.executeQuery()) {
            // Procesa los resultados
            while (rs.next()) {
                lista.add(rs.getString("servicio"));
            }
        }
        
    } catch (Exception e) {
        System.err.println("Error al ejecutar la consulta en combobox Catg serv: " + e.getMessage());
    }
      finally {
           try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (Exception ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }
        }
    return lista;
}
        
          
          
          
          
          
             private void closeResources(ResultSet rs, PreparedStatement ps, Connection con) {
    try {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    } catch (Exception ex) {
        System.out.println("Error al cerrar la conexión o los recursos: " + ex.getMessage());
    }
}      
          
          
          
          
        
}
